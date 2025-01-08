/**
 * @file map.c 
 * @author Muzamani Gausi (mhgausi)
 * File for Map struct, including getters/setters, size, etc.
*/
#include "map.h"
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/** Node containing a key / value pair. */
typedef struct NodeStruct {
    /** String key for this map entry. */
    char key[ KEY_LIMIT + 1 ];
    
    /** Pointer to the value part of the key / value pair. */
    Value *val;
    
    /** Pointer to the next node at the same element of this table. */
    struct NodeStruct *next;
} Node;

/** Representation of a hash table implementation of a map. */
struct MapStruct {
    /** Table of key / value pairs. */
    Node **table;

    /** Current length of the table. */
    int tlen;
    
    /** Current size of the map (number of different keys). */
    int size;
};


/** 
 * Hash function for generating an index for a given key. 
 * @param key the given key
 * @param tlen length of hashtable
 * @return int hashcode for index of key
 */
static int hash(const char *key, int tlen) 
{
    int hash = 0;
    while (*key) {
        hash = (hash * 31 + *key++) % tlen; 
    }
    return hash;
}

Map *makeMap(int len) 
{
    Map *map = malloc(sizeof(Map));
    if (!map) {
        return NULL;
    }
    map->table = calloc(len, sizeof(Node *));
    if (!map->table) {
        free(map);
        return NULL;
    }
    map->tlen = len;
    map->size = 0;
    return map;
}

int mapSize(Map *m) 
{
    return m->size;
}

void mapSet(Map *m, char const *key, Value *val) 
{
    if (!m || !key || !val) return;

    int index = hash(key, m->tlen);
    Node *current = m->table[index];
  
    while (current) {
        if (strcmp(current->key, key) == 0) {
            free(current->val); 
            current->val = val; 
            return;
        }
        current = current->next;
    }
    Node *newNode = malloc(sizeof(Node));
    if (!newNode) return;

    strncpy(newNode->key, key, KEY_LIMIT);
    newNode->key[KEY_LIMIT] = '\0'; 
    newNode->val = val;
    newNode->next = m->table[index];
    m->table[index] = newNode;
    m->size++;
}

Value *mapGet(Map *m, char const *key) 
{
    if (!m || !key) return NULL;

    int index = hash(key, m->tlen);
    Node *current = m->table[index];

    while (current) {
        if (strcmp(current->key, key) == 0) {
            return current->val;
        }
        current = current->next;
    }
    return NULL; 
}

bool mapRemove(Map *m, char const *key) 
{
    if (!m || !key) return false;

    int index = hash(key, m->tlen);
    Node *current = m->table[index];
    Node *prev = NULL;

    while (current) {
        if (strcmp(current->key, key) == 0) {
            if (prev) {
                prev->next = current->next;
            } else {
                m->table[index] = current->next;
            }
            free(current->val); 
            free(current);     
            m->size--;
            return true;
        }
        prev = current;
        current = current->next;
    }
    return false;
}

void freeMap(Map *m) 
{
    if (!m) return;
    for (int i = 0; i < m->tlen; i++) {
        Node *current = m->table[i];
        while (current) {
            Node *next = current->next;
            free(current->val);  
            free(current);       
            current = next;
        }
    }
    free(m->table); 
    free(m);        
}