/**
 * @file map.c 
 * @author Muzamani Gausi (mhgausi)
 * File for the processing of map input files
 */
#include "map.h"
#include "numbers.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <limits.h>
/** Maximum number of locations. */
#define LOC_LIMIT 1000
/** Max length of location names */
#define LOC_NAME_LENGTH 21
/** Array of all tracks in the file */
static char tracks[TRACK_LIMIT][2][LOC_NAME_LENGTH]; 
/** Array of 499500 or less distances */
static unsigned long distances[TRACK_LIMIT];
/** Array of locations */
static char locations[LOC_LIMIT][LOC_NAME_LENGTH];
/** Number of locations */
static int locationCount;
/** Number of tracks */
static int trackCount; 

/** Maximum number of tracks. */
#define TRACK_LIMIT ( LOC_LIMIT * ( LOC_LIMIT - 1 ) / 2 )

/**
 * Whether location is already contained in array
 * @param locations array of locations with limited length
 * @param size size of array
 * @param target string being checked 
 */
bool contains(char locations[][LOC_NAME_LENGTH], int size, const char* target) {
    for (int i = 0; i < size; i++) {
        if (strcmp(locations[i], target) == 0) {
            return true;
        }
    }
    return false;
}

/**
 * Reads the map from the given file and stores the tracks between locations.
 * 
 * @param filename name of the file with map data
 */
void readMap(char const *filename) {
    FILE *fp = fopen(filename, "r");
    if (!fp) {
        fprintf(stderr, "Invalid map file: %s\n", filename);
        exit(EXIT_FAILURE);
    }
    char start[21];
    char end[21];
    unsigned long dist;
    while (fscanf(fp, "%20s %20s", start, end) == 2) {
        if (!readDistance(fp, &dist) || strcmp(start, end) == 0 || dist > ULONG_MAX) {
            fprintf(stderr, "Invalid map file: %s\n", filename);
            fclose(fp);
            exit(EXIT_FAILURE);
        }
        for (int i = 0; i < trackCount; i++) {
            if ((strcmp(tracks[i][0], start) == 0 && strcmp(tracks[i][1], end) == 0) ||
                (strcmp(tracks[i][0], end) == 0 && strcmp(tracks[i][1], start) == 0)) {
                fprintf(stderr, "Invalid map file: %s\n", filename);
                fclose(fp);
                exit(EXIT_FAILURE);
            }
        }

        //Checks if the start location is in the array of locations
        if (!contains(locations, (sizeof(locations) / sizeof(locations[0])), start)) {
            if (locationCount >= LOC_LIMIT) {
                fprintf(stderr, "Invalid map file: %s\n", filename);
                exit(EXIT_FAILURE);
            }
            strncpy(locations[locationCount], start, LOC_NAME_LENGTH - 1);
            locations[locationCount][LOC_NAME_LENGTH - 1] = '\0'; 
            locationCount++;
        }
        //Checks if the end location is in the array of locations
        if (!contains(locations, (sizeof(locations) / sizeof(locations[0])), end)) {
            if (locationCount >= LOC_LIMIT) {
                exit(EXIT_FAILURE);
            }
            strncpy(locations[locationCount], end, LOC_NAME_LENGTH - 1);
            locations[locationCount][LOC_NAME_LENGTH - 1] = '\0'; 
            locationCount++;
        }
        if (trackCount >= TRACK_LIMIT) {
            fprintf(stderr, "Invalid map file: %s\n", filename);
            exit(EXIT_FAILURE);
        }
        distances[trackCount] = dist;
        strncpy(tracks[trackCount][0], start, LOC_NAME_LENGTH - 1);
        strncpy(tracks[trackCount][1], end, LOC_NAME_LENGTH - 1);
        trackCount++;
    }
    fclose(fp);
}

/**
 * Gets the distance between two locations
 * 
 * @param dist pointer to store the distance 
 * @param src pointer to source 
 * @param dest pointer to destination location
 * @return boolean true or false for whether distance was found
 */
bool getDistance(unsigned long *dist, char const *src, char const *dest) {
    int srcIndex = -1;
    int destIndex = -1;
    
    for (int i = 0; i < locationCount; i++) {
        if (strcmp(locations[i], src) == 0) {
            srcIndex = i; 
        }
        if (strcmp(locations[i], dest) == 0) {
            destIndex = i; 
        }
    }
    if (srcIndex == -1 || destIndex == -1) {
        return false;
    }
    for (int i = 0; i < trackCount; i++) {
        if ((strcmp(tracks[i][0], src) == 0 && strcmp(tracks[i][1], dest) == 0) ||
            (strcmp(tracks[i][0], dest) == 0 && strcmp(tracks[i][1], src) == 0)) {
            *dist = distances[i]; 
            return true; 
        }
    }
    return false;
}