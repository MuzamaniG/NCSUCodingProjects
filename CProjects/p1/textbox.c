/**
 * @file textbox.c
 * @author Muzamani Gausi (mhgausi)
 * This program takes an input of text and creates a border of asterisks around it
 */
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/** Width of each line of text in the box. */
#define LINE_WIDTH 60

/** Symbol used to draw the border around the box. */
#define BORDER '*'

/**
 * Reads and prints a line of text inside the border
 * @return true if a line was successfully read and printed and false if EOF is hit
 */
bool paddedLine()
{
    int ch;
    int count = 0;
    ch = getchar();
    if (ch == EOF) {
        return false;
    }
    printf("%c", BORDER);
    while (count < LINE_WIDTH && ch != EOF && ch != '\n') {
        printf("%c", ch);
        count++;
        ch = getchar(); 
    }
    while (count < LINE_WIDTH) {
        printf(" ");
        count++;
    }
    printf("%c\n", BORDER);
    while (ch != EOF && ch != '\n') {
        ch = getchar();
    }
    return true;
}

/**
 * Prints out multiple copies of the given character, 
 * followed by a newline. The number of copies is determined
 * by the count parameter. 
 * @param ch character being copied
 * @param count number of copies
 */
void lineOfChars(char ch, int count)
{
  for (int i = 0; i < count; i++) {
    printf("%c", ch);
  }
  printf("\n");
}

/**
 * Uses the lineOfChars function and paddedLine function
 * to take text and create a border around it
  */
int main()
{
    lineOfChars(BORDER, LINE_WIDTH + 2);
    while (paddedLine()) {
        //simply running the paddedLine function until it returns false
    }
    lineOfChars(BORDER, LINE_WIDTH + 2);
    return 0;
}
