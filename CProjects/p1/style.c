/**
 * @file style.c
 * @author Muzamani Gausi (mhgausi)
 * This program prints a paragraph of random words as well as the 
 * number of words contained in the paragraph 
 */

#include <stdio.h>
#include <stdlib.h>

/** Max length of printed line */
#define LENGTH_LIMIT 72

/**
 * Prints a word with random lower-case letters
 * @param x length of the word being printed
 */
void printWord(int x)
{
    for ( int i = 0; i < x; i++ ) {
        // Print a random lower-case letter.
        printf("%c", 97 + rand() % 26);
    }
}

/** 
 * Prints a line of words, each at a random length
 * Space printed after each word except for the last
 * @return the number of words printed in the line
 */
int printLine() 
{
    int count = 0, pos = 0, space = 0;
    int len = 1 + rand() % 10;
	// Print a line of words up to a limited length.
    while (pos + len + space < LENGTH_LIMIT) {
	    if (space > 0) {
		    printf(" ");
        }
		printWord(len);
		pos += len + space;
		len = 1 + rand() % 10;
		space = 1;
		count += 1;
	}
	printf("\n");
	return count;
}
/**
 * Prints a paragraph with multiple lines using the printLine function
 * @param n number of lines printed in the paragraph
 * @return the total number of words in the paragraph
 */
int printParagraph(int n) 
{
	int total = 0;
	for (int i = 0; i < n; i++) {
		total += printLine();
	}
	return total;
}

/**
 * Generates and prints a paragraph along with printing the total number of
 * words in the paragraph
 * @return 0 after successful execution
 */
int main()
{
	int w = printParagraph(10);
	printf("Words: %d\n", w);
	return 0;
}