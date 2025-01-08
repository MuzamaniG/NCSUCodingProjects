/**
 * @file crack.c
 * @author Muzamani Gausi (mhgausi)
 * Performs a dictionary attack on hashed passwords in a shadow file.
 * It reads a dictionary of potential passwords and attempts to match each dictionary word
 * with the hashed passwords in the shadow file. Matching usernames and passwords are printed to the console.
 */

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#include "password.h"

/** Maximum username length */
#define USERNAME_LIMIT 32

/** Maximum number of words we can have in the dictionary. */
#define DLIST_LIMIT 1000

/** Number of required arguments on the command line. */
#define REQ_ARGS 2

/** Type for representing a word in the dictionary. */
typedef char Password[ PW_LIMIT + 1 ];

/** Maximum password length as per PW_LIMIT in password.h */
#define PW_LIMIT 15

/** Type for representing a username */
typedef char Username[USERNAME_LIMIT + 1];

/** Print out a usage message and exit unsuccessfully. */
static void usage()
{
  fprintf( stderr, "Usage: crack dictionary-filename shadow-filename\n" );
  exit( EXIT_FAILURE );
}

/**
 * Reads a line from the given file pointer.
 * 
 * @param fp File pointer from which to read a line
 * @return dynamically allocated string containing the line or null if end of file
 */
char *readLine(FILE *fp) 
{
    int bufferSize = 128; 
    int position = 0;
    char *buffer = malloc(bufferSize);  
    if (buffer == NULL) 
    {
        fprintf(stderr, "Memory allocation error\n");
        exit(EXIT_FAILURE);
    }
    int ch;
    while ((ch = fgetc(fp)) != EOF && ch != '\n') 
    {
        buffer[position++] = ch;
        if (position >= bufferSize) 
        {
            bufferSize *= 2;  
            buffer = realloc(buffer, bufferSize);
            if (buffer == NULL) 
            {
                fprintf(stderr, "Memory allocation error\n");
                exit(EXIT_FAILURE);
            }
        }
    }

    if (position == 0 && ch == EOF) 
    {
        free(buffer);
        return NULL;
    }

    buffer[position] = '\0';  
    return buffer;
}

/**
 * Loads dictionary words from a file
 * 
 * @param filename The name of the file containing dictionary words
 * @param dictionary Array to store dictionary words
 * @return The number of dictionary words loaded
 */
static int loadDictionary(const char *filename, Password dictionary[DLIST_LIMIT]) 
{
    FILE *file = fopen(filename, "r");
    if (!file) {
        perror(filename);
        exit(EXIT_FAILURE);
    }

    int count = 0;
    char *line;
    while ((line = readLine(file)) && count < DLIST_LIMIT) {
        if (strlen(line) > PW_LIMIT) {
            fprintf(stderr, "Invalid dictionary word\n");
            free(line);
            fclose(file);
            exit(EXIT_FAILURE);
        }
        strcpy(dictionary[count++], line);
        free(line);
    }
    if (count >= DLIST_LIMIT && line) {
        fprintf(stderr, "Too many dictionary words\n");
        free(line);  
        fclose(file);
        exit(EXIT_FAILURE);
    }
    fclose(file);
    return count;
}


/**
 * Parses entries from the shadow file
 * 
 * @param filename name of the shadow file
 * @param usernames Array to store usernames
 * @param salts Array to store salts
 * @param hashes Array to store hashed passwords
 * @return number of users processed
 */
static int parseShadowFile(const char *filename, Username usernames[DLIST_LIMIT],
                           char salts[DLIST_LIMIT][SALT_LENGTH + 1],
                           char hashes[DLIST_LIMIT][PW_HASH_LIMIT + 1]) 
{
    FILE *file = fopen(filename, "r");
    if (!file) {
        perror(filename);
        exit(EXIT_FAILURE);
    }
    char *line;
    int count = 0;
    while ((line = readLine(file)) && count < DLIST_LIMIT) {
        if (sscanf(line, "%32[^:]:$1$%8[^$]$%22[^:]", usernames[count], salts[count], hashes[count]) != 3) {
            fprintf(stderr, "Invalid shadow file entry\n");
            free(line);
            fclose(file);
            exit(EXIT_FAILURE);
        }
        count++;
        free(line);
    }
    fclose(file);
    return count;
}

/**
 * Main function to perform the dictionary attack
 * 
 * @param argc number of arguments
 * @param argv array of arguments
 * @return 0 on exit success
 */
int main(int argc, char *argv[]) 
{
    if (argc != REQ_ARGS + 1) { 
        usage();
    }
    Password dictionary[DLIST_LIMIT];
    Username usernames[DLIST_LIMIT];
    char salts[DLIST_LIMIT][SALT_LENGTH + 1];
    char hashes[DLIST_LIMIT][PW_HASH_LIMIT + 1];
    int dictCount = loadDictionary(argv[1], dictionary);
    int userCount = parseShadowFile(argv[2], usernames, salts, hashes);
    for (int i = 0; i < userCount; i++) {
        bool found = false;
        for (int j = 0; j < dictCount && !found; j++) {
            char computedHash[PW_HASH_LIMIT + 1];
            hashPassword(dictionary[j], salts[i], computedHash);
            if (strcmp(computedHash, hashes[i]) == 0) {
                fprintf(stdout, "%s : %s\n", usernames[i], dictionary[j]);
                found = true;
            }
        }
    }
    return EXIT_SUCCESS;
}
