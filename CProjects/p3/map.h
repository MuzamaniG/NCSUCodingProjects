/**
 */
#include <stdbool.h>
#include <stdio.h>

/** Maximum length of a location name. */
#define NAME_LIMIT 20
/** Maximum number of locations. */
#define LOC_LIMIT 1000
/** Max length of location names */
#define LOC_NAME_LENGTH 21
/** Maximum number of tracks. */
#define TRACK_LIMIT ( LOC_LIMIT * ( LOC_LIMIT - 1 ) / 2 )


void readMap(char const *filename);

bool getDistance(unsigned long *dist, char const *src, char const *dest);

bool add(unsigned long *result, unsigned long a, unsigned long b);

bool subtract(unsigned long *result, unsigned long a, unsigned long b);

bool multiply(unsigned long *result, unsigned long a, unsigned long b);

bool readLongNumber(FILE *fp, unsigned long *result);