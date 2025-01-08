/**
 * @file replace.h
 * @author Muzamani Gausi (mhgausi)
 * Header file for replace.c 
 */

/** Maximum length of a unity ID. */
#define UNITY_MAX 8

/** Maximum length of all other replacement strings. */
#define FIELD_MAX 24

/** Exit status for lines exceeding 80 character limit */
#define LINE_EXIT 103

/** Maximum length of lines in the memo. */
#define LINE_MAX 80

/** Placeholder for the first name. */
#define FIRST "John"

/** Placeholder for the last name. */
#define LAST "Doe"

/** Placeholder for the unity ID. */
#define UNITY "jdoe"

/** Placeholder for the subjective pronoun. */
#define PRONOUN1 "he"

/** Placeholder for the objective pronoun. */
#define PRONOUN2 "him"

/** Placeholder for the possessive adjective. */
#define PRONOUN3 "his"

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
void replaceWord(char line[], char part[], char word[]);

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
void replacePronoun(char line[], char part[], char word[]);

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
void computeLen(char line[], int part_length, int word_length);