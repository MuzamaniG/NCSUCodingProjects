/**
 * @file md5.c
 * @author Muzamani Gausi (mhgausi)
 * Implementation of the MD5 hashing algorithm
 */

#include "md5.h"
#include "block.h"
#include "magic.h"
#include <stdlib.h>
#include <stdio.h>  

/** Function type for the f functions in the md5 algorithm */
typedef word (*FFunction)( word, word, word );

/** Function type for the g functions in the md5 algorithm */
typedef int (*GFunction)( int );

/** 
 * F function for the first round.
 * Performs a bitwise operation on three intermediate state variables
 * 
 * @param B Intermediate state variable
 * @param C Intermediate state variable
 * @param D Intermediate state variable
 * @return Computed word based on B, C, and D
 */
word fVersion0(word B, word C, word D) { return (B & C) | (~B & D); }

/** 
 * F function for the second round.
 * Performs a bitwise operation on three intermediate state variables
 * 
 * @param B Intermediate state variable
 * @param C Intermediate state variable
 * @param D Intermediate state variable
 * @return Computed word based on B, C, and D
 */
word fVersion1(word B, word C, word D) { return (D & B) | (~D & C); }

/** 
 * F function for the third round.
 * Performs a bitwise operation on three intermediate state variables
 * 
 * @param B Intermediate state variable
 * @param C Intermediate state variable
 * @param D Intermediate state variable
 * @return Computed word based on B, C, and D
 */
word fVersion2(word B, word C, word D) { return B ^ C ^ D; }

/** 
 * F function for the fourth round.
 * Performs a bitwise operation on three intermediate state variables
 * 
 * @param B Intermediate state variable
 * @param C Intermediate state variable
 * @param D Intermediate state variable
 * @return Computed word based on B, C, and D
 */
word fVersion3(word B, word C, word D) { return C ^ (B | ~D); }

/** 
 * G function for the first round
 * Computes the index used to select a message word
 * 
 * @param idx index used for computation
 * @return computed index for the round
 */
int gVersion0(int idx) { return idx; }

/** 
 * G function for the second round
 * Computes the index used to select a message word
 * 
 * @param idx index used for computation
 * @return computed index for the round
 */
int gVersion1(int idx) { return (5 * idx + 1) % 16; }

/** 
 * G function for the third round
 * Computes the index used to select a message word
 * 
 * @param idx index used for computation
 * @return computed index for the round
 */
int gVersion2(int idx) { return (3 * idx + 5) % 16; }

/** 
 * G function for the fourth round
 * Computes the index used to select a message word
 * 
 * @param idx index used for computation
 * @return computed index for the round
 */
int gVersion3(int idx) { return (7 * idx) % 16; }

/** Array of f functions for each MD5 round */
static FFunction fFunctions[] = {fVersion0, fVersion1, fVersion2, fVersion3};

/** Array of g functions for each MD5 round */
static GFunction gFunctions[] = {gVersion0, gVersion1, gVersion2, gVersion3};

/**
 * Rotates a word to the left by the specified number of bits
 * 
 * @param value The word to rotate
 * @param s number of bits to rotate by
 * @return rotated word
 */
word rotateLeft(word value, int s) 
{
    return (value << s) | (value >> (32 - s));
}

/**
 * Performs an iteration of the MD5 algorithm on a given block
 * 
 * @param M 16-word message array
 * @param A Pointer to the A state variable
 * @param B Pointer to the B state variable
 * @param C Pointer to the C state variable
 * @param D Pointer to the D state variable
 * @param i current iteration index
 */
void md5Iteration(word M[BLOCK_WORDS], word *A, word *B, word *C, word *D, int i) 
{
    int round = i / 16;
    word F = fFunctions[round](*B, *C, *D);
    int G = gFunctions[round](i);
    word temp = *D;
    *D = *C;
    *C = *B;
    *B = *B + rotateLeft((*A + F + md5Noise[i] + M[G]), md5Shift[i]);
    *A = temp;
}

/**
 * Pads the given block to meet the requirements for MD5 hashing
 * @param block Pointer to the Block structure to pad
 */
void padBlock(Block *block) 
{
    unsigned long long bitLength = block->len * 8;
    appendByte(block, 0x80);
    while (block->len % 64 != 56) {
        appendByte(block, 0x00);
    }
    for (int i = 0; i < 8; i++) {
        appendByte(block, (byte)(bitLength >> (8 * i)));
    }
}

/**
 * Computes the MD5 hash for a given block
 * 
 * @param block pointer to the Block to hash
 * @param hash byte array where the hash will be stored
 */
void md5Hash(Block *block, byte hash[HASH_SIZE]) 
{
    word A = md5Initial[0];
    word B = md5Initial[1];
    word C = md5Initial[2];
    word D = md5Initial[3];
    padBlock(block);
    word M[BLOCK_WORDS];
    for (int i = 0; i < BLOCK_WORDS; i++) {
        M[i] = (block->data[i * 4]) | (block->data[i * 4 + 1] << 8) |
               (block->data[i * 4 + 2] << 16) | (block->data[i * 4 + 3] << 24);
    }
    for (int i = 0; i < 64; i++) {
        md5Iteration(M, &A, &B, &C, &D, i);
    }
    A += md5Initial[0];
    B += md5Initial[1];
    C += md5Initial[2];
    D += md5Initial[3];
    word finalHash[] = {A, B, C, D};
    for (int i = 0; i < 4; i++) {
        hash[i * 4] = (byte)(finalHash[i] & 0xFF);
        hash[i * 4 + 1] = (byte)((finalHash[i] >> 8) & 0xFF);
        hash[i * 4 + 2] = (byte)((finalHash[i] >> 16) & 0xFF);
        hash[i * 4 + 3] = (byte)((finalHash[i] >> 24) & 0xFF);
    }
}