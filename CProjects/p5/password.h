/**
 * @file password.h
 * @author Muzamani Gausi (mhgausi)
 * Header for password.c
 */

#ifndef _PASSWORD_H_
#define _PASSWORD_H_

/** Required length of the salt string. */
#define SALT_LENGTH 8

/** Maximum length of a password.  Just to simplify our program; passwords
    aren't really required to be this short. */
#define PW_LIMIT 15

/** Maximum length of a password hash string created by hashPassword() */
#define PW_HASH_LIMIT 22

/**
 * Hashes a password with a given salt
 * 
 * @param pass password string
 * @param salt salt string
 * @param result output string for the final hashed password
 */
void hashPassword(char const pass[], char const salt[SALT_LENGTH + 1], char result[PW_HASH_LIMIT + 1]);

#endif
