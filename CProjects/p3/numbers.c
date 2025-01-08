/**
 * @file numbers.c
 * @author Muzamani Gausi (mhgausi)
 * File for adding, subtracting, and multiplying numbers to check for overflow, as well as
 * read distances and long numbers from files
 */
#include "numbers.h"
#include <limits.h>
#include <ctype.h>
#include <stdlib.h>

/** Number of fractional digits in a number representing a distance. */
#define DISTANCE_FRAC 1

/**
 * Method for adding numbers to result pointer value
 * @param result sum of numbers
 * @param a first number being added
 * @param b second number being added
 */
bool add(unsigned long *result, unsigned long a, unsigned long b) {
    if (ULONG_MAX - a < b) {
        fprintf(stderr, "Overflow\n");
        exit(EXIT_FAILURE);
        return false;
    }
    *result = a + b;
    return true;
}

/**
 * Method for subtracting numbers to result pointer value
 * @param result final answer
 * @param a number being subtracted from
 * @param b number that is subtracted
 */
bool subtract(unsigned long *result, unsigned long a, unsigned long b) {
    if (a < b) {
        fprintf(stderr, "Overflow\n");
        exit(EXIT_FAILURE);
        return false;
    }
    *result = a - b;
    return true;
}

/**
 * Method for multiplying values into result
 * @param result product of numbers
 * @param a first number being multiplied
 * @param b second number being multiplied
 */
bool multiply(unsigned long *result, unsigned long a, unsigned long b) {
    if (a != 0 && ULONG_MAX / a < b) {
        fprintf(stderr, "Overflow\n");
        exit(EXIT_FAILURE);
        return false;
    }
    *result = a * b;
    return true;
}

/**
 * Reads long numbers from route file
 * @param fp file being read from
 * @param result final processed long number
 */
bool readLongNumber(FILE *fp, unsigned long *result) {
    unsigned long temp = 0;
    int c;
    bool found_digit = false;
    while ((c = fgetc(fp)) != EOF && isspace(c));

    while (c != EOF && isdigit(c)) {
        found_digit = true;
        unsigned long digit = c - '0';
        if (temp > (ULONG_MAX - digit) / 10) {
            return false;  
        }
        temp = temp * 10 + digit;
        c = fgetc(fp);  
    }
    if (!found_digit) {
        return false;  
    }
    if (c != EOF && !isspace(c)) {
        ungetc(c, fp);
    }

    *result = temp;
    return true;
}

/**
 * Reads distances from map file
 * @param fp file being read from
 * @param result final processed distance
 */
bool readDistance(FILE *fp, unsigned long *result) {
    unsigned long integerPart = 0;
    unsigned long fractionalPart = 0;
    int c;
    bool foundDecimal = false;
    bool foundFractionalDigit = false;  // To ensure exactly one digit after the decimal

    while ((c = fgetc(fp)) != EOF) {
        if (isdigit(c)) {
            unsigned long digit = c - '0';
            if (!foundDecimal) {
                // Check for potential overflow when adding the digit to integerPart
                if (integerPart > (ULONG_MAX - digit) / 10) {
                    return false;  // Overflow detected
                }
                integerPart = integerPart * 10 + digit;
            } 
            else {
                if (foundFractionalDigit) {
                    return false;  // More than one digit after the decimal point
                }
                fractionalPart = digit;  // Only one digit after the decimal
                foundFractionalDigit = true;
            }
        } 
        else if (c == '.' && !foundDecimal) {
            foundDecimal = true;  // Start reading fractional part
        } 
        else if (isspace(c)) {
            if (foundDecimal || foundFractionalDigit) {
                break;  // Stop reading at whitespace after we've parsed the number
            } 
        } 
        else {
            return false;  // Invalid character
        }
    }

    // Check if we have a valid distance
    if (!foundDecimal || !foundFractionalDigit) {
        return false;  // Either no decimal point or no fractional digit
    }

    // Combine the integer part and fractional part (fractionalPart represents tenths)
    // Check for overflow when combining integerPart and fractionalPart
    if (integerPart > (ULONG_MAX / 10)) {
        return false;  // Overflow detection before final multiplication
    }
    
    *result = integerPart * 10 + fractionalPart;  

    // Check if the combined result exceeds ULONG_MAX
    if (*result < integerPart * 10 || *result < fractionalPart) {
        return false;  // Final result exceeded ULONG_MAX
    }

    return true;
}