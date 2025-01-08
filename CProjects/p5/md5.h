/**
 * @file md5.h
 * @author Muzamani Gausi (mhgausi)
 * Header for md5.c hashing 
 */

#ifndef _MD5_H_
#define _MD5_H_

#include "block.h"

/** Number of bytes in a MD5 hash */
#define HASH_SIZE 16

/**
 * Computes the MD5 hash for a given block
 * 
 * @param block pointer to the Block to hash
 * @param hash byte array where the hash will be stored
 */
void md5Hash(Block *block, byte hash[HASH_SIZE]);

#endif
