/**
 * @file transit.c
 * @author Muzamani Gausi (mhgausi)
 * Main file for processing maps and routes for given trips
 */
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#include "numbers.h"
#include "map.h"

/** Number of required arguments on the command line. */
#define REQ_ARGS 2

void readMap(char const *filename);
bool getDistance(unsigned long *dist, char const *src, char const *dest);

/**
   Report a usage message and exit unsuccessfully.
*/
static void usage()
{
  fprintf( stderr, "Usage: transit [-v] [-r report-filename] map-filename route-filename\n" );
  exit( EXIT_FAILURE );
}

/**
 * Processes routes from file
 * @param filename name of file being processed
 * @param outfile file to receive output 
 * @param verbose whether verbose tag is added
 */
void processRoute( char const *filename, FILE *outfile, bool verbose ) 
{
    FILE *routeFile = fopen(filename, "r");
    if (routeFile == NULL) {
        fprintf(stderr, "Can't open file: %s\n", filename);
        exit(EXIT_FAILURE);
    }
    if (verbose) {
        fprintf(outfile, "%-20s %-20s %20s %21s %21s\n", 
        "Depart", "Arrive", "Passengers", "Distance", "Passenger Distance");
    }
    else {
        fprintf(outfile, "%-20s %-20s %21s\n", "Depart", "Arrive", "Passenger Distance");
    }
    char location[LOC_NAME_LENGTH]; // for deciding whether it is depart or arrive
    char departLocation[LOC_NAME_LENGTH];
    char arriveLocation[LOC_NAME_LENGTH];
    bool firstLoc = true;
    unsigned long dropOff = 0;
    unsigned long pickUp = 0;
    unsigned long distance;
    unsigned long totalPassengerDistance = 0;
    unsigned long totalDistance = 0;
    unsigned long currentPassengers = 0;
    unsigned long totalPassengers = 0;
    bool result;
    while (fscanf(routeFile, "%s", location) == 1) {  // Read location
        // Read dropOff and pickUp using readLongNumber
        if (!readLongNumber(routeFile, &dropOff)) {
            fprintf(stderr, "Invalid route file: %s\n", filename);
            exit(EXIT_FAILURE);
        }

        if (!readLongNumber(routeFile, &pickUp)) {
            fprintf(stderr, "Invalid route file: %s\n", filename);
            exit(EXIT_FAILURE);
        }

        if (firstLoc) {
            // First location is departLocation
            strncpy(departLocation, location, LOC_NAME_LENGTH - 1);
            departLocation[LOC_NAME_LENGTH - 1] = '\0';  // Null-terminate
            currentPassengers = pickUp;
            firstLoc = false;
        } 
        else {
            // Second location is arriveLocation
            strncpy(arriveLocation, location, LOC_NAME_LENGTH - 1);
            arriveLocation[LOC_NAME_LENGTH - 1] = '\0';
            result = getDistance(&distance, departLocation, arriveLocation);

            if (!add(&totalDistance, totalDistance, distance)) {
                fprintf(stderr, "Invalid distance\n");
                exit(EXIT_FAILURE);
            }
            unsigned long passengerDistance = distance;
            if (!multiply(&distance, distance, currentPassengers)) {
                fprintf(stderr, "Failed multiplication\n");
                exit(EXIT_FAILURE);
            }
            unsigned long previousPassengers = currentPassengers;
            if (!add(&totalPassengerDistance, distance, totalPassengerDistance)) {
                fprintf(stderr, "Invalid passenger distance\n");
                exit(EXIT_FAILURE);
            }
            if (!subtract(&currentPassengers, currentPassengers, dropOff) || !add(&currentPassengers, currentPassengers, pickUp)) {
                fprintf(stderr, "Invalid number of passengers (arithmetic)\n");
                exit(EXIT_FAILURE);
            }
            if (!add(&totalPassengers, totalPassengers, previousPassengers)) {
                fprintf(stderr, "Invalid passenger distance\n");
                exit(EXIT_FAILURE);
            }
            if (result) {
                if (verbose) {
                    fprintf(outfile, "%-20s %-20s %20lu %19lu.%lu %19lu.%lu\n",
                    departLocation, arriveLocation, previousPassengers, 
                    passengerDistance / 10, passengerDistance % 10, distance / 10, distance % 10); 
                }
                else {
                    fprintf(outfile, "%-20s %-20s%20lu.%lu\n", departLocation, arriveLocation, distance / 10, distance % 10);
                }
            } 
            else {
                fprintf(outfile, "No route found between %s and %s\n", departLocation, arriveLocation);
            }
            strncpy(departLocation, arriveLocation, LOC_NAME_LENGTH - 1);
            departLocation[LOC_NAME_LENGTH - 1] = '\0';
        }
    
    }
    if (verbose) {
        fprintf(outfile, "%-40s %21lu%20lu.%lu%20lu.%lu\n", 
        "Total", totalPassengers, totalDistance / 10, totalDistance % 10, 
        totalPassengerDistance / 10, totalPassengerDistance % 10);
    }
    else  {
        fprintf(outfile, "%-40s %20lu.%lu\n", "Total", totalPassengerDistance / 10, totalPassengerDistance % 10);
    }
    fclose(outfile);
    fclose(routeFile);
}

/**
 * Main method for processing trips
 * @param arc number of command line arguments
 * @param argv pointer to command line arguments array
 */
int main(int argc, char *argv[]) 
{
    bool verbose = false;
    bool reportFileFlag = false;
    const char *mapFile = NULL;
    const char *routeFile = NULL;
    const char *reportFile = NULL;
    FILE *outfile = stdout;  
    if (argc < 3) {
        usage();  
    }
    int i = 1;
    while (i < argc && argv[i][0] == '-') {  
        if (strcmp(argv[i], "-v") == 0) {
            verbose = true;
            i++;
        } else if (strcmp(argv[i], "-r") == 0) {
            if (i + 1 >= argc || reportFileFlag) {  
                usage();
            }
            reportFileFlag = true;
            reportFile = argv[++i]; 
            i++;
        } else {
            usage();
        }
    }
    if (i + 1 >= argc) {  
        usage();
    }
    mapFile = argv[i];
    routeFile = argv[i + 1];

    if (reportFile != NULL) {
        outfile = fopen(reportFile, "w");
        if (outfile == NULL) {
            fprintf(stderr, "Can't open file: %s\n", reportFile);
            return EXIT_FAILURE;
        }
    }
    readMap(mapFile);
    processRoute(routeFile, outfile, verbose);

    if (outfile != stdout) {
        fclose(outfile);
    }

    return EXIT_SUCCESS;
}


