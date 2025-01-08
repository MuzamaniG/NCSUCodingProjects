/**
 * @file map.h 
 * @author Muzamani Gausi (mhgausi)
 * Header for Map struct
*/

#ifndef MAP_H
#define MAP_H

#include "value.h"
#include <stdbool.h>

/** Incomplete type for the Map representation. */
typedef struct MapStruct Map;

// Maximum length of a key.
#define KEY_LIMIT 24

/** 
 * Creates a new map with the specified length
 * @param len number of elements in the map
 * @return a pointer to the map
 */
Map *makeMap(int len);

/** 
 * Returns the number of key/value pairs in the map
 * @param m the map
 * @return size of the map
 */
int mapSize(Map *m);

/** 
 * Sets a key/value pair in the map
 * @param m the map
 * @param key the key for the value
 * @param val the value to associate with the key
 */
void mapSet(Map *m, char const *key, Value *val);

/** 
 * Retrieves the value associated with the given key.
 * @param m the map
 * @param key key to look up
 * @return the value associated with the key
 */
Value *mapGet(Map *m, char const *key);

/** 
 * Removes a key/value pair from the map
 * @param m the map
 * @param key key to remove
 * @return true if key was successfully removed, false otherwise
 */
bool mapRemove(Map *m, char const *key);

/** 
 * Frees all memory used by the map
 * @param m the map being freed
 */
void freeMap(Map *m);

#endif
