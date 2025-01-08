/**
 * @file group.c 
 * @author Muzamani Gausi (mhgausi)
 * This file defines the structures and functions needed to manage a group of members and the items they sell
 * It also allows for dynamically resizable arrays to store members and items with their sales.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "input.h"  

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

int compareSaleItemsById(const void *a, const void *b);

/**
 * Dynamically allocates storage for the Group, initializes the resizable 
 * arrays for items and members with an initial capacity of 5, and returns a pointer to the new Group
 * 
 * @return pointer to the created Group
 */
Group *makeGroup() 
{
    Group *group = (Group *)malloc(sizeof(Group));
    if (!group) {
        fprintf(stderr, "Memory allocation error for Group\n");
        exit(1);
    }

    group->iCount = 0;
    group->iCap = 5;
    group->iList = (Item **)malloc(group->iCap * sizeof(Item *));
    if (!group->iList) {
        fprintf(stderr, "Memory allocation error for Item array\n");
        exit(EXIT_FAILURE);
    }

    group->mCount = 0;
    group->mCap = 5;
    group->mList = (Member **)malloc(group->mCap * sizeof(Member *));
    if (!group->mList) {
        fprintf(stderr, "Memory allocation error for Member array\n");
        exit(EXIT_FAILURE);
    }

    return group;
}

/**
 * Frees the memory used by a Group structure
 * 
 * @param group pointer to the Group to be freed
 */
void freeGroup(Group *group) 
{
    for (int i = 0; i < group->iCount; ++i) {
        free(group->iList[i]); 
    }
    free(group->iList);  
    for (int i = 0; i < group->mCount; ++i) {
        Member *member = group->mList[i];
        for (int j = 0; j < member->count; ++j) {
            free(member->list[j]);  
        }
        free(member->list);  
        free(member); 
    }
    free(group->mList); 
    free(group);  
}

/**
 * Reads items from the specified file using the readLine() function,
 * creates an instance of the Item struct for each item, and stores a pointer to that
 * Item in the item array in the Group
 * 
 * @param filename name of the file being read from
 * @param group pointer to the Group where the items are stored
 */
void readItems(char const *filename, Group *group) 
{
    FILE *fp = fopen(filename, "r");
    if (!fp) {
        fprintf(stderr, "Can't open file: %s\n", filename);
        exit(EXIT_FAILURE);
    }

    char *line;
    while ((line = readLine(fp)) != NULL) {
        if (group->iCount >= group->iCap) {
            group->iCap *= 2;
            group->iList = (Item **)realloc(group->iList, group->iCap * sizeof(Item *));
            if (!group->iList) {
                fprintf(stderr, "Memory allocation error during item resizing\n");
                exit(EXIT_FAILURE);
            }
        }

        Item *item = (Item *)malloc(sizeof(Item));
        if (!item) {
            fprintf(stderr, "Memory allocation error for Item\n");
            free(line);
            fclose(fp);
            exit(EXIT_FAILURE);
        }

        if (sscanf(line, "%d %d %[^\n]", &item->id, &item->cost, item->name) != 3) {
            fprintf(stderr, "Invalid item file: %s\n", filename);
            free(item);
            free(line);
            fclose(fp);
            exit(EXIT_FAILURE);
        }
        if (item->cost < 0) {
            fprintf(stderr, "Invalid item file: %s\n", filename);
            exit(EXIT_FAILURE);
        }
        item->numSold = 0; 
        group->iList[group->iCount++] = item;

        free(line);
    }
    fclose(fp);
}

/**
 * Reads members from the specified file using the readLine() function,
 * creates an instance of the Member struct for each member, and stores a pointer to that
 * Member in the resizable member array in the Group
 * 
 * @param filename name of the file being read from
 * @param group pointer to the Group where the members are stored
 */
void readMembers(char const *filename, Group *group) 
{
    FILE *fp = fopen(filename, "r");
    if (!fp) {
        fprintf(stderr, "Can't open file: %s\n", filename);
        exit(EXIT_FAILURE);
    }
    char *line;
    while ((line = readLine(fp)) != NULL) {
        if (group->mCount >= group->mCap) {
            group->mCap *= 2;
            group->mList = (Member **)realloc(group->mList, group->mCap * sizeof(Member *));
            if (!group->mList) {
                fprintf(stderr, "Memory allocation error during member resizing\n");
                exit(EXIT_FAILURE);
            }
        }
        Member *member = (Member *)malloc(sizeof(Member));
        if (!member) {
            fprintf(stderr, "Memory allocation error for Member\n");
            free(line);
            fclose(fp);
            exit(EXIT_FAILURE);
        }

        if (sscanf(line, "%s %[^\n]", member->id, member->name) != 2) {
            fprintf(stderr, "Invalid member file: %s\n", filename);
            free(member);
            free(line);
            fclose(fp);
            exit(EXIT_FAILURE);
        }
        if (strlen(member->name) >= 30) {
            fprintf(stderr, "Invalid member file: %s\n", filename);
            free(member);
            free(line);
            fclose(fp);
            exit(EXIT_FAILURE);  
        }
        for (int i = 0; i < group->mCount; ++i) {
            if (strcmp(group->mList[i]->id, member->id) == 0) {
                fprintf(stderr, "Invalid member file: %s\n", filename);
                free(member);
                free(line);
                fclose(fp);
                exit(EXIT_FAILURE);  
            }
        }
        member->list = (SaleItem **)malloc(5 * sizeof(SaleItem *));
        if (!member->list) {
            fprintf(stderr, "Memory allocation error for SaleItem list\n");
            free(member);
            free(line);
            fclose(fp);
            exit(EXIT_FAILURE);
        }
        member->count = 0;
        member->capacity = 5;
        group->mList[group->mCount++] = member;
        free(line);
    }

    fclose(fp);
}

