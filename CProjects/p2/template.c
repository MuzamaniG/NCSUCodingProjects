/**
 * @file template.c
 * @author Muzamani Gausi (mhgausi)
 * Handles the logic of the memo replacement program
 * using the input.c and replace.c classes
 */

#include <stdio.h>
#include <string.h>
#include "input.h"
#include "replace.h"
#include <stdlib.h>
#include <stdbool.h>

/** Error for incorrect pronouns */
#define PRONOUN_ERROR 102

int main() {
    char firstName[FIELD_MAX + 1], lastName[FIELD_MAX + 1], unityID[FIELD_MAX + 1];
    char pronoun1[FIELD_MAX + 1], pronoun2[FIELD_MAX + 1], pronoun3[FIELD_MAX + 1];
    char line[LINE_MAX + 1];
    readWord(firstName);
    readWord(lastName);
    readWord(unityID);

    for (int i = 0; i <= FIELD_MAX; i++) {
        pronoun1[i] = '\0';
        pronoun2[i] = '\0';
        pronoun3[i] = '\0';
    }
    int c, idx = 0;
    char tempPronouns[FIELD_MAX * 3 + 2];
    while ((c = getchar()) != '\n' && c != EOF && idx < FIELD_MAX * 3 + 2) {
        tempPronouns[idx++] = (char)c;
    }
    tempPronouns[idx] = '\0';
    int tempIdx = 0, partIdx = 0, pronounIdx = 0;
    char *pronounParts[] = {pronoun1, pronoun2, pronoun3};
    
    for (pronounIdx = 0; pronounIdx < 3; pronounIdx++) {
        partIdx = 0;
        while (tempPronouns[tempIdx] != '/' && tempPronouns[tempIdx] != '\0') {
            if (partIdx >= FIELD_MAX) {
                exit(WORD_EXIT); 
            }
            pronounParts[pronounIdx][partIdx++] = tempPronouns[tempIdx++];
        }
        pronounParts[pronounIdx][partIdx] = '\0'; 
        if (tempPronouns[tempIdx] == '/') {
            tempIdx++; 
        }
    }

    if (pronoun1[0] == '\0' || pronoun2[0] == '\0' || pronoun3[0] == '\0') {
        exit(PRONOUN_ERROR); 
    }
    while (readLine(line)) {
        replaceWord(line, FIRST, firstName);
        replaceWord(line, LAST, lastName);
        replaceWord(line, UNITY, unityID);
        replacePronoun(line, PRONOUN1, pronoun1);
        replacePronoun(line, PRONOUN2, pronoun2);
        replacePronoun(line, PRONOUN3, pronoun3);

        printf("%s\n", line);
    }

    exit(EXIT_SUCCESS);
}