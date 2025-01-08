/**
 * @file password.c
 * @author Muzamani Gausi (mhgausi)
 * Provides functions for MD5-based password hashing
 */

#include "password.h"
#include "magic.h"
#include "md5.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h> 

/** Number of iterations of hashing to make a password. */
#define PW_ITERATIONS 1000

/**
 * Computes the alternate hash by concatenating the password, salt, and password again
 * 
 * @param pass password string
 * @param salt salt string
 * @param altHash array to store the computed alternate hash
 */
void computeAlternateHash(char const pass[], char const salt[SALT_LENGTH + 1], byte altHash[HASH_SIZE]) {
    Block *block = makeBlock();
    appendString(block, pass);       
    appendString(block, salt);      
    appendString(block, pass);       
    md5Hash(block, altHash);         
    freeBlock(block);
}

/**
 * Computes the first intermediate hash by combining the password,
 * salt, and bytes from the alternate hash
 * 
 * @param pass password string
 * @param salt salt string
 * @param altHash alternate hash computed previously
 * @param intHash array to store the first intermediate hash
 */
void computeFirstIntermediate(char const pass[], char const salt[SALT_LENGTH + 1], byte altHash[HASH_SIZE], byte intHash[HASH_SIZE]) {
    size_t length = strlen(pass);
    Block *block = makeBlock();
    appendString(block, pass);
    appendString(block, "$1$");
    appendString(block, salt);

    for (size_t i = 0; i < length; i++) {
        appendByte(block, altHash[i % HASH_SIZE]);
    }
    for (size_t i = length; i > 0; i >>= 1) {
        if (i & 1) {
            appendByte(block, 0x00);
        } else {
            appendByte(block, pass[0]);
        }
    }
    md5Hash(block, intHash);
    freeBlock(block);
}

/**
 * Computes each intermediate hash after the first. Uses the iteration number to
 * determine the specific values to append to the block for hashing
 * 
 * @param pass password string
 * @param salt salt string
 * @param inum current iteration number
 * @param intHash array holding the previous intermediate hash
 */
void computeNextIntermediate(char const pass[], char const salt[SALT_LENGTH + 1], int inum, byte intHash[HASH_SIZE]) {
    Block *block = makeBlock();
    if (inum % 2 == 0) {
        for (int i = 0; i < HASH_SIZE; i++) {
            appendByte(block, intHash[i]);
        }
    } 
    else {
        appendString(block, pass);  
    }
    if (inum % 3 != 0) {
        appendString(block, salt);  
    }
    if (inum % 7 != 0) {
        appendString(block, pass);  
    }
    if (inum % 2 == 0) {
        appendString(block, pass);  
    } else {
        for (int i = 0; i < HASH_SIZE; i++) {
            appendByte(block, intHash[i]);  
        }
    }
    md5Hash(block, intHash);  
    freeBlock(block);
}

/**
 * Converts a 16-byte hash into a printable string 
 * 
 * @param hash the input 16-byte hash
 * @param result output string
 */
void hashToString(byte hash[HASH_SIZE], char result[PW_HASH_LIMIT + 1]) 
{
    byte permutedHash[HASH_SIZE];
    for (int i = 0; i < HASH_SIZE; i++) {
        permutedHash[i] = hash[pwPerm[i]];
    }
    int bitIndex = 0;
    for (int i = 0; i < PW_HASH_LIMIT; i++) {
        int value = 0;
        for (int j = 0; j < 6; j++) {
            int byteIdx = bitIndex / 8;
            int bitPos = bitIndex % 8;
            if (byteIdx < HASH_SIZE) {
                value |= ((permutedHash[byteIdx] >> bitPos) & 1) << j;
            }
            bitIndex++;
        }
        result[i] = pwCode64[value];
    }
    result[PW_HASH_LIMIT] = '\0';  
}

void hashPassword(char const pass[], char const salt[SALT_LENGTH + 1], char result[PW_HASH_LIMIT + 1]) 
{
    byte altHash[HASH_SIZE];
    byte intHash[HASH_SIZE];
    computeAlternateHash(pass, salt, altHash);         
    computeFirstIntermediate(pass, salt, altHash, intHash); 

    for (int i = 0; i < PW_ITERATIONS; i++) {
        computeNextIntermediate(pass, salt, i, intHash);
    }
    hashToString(intHash, result); 
}