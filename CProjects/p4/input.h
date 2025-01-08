#include <stdio.h>

/**
 * Reads a line of text from the specified file until it encounters a newline or 
 * the end of the file and returns it as a dynamically allocated string. 
 * 
 * @param fp file being read from 
 * @return pointer to the dynamically allocated string containing the line, or 
 * null if the end of the file is reached and no characters were read
 */
char *readLine(FILE *fp);