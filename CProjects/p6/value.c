/**
 * @file value.c
 * @author Muzamani Gausi (mhgausi)
 * File for values containing required data
*/
#include "value.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

bool blankString( char const *str )
{
  // Skip spaces.
  while ( isspace( *str ) )
    ++str;

  // Return false if we see non-whiespace before the end-of-string.
  if ( *str )
    return false;
  return true;
}

/**
 * Generic destroy method, suitable for most types of values.  It
 * assumes the data can be freed as a single block of heap memory.
 * @param v value being destroyed
 */
static void destroyGeneric( Value *v )
{
  free( v->data );
  free( v );
}

/**
 * Print method for Integer.
 * @param v value to get integer from
 */ 
static void printInteger( Value const *v )
{
  // Print the data as an integer.
  printf( "%d", * (int *) v->data );
}

/**
 * Print method for Double.
 * @param v value to get double from
 */ 
static void printDouble(Value const *v) {
    printf("%f", *(double *)v->data);
}

/**
 * Print method for String.
 * @param v value to get string from
 */ 
static void printString(Value const *v) {
    printf("\"%s\"", (char *)v->data);
}

Value *parseInteger( char const *str )
{
  // Parse an integer value from the given string.
  int val, n;
  if ( sscanf( str, "%d%n", &val, &n ) != 1 )
    return NULL;

  // Make sure there's nothing extra after the integer value
  if ( ! blankString( str + n ) )
    return NULL;
  
  // Allocate space for the value struct and the integer in its data field.
  Value *this = (Value *) malloc( sizeof( Value ) );
  this->data = malloc( sizeof( int ) );
  * (int *) this->data = val;

  // Fill in function pointers and return this value.
  this->print = printInteger;
  this->destroy = destroyGeneric;
  return this;
}

Value *parseDouble(char const *str) {
    double val;
    int n;
    if (sscanf(str, "%lf%n", &val, &n) != 1) {
        return NULL;
    }
    if (!blankString(str + n)) {
        return NULL;
    }

    Value *this = malloc(sizeof(Value));
    this->data = malloc(sizeof(double));
    *(double *)this->data = val;
    this->print = printDouble;
    this->destroy = destroyGeneric;
    return this;
}

Value *parseString(char const *str) {
    while (isspace(*str)) {
        ++str; 
    }
    if (*str != '"') {
        return NULL; 
    }
    str++; 
    const char *end = strchr(str, '"');
    if (!end) {
        return NULL; 
    }
    Value *this = malloc(sizeof(Value));
    if (!this) {
        return NULL; 
    }
    size_t len = end - str;
    this->data = malloc(len + 1);
    if (!this->data) {
        free(this); 
        return NULL;
    }

    strncpy(this->data, str, len);
    ((char *)this->data)[len] = '\0';

    this->print = printString;
    this->destroy = destroyGeneric;
    return this;
}