/**
 * Sorts the items in the Group using the qsort() function
 * 
 * @param group pointer to the Group whose items are being sorted
 * @param compare pointer to the comparison function.
 */
void sortItems(Group *group, int (*compare)(void const *va, void const *vb)) 
{
    qsort(group->iList, group->iCount, sizeof(Item *), compare);
}

/**
 * Sorts the members in the Group using the qsort() function
 * 
 * @param group pointer to the Group whose members are being sorted
 * @param compare pointer to the comparison function
 */
void sortMembers(Group *group, int (*compare)(void const *va, void const *vb)) 
{
    qsort(group->mList, group->mCount, sizeof(Member *), compare);
}

/**
 * Function responsible for printing the list of items 
 * 
 * @param group pointer to the Group whose items are to be listed
 * @param test pointer to the test function used for filtering
 * @param str string for searching and filtering 
 */
void listItems(Group *group, bool (*test)(Item const *item, char const *str), char const *str) 
{
    printf("ID  Name                             Cost   Sold  Total\n");

    int totalSold = 0;
    int totalRevenue = 0;
    for (int i = 0; i < group->iCount; ++i) {
        Item *item = group->iList[i];
        if (test(item, str)) {
            int totalCost = item->numSold * item->cost;
            printf("%-3d %-30s %6d %6d %6d\n", item->id, item->name, item->cost, item->numSold, totalCost);
            totalSold += item->numSold;
            totalRevenue += totalCost;
        }
    }
    printf("TOTAL                                     %6d %6d\n", totalSold, totalRevenue);
}

/**
 * Lists the items sold by the specified member
 * 
 * @param group pointer to group structure containing members and items
 * @param memberId The ID of the member whose sales to list
 */
void listItemsByMember(Group *group, const char *memberId) 
{
    Member *member = NULL;
    for (int i = 0; i < group->mCount; ++i) {
        if (strcmp(group->mList[i]->id, memberId) == 0) {
            member = group->mList[i];
            break;
        }
    }
    if (!member) {
        printf("Invalid command\n");
        return;
    }
    qsort(member->list, member->count, sizeof(SaleItem *), compareSaleItemsById);
    printf("ID  Name                             Cost   Sold  Total\n");

    int totalSold = 0;
    int totalRevenue = 0;

    for (int i = 0; i < member->count; ++i) {
        SaleItem *currentSale = member->list[i];
        int itemSold = currentSale->numSold;
        int itemTotalCost = currentSale->numSold * currentSale->item->cost;
        while (i + 1 < member->count && currentSale->item->id == member->list[i + 1]->item->id) {
            i++;
            itemSold += member->list[i]->numSold;
            itemTotalCost += member->list[i]->numSold * member->list[i]->item->cost;
        }
        printf("%-3d %-30s %6d %6d %6d\n",
               currentSale->item->id,
               currentSale->item->name,
               currentSale->item->cost,
               itemSold,
               itemTotalCost);

        totalSold += itemSold;
        totalRevenue += itemTotalCost;
    }

    printf("TOTAL                                     %6d %6d\n", totalSold, totalRevenue);
}

/**
 * Prints members based on a test function that filters the output.
 * 
 * @param group pointer to the Group whose members are to be listed
 * @param test pointer to the test function used for filtering
 * @param str Optional string for searching and filtering 
 */
void listMembers(Group *group, bool (*test)(Member const *member, char const *str), char const *str) 
{
    printf("ID       Name                             Sold  Total\n");
    int totalSold = 0;
    int totalRevenue = 0;
    for (int i = 0; i < group->mCount; ++i) {
        Member *member = group->mList[i];
        if (test(member, str)) {
            int memberSold = 0;
            int memberTotal = 0;
            for (int j = 0; j < member->count; ++j) {
                SaleItem *sale = member->list[j];
                memberSold += sale->numSold;
                memberTotal += sale->numSold * sale->item->cost;
            }
            printf("%-8s %-30s %6d %6d\n", member->id, member->name, memberSold, memberTotal);
            totalSold += memberSold;
            totalRevenue += memberTotal;
        }
    }
    printf("TOTAL                                   %6d %6d\n", totalSold, totalRevenue);
}

