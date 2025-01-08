/**
 * @file input.c 
 * @author Muzamani Gausi (mhgausi)
 * This file processes input, reading individual lines
 * in the given file
*/
#include <stdio.h>
#include <stdlib.h>
#include "input.h"

char *readLine(FILE *fp) 
{
    int bufferSize = 128; 
    int position = 0;
    char *buffer = malloc(bufferSize);  
    if (buffer == NULL) 
    {
        fprintf(stderr, "Memory allocation error\n");
        exit(EXIT_FAILURE);
    }
    int ch;
    while ((ch = fgetc(fp)) != EOF && ch != '\n') 
    {
        buffer[position++] = ch;
        if (position >= bufferSize) 
        {
            bufferSize *= 2;  
            buffer = realloc(buffer, bufferSize);
            if (buffer == NULL) 
            {
                fprintf(stderr, "Memory allocation error\n");
                exit(EXIT_FAILURE);
            }
        }
    }

    if (position == 0 && ch == EOF) 
    {
        free(buffer);
        return NULL;
    }

    buffer[position] = '\0';  
    return buffer;
}