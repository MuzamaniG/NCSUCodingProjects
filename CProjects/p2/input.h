/**
 * @file input.h
 * @author Muzamani Gausi (mhgausi)
 * Header file for input.c 
 */

#include <stdbool.h>
#include <stdio.h>
#include "replace.h"

/** Exit status for words more than 24 characters in length */
#define WORD_EXIT 101
/** Exit status for lines exceeding 80 character limit */
#define LINE_EXIT 103
/** Max number of characters per word */
#define FIELD_MAX 24
/** Max number of characters per line */
#define LINE_MAX 80
/** Error for missing replacement strings */
#define STRING_ERROR 100

/**
 * This function is given a string and 
 * reads into that string a replacement word 
 * from standard input. If the word is more 
 * than 24 characters in length, the program 
 * should terminate with an exit status of 101. 
 * It returns nothing.
 * @param str string being read
 */
void readWord(char str[FIELD_MAX + 1]);

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
bool readLine(char str[LINE_MAX + 1]);