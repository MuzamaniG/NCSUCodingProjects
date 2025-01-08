/**
 * @file replace.c
 * @author Muzamani Gausi (mhgausi)
 * This file allows for the replacement of words
 * in each line.
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h> 
#include <ctype.h>
#include "replace.h"

/**
 * This function is used to replace placeholders
 * in each line of the memo. For example, if John 
 * is the placeholder part to be replaced, this 
 * function should replace ALL John placeholders 
 * with word starting at the beginning of the line 
 * of text. It returns nothing.
 * @param line a line of text contained in the memo
 * @param part the part to be replaced
 * @param word the word replacing the part
 */
void replaceWord( char line[], char part[], char word[] ) 
{
    int linelen = strlen(line);
    int partlen = strlen(part);
    int wordlen = strlen(word);
    char newline[LINE_MAX + 1];
    int newpos = 0;
    bool match;

    for (int i = 0; i < linelen; i++) {
        if (line[i] == part[0] && i + partlen - 1 < linelen) {
            match = true;
            for (int j = 0; j < partlen; j++) {
                if (line[i + j] != part[j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                for (int j = 0; j < wordlen; j++) {
                    newline[newpos++] = word[j];
                }
                i += partlen - 1;
            } 
            else {
                newline[newpos++] = line[i];
            }
        } 
        else {
            newline[newpos++] = line[i];
        }
    }
    newline[newpos] = '\0';
    strcpy(line, newline);
}

/**
 * Computes the length of the resulting 
 * line of text if the replacement is made. If the 
 * replacement would result in the line exceeding its 
 * 80 character limit, the program is terminated with 
 * an exit status of 103.
 * @param a line of text contained in the memo
 * @param part_length length of the part being replaced
 * @param word_length length of the word replacing the part
 */
void computeLen( char line[], int part_length, int word_length ) 
{
    int linelen = strlen(line);
    int space = linelen + (word_length - part_length);

    if (space > LINE_MAX) {
        exit(LINE_EXIT); 
    }
}

/**
 * Similar to replaceWord(), but it 
 * also performs the capitalization changes required 
 * for pronouns. For example, if part is he and word 
 * is she, it will replace occurrences of he in line 
 * with the string she. However, occurrences that are 
 * capitalized, like He will be replaced with She. It 
 * also should check that the pronouns follow the 
 * she/her/her or they/them/their format.
 * @param line a line of text contained in the memo
 * @param part the part to be replaced
 * @param word the word replacing the part
 */
void replacePronoun( char line[], char part[], char word[] ) 
{
    int linelen = strlen(line);
    int placeholder_len = strlen(part);
    int word_len = strlen(word);
    char newline[LINE_MAX + 1];
    for (int i = 0; i <= LINE_MAX; i++) {
        newline[i] = 0;
    }
    int new_counter = 0;
    for (int i = 0; i < linelen; i++) {
        if (strncmp(&line[i], part, placeholder_len) == 0 && 
            (i == 0 || !isalnum(line[i-1])) &&
            !isalnum(line[i + placeholder_len])) {
            for (int j = 0; j < word_len; j++) {
                if (new_counter < LINE_MAX) {
                    newline[new_counter++] = word[j];
                }
            }
            i += placeholder_len - 1;
        } 
        else {
            if (new_counter < LINE_MAX) {
                newline[new_counter++] = line[i];
            }
        }
    }
    newline[new_counter] = '\0';
    strcpy(line, newline);
}
