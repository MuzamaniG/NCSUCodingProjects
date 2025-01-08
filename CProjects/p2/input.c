/**
 * @file input.c
 * @author Muzamani Gausi (mhgausi)
 * This file allows for the reading of given words
 * and lines of limited lengths. 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "input.h"

/**
 * This function is given a string and 
 * reads into that string a replacement word 
 * from standard input. If the word is more 
 * than 24 characters in length, the program 
 * should terminate with an exit status of 101. 
 * It returns nothing.
 * @param str string being read
 */
void readWord( char str[ FIELD_MAX + 1 ]) 
{
    int i = 0;
    char ch;
    while (i < FIELD_MAX && (ch = getchar()) != EOF && ch != '\n') {
        str[i++] = ch;
    }
    str[i] = '\0';
    if (i == 0) {
        exit(STRING_ERROR); 
    }
    if (i > FIELD_MAX) {
        exit(WORD_EXIT); 
    }
}

/**
 * This function is given a string and reads 
 * into that string a line of text from standard
 * input. If the line of text is more than 80 
 * characters in length, the program should terminate
 *  with an exit status of 103. It returns true if the 
 * text is successfully read without an error and returns
 * false if the end-of-file is reached. The last line of 
 * text in an input file can be assumed to be terminated
 *  by a newline before the end-of-file.
 * @param str string being read
 * @return true if text is successfully read without error
 * and false if the end of file is reached
 */
bool readLine( char str[ LINE_MAX + 1 ]) 
{
    int i = 0;
    char ch;
    while (i < LINE_MAX && (ch = getchar()) != EOF) {
        if (ch == '\n') {
            break; 
        }
        str[i++] = ch; 
    }
    str[i] = '\0';
    if (i == 0) {
        return false;
    }
    if (i > LINE_MAX) {
        exit(LINE_EXIT);
    }

    return true; 
}
