/** 
*/

#ifndef _BLOCK_H_
#define _BLOCK_H_


#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "magic.h"

/** A (partially filled) block of up to 64 bytes. */
typedef struct {
  /** Array of bytes in this block. */
  byte data[ BLOCK_SIZE ];

  /** Number of bytes in the data array currently in use. */
  int len;
} Block;

/**
 * Initializes a new Block structure
 * @return pointer to the dynamically allocated Block
 */
Block *makeBlock();

/**
 * Frees the memory allocated for a Block 
 * @param block pointer to the Block being freed
 */
void freeBlock(Block *block);

/**
 * Appends a byte to the end of the specified Block 
 * @param dest pointer to the Block where the byte should be appended
 * @param b byte to be appended
 */
void appendByte(Block *dest, byte b);

/**
 * Appends a string to the end of the specified Block
 * @param dest pointer to the Block where the string should be appended
 * @param src the string to append
 */
void appendString(Block *dest, char const *src);

#endif
