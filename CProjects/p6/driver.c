/**
 * @file driver.c
 * @author Muzamani Gausi (mhgausi)
 * File for processing and handling commands 
*/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <setjmp.h>
#include <unistd.h> // Unix-specific isatty() function.

#include "map.h"
#include "value.h"
#include "input.h"

/** Print out a usage message and exit unsuccessfully. */
static void usage()
{
    fprintf( stderr, "Usage: driver [-term]\n" );
    exit( EXIT_FAILURE );
}

/**
 * Parses and handles commands from the input line
 * Updates the map or prints output based on the command
 * @param map being modified
 * @param line line being processed
 * @param env buffer for longjump
 * @return true if command is quit, false otherwise
 */
bool handleCommand(Map *map, char const *line, jmp_buf *env) 
{
    char command[32];
    char key[KEY_LIMIT + 1];
    char valueStr[128];
    int numArgs;

    numArgs = sscanf(line, "%31s", command);
    if (numArgs < 1) {
        longjmp(*env, 1); 
    }
    if (strcmp(command, "quit") == 0) {
        return true; 
    } else if (strcmp(command, "size") == 0) {
        if (sscanf(line, "%31s %s", command, key) == 2) {
            longjmp(*env, 1); 
        }
        printf("%d\n", mapSize(map));
    } else if (strcmp(command, "set") == 0) {
        numArgs = sscanf(line, "%31s %24s %127[^\n]", command, key, valueStr);
        if (numArgs != 3) {
            longjmp(*env, 1); 
        }
        Value *value = parseInteger(valueStr);
        if (!value) value = parseDouble(valueStr);
        if (!value) value = parseString(valueStr);
        if (!value) {
            longjmp(*env, 1); 
        }
        mapSet(map, key, value);
    } else if (strcmp(command, "get") == 0) {
        numArgs = sscanf(line, "%31s %24s", command, key);
        if (numArgs != 2) {
            longjmp(*env, 1); 
        }

        Value *value = mapGet(map, key);
        if (!value) {
            longjmp(*env, 1); 
        }
        value->print(value);
        printf("\n");
    } else if (strcmp(command, "remove") == 0) {
        numArgs = sscanf(line, "%31s %24s", command, key);
        if (numArgs != 2) {
            longjmp(*env, 1); 
        }
        if (!mapRemove(map, key)) {
            longjmp(*env, 1); 
        }
    } else {
        longjmp(*env, 1); 
    }
    return false; 
}

/**
   Starting point for the program.
   @param argc number of command-line arguments.
   @param argv array of strings given as command-line arguments.
   @return exit status for the program.
 */
int main(int argc, char *argv[]) 
{
    // See if our input is from a terminal.
    bool interactive = isatty(STDIN_FILENO);

    // Parse command-line arguments.
    int apos = 1;
    while (apos < argc) {
        // The -term option makes the program behave as if it's in interactive mode,
        // even if it's reading from a file.
        if (strcmp(argv[apos], "-term") == 0) {
            interactive = true;
            apos++;
        } else {
            usage();
        }
    }

    int tableLength = 128;
    Map *map = makeMap(tableLength);
    if (!map) {
        fprintf(stderr, "Error: Unable to create map.\n");
        exit(EXIT_FAILURE);
    }
    char *line = NULL;
    jmp_buf env;
    if (setjmp(env) != 0) {
        if (interactive) {
            printf("Invalid command\n"); 
        } else {
            fprintf(stderr, "Invalid command: %s\n", line);
            free(line);
            exit(EXIT_FAILURE); 
        }
    }
    while (true) {
        if (interactive) {
            printf("cmd> ");
            fflush(stdout);
        }
        line = readLine(stdin);
        if (!line) {
            break; 
        }
        bool quit = handleCommand(map, line, &env);
        free(line);

        if (quit) {
            break;
        }
    }
    freeMap(map);
    return EXIT_SUCCESS;
}

