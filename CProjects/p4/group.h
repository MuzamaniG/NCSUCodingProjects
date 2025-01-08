/**
 * @file group.h
 * @author Muzamani Gausi (mhgausi)
 * Header for the group file
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/** 
 * Structure representing an Item
 */
typedef struct 
{
    /** Item ID */
    int id;              
    /** Item cost */
    int cost;            
    /** Item name */
    char name[31];      
    /** Number of items sold */
    int numSold;         
} Item;

/** 
 * Structure representing a SaleItem
 */
typedef struct 
{
    /** Pointer to the item */
    Item *item;    
    /** Number of items sold by the member */      
    int numSold;         
} SaleItem;

/** 
 * Structure representing a Member
 */
typedef struct 
{
    /** Member ID */
    char id[9];    
    /** Member name */      
    char name[31];   
    /** Dynamic array of SaleItem pointers */    
    SaleItem **list;  
    /** Number of SaleItems */   
    int count;   
    /** Capacity of the SaleItem array */        
    int capacity;        
} Member;

/** 
 * Structure representing a Group
 */
typedef struct 
{
    /** Dynamic array of Item pointers */
    Item **iList; 
    /** Number of Items */       
    int iCount;    
    /** Capacity of the Item array */      
    int iCap;          
    /** Dynamic array of Member pointers */  
    Member **mList;  
    /** Number of Members */    
    int mCount; 
    /** Capacity of the Member array */         
    int mCap;            
} Group;

/**
 * Dynamically allocates storage for the Group, initializes the resizable 
 * arrays for items and members with an initial capacity of 5, and returns a pointer to the new Group
 * 
 * @return pointer to the created Group
 */
Group *makeGroup(void);

/**
 * Frees the memory used by a Group structure
 * 
 * @param group pointer to the Group to be freed
 */
void freeGroup(Group *group);

/**
 * Reads items from the specified file using the readLine() function,
 * creates an instance of the Item struct for each item, and stores a pointer to that
 * Item in the item array in the Group
 * 
 * @param filename name of the file being read from
 * @param group pointer to the Group where the items are stored
 */
void readItems(char const *filename, Group *group);

/**
 * Reads members from the specified file using the readLine() function,
 * creates an instance of the Member struct for each member, and stores a pointer to that
 * Member in the resizable member array in the Group
 * 
 * @param filename name of the file being read from
 * @param group pointer to the Group where the members are stored
 */
void readMembers(char const *filename, Group *group);

/**
 * Sorts the items in the Group using the qsort() function
 * 
 * @param group pointer to the Group whose items are being sorted
 * @param compare pointer to the comparison function.
 */
void sortItems(Group *group, int (*compare)(void const *va, void const *vb));

/**
 * Sorts the members in the Group using the qsort() function
 * 
 * @param group pointer to the Group whose members are being sorted
 * @param compare pointer to the comparison function
 */
void sortMembers(Group *group, int (*compare)(void const *va, void const *vb));

/**
 * Function responsible for printing the list of items 
 * 
 * @param group pointer to the Group whose items are to be listed
 * @param test pointer to the test function used for filtering
 * @param str string for searching and filtering 
 */
void listItems(Group *group, bool (*test)(Item const *item, char const *str), char const *str);

/**
 * Prints members based on a test function that filters the output.
 * 
 * @param group pointer to the Group whose members are to be listed
 * @param test pointer to the test function used for filtering
 * @param str Optional string for searching and filtering 
 */
void listMembers(Group *group, bool (*test)(Member const *member, char const *str), char const *str);

/**
 * Lists the items sold by the specified member
 * 
 * @param group pointer to group structure containing members and items
 * @param memberId The ID of the member whose sales to list
 */
void listItemsByMember(Group *group, char const *memberId);


