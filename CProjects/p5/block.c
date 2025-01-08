
#include "block.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "magic.h"

Block *makeBlock() {
    Block *block = (Block *)malloc(sizeof(Block));
    if (block == NULL) {
        perror("Failed to allocate Block");
        exit(EXIT_FAILURE);
    }
    block->len = 0; 
    return block;
}

void freeBlock(Block *block) {
    if (block) {
        free(block);
    }
}

void appendByte(Block *dest, byte b) {
    if (dest->len >= BLOCK_SIZE) {
        fprintf(stderr, "Block overflow\n");
        exit(EXIT_FAILURE);
    }
    dest->data[dest->len] = b;
    dest->len++;
}

void appendString(Block *dest, char const *src) {
    int src_len = strlen(src);
    if (dest->len + src_len > BLOCK_SIZE) {
        fprintf(stderr, "Block overflow\n");
        exit(EXIT_FAILURE);
    }
    for (int i = 0; i < src_len; i++) {
        appendByte(dest, (byte)src[i]);
    }
}