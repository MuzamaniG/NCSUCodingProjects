/**
 * @file fundraiser.c 
 * @author Muzamani Gausi (mhgausi)
 * File for the comparison of items and members based on id, name, and sales,
 * as well as the file for the main method printing out information,
 * such as item sales and members associated with the fundraiser
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "group.h"
#include "input.h"

/**
 * Converts a string to lowercase
 * @param str pointer to the string to be converted
 * @return a newly allocated lowercase version of the input string
 */
char *toLowerCase(const char *str) 
{
    char *lowerStr = malloc(strlen(str) + 1);
    if (!lowerStr) {
        fprintf(stderr, "Memory allocation error in toLowerCase.\n");
        exit(EXIT_FAILURE);
    }
    for (int i = 0; str[i]; i++) {
        lowerStr[i] = tolower((unsigned char)str[i]);
    }
    lowerStr[strlen(str)] = '\0';
    return lowerStr;
}

/**
 * Returns true if the item name contains the search string in a case-insensitive way
 * @param item pointer to the item being evaluated
 * @param str pointer to the search string
 * @return boolean true or false for whether item name contains the search string, false otherwise
 */
bool itemContainsNameIgnoreCase(const Item *item, const char *str) 
{
    char *itemNameLower = toLowerCase(item->name);
    char *searchStringLower = toLowerCase(str);
    bool result = strstr(itemNameLower, searchStringLower) != NULL;
    free(itemNameLower);
    free(searchStringLower);
    return result;
}

/**
 * Returns true if the member's name contains the search string in a case-insensitive way
 * @param member pointer to the member being evaluated
 * @param str pointer to the search string
 * @return boolean true or false for whether the member's name contains the search string
 */
bool memberContainsNameIgnoreCase(const Member *member, const char *str) 
{
    char *memberNameLower = toLowerCase(member->name);
    char *searchStringLower = toLowerCase(str);
    bool result = strstr(memberNameLower, searchStringLower) != NULL;
    free(memberNameLower);
    free(searchStringLower);
    return result;
}
/**
 * Compares two items by their IDs
 * @param a pointer to first item being compared
 * @param b pointer to second item being compared
 * @return negative if itemA comes before itemB, positive if itemA comes after itemB, 0 if equal
 */
int compareItemsById(const void *a, const void *b) 
{
    const Item *itemA = *(const Item **)a;
    const Item *itemB = *(const Item **)b;
    return (itemA->id - itemB->id);
}

/**
 * Compares two items by their names
 * @param a pointer to first item being compared
 * @param b pointer to second item being compared
 * @return negative if itemA comes before itemB, positive if itemA comes after itemB, 0 if equal
 */
int compareItemsByName(const void *a, const void *b) 
{
    const Item *itemA = *(const Item **)a;
    const Item *itemB = *(const Item **)b;
    return strcmp(itemA->name, itemB->name);
}

/**
 * Compares two members by their IDs
 * @param a pointer to first member being compared
 * @param b pointer to second member being compared
 * @return negative if memberA comes before memberB, positive if memberA comes after memberB, 0 if equal
 */
int compareMembersById(const void *a, const void *b) 
{
    const Member *memberA = *(const Member **)a;
    const Member *memberB = *(const Member **)b;
    return strcmp(memberA->id, memberB->id);
}

/**
 * Compares two members by their names
 * @param a pointer to first member being compared
 * @param b pointer to second member being compared
 * @return negative if memberA comes before memberB, positive if memberA comes after memberB, 0 if equal
 */
int compareMembersByName(const void *a, const void *b) 
{
    const Member *memberA = *(const Member **)a;
    const Member *memberB = *(const Member **)b;
    return strcmp(memberA->name, memberB->name);  
}

/**
 * Compares two members by their total sales
 * @param a pointer to first member being compared
 * @param b pointer to second member being compared
 * @return negative if memberA has fewer sales, positive if memberA has more sales, 0 if equal
 */
int compareMembersByTotalSales(const void *a, const void *b) 
{
    const Member *memberA = *(const Member **)a;
    const Member *memberB = *(const Member **)b;
    int totalSalesA = 0, totalSalesB = 0;
    for (int i = 0; i < memberA->count; i++) {
        totalSalesA += memberA->list[i]->numSold * memberA->list[i]->item->cost;
    }
    for (int i = 0; i < memberB->count; i++) {
        totalSalesB += memberB->list[i]->numSold * memberB->list[i]->item->cost;
    }
    if (totalSalesB != totalSalesA) {
        return totalSalesB - totalSalesA;
    }
    return strcmp(memberA->id, memberB->id);
}

/**
 * Compares two sale items by their item ID
 * @param a pointer to first sale item being compared
 * @param b pointer to second sale item being compared
 * @return negative if saleA's item comes before saleB's item, positive if saleA's item comes after, 0 if equal
 */
int compareSaleItemsById(const void *a, const void *b) 
{
    const SaleItem *saleA = *(const SaleItem **)a;
    const SaleItem *saleB = *(const SaleItem **)b;
    return saleA->item->id - saleB->item->id;
}
/**
 * Always returns true for listing all items
 * @param item pointer to the item being evaluated
 * @param str unused parameter kept for function consistency
 * @return true, since it always returns true
 */
bool alwaysTrueItem(const Item *item, const char *str) 
{
    return true;
}

/**
 * Returns true if the item name contains the search string
 * @param item pointer to the item being evaluated
 * @param str pointer to the search string to check for in the item name
 * @return true if the item name contains the search string, false otherwise
 */
bool itemContainsName(const Item *item, const char *str) 
{
    return strstr(item->name, str) != NULL;
}

/**
 * Always returns true for listing all members
 * @param member pointer to the member being evaluated
 * @param str unused parameter kept for function consistency
 * @return true
 */
bool alwaysTrueMember(const Member *member, const char *str) 
{
    return true;
}

/**
 * Returns true if the member's name contains the search string.
 * @param member pointer to the member being evaluated
 * @param str pointer to the search string to check for in the member's name
 * @return true if the member's name contains the search string, false otherwise
 */
bool memberContainsName(const Member *member, const char *str) 
{
    return strstr(member->name, str) != NULL;
}

/**
 * Records the sale of an item by a member
 * @param group pointer to the Group containing members and items
 * @param memberId pointer to the member's ID
 * @param itemId ID of the item being sold
 * @param numSold number of items sold
 */

void recordSale(Group *group, const char *memberId, int itemId, int numSold) 
{
    Member *member = NULL;
    Item *item = NULL;
    for (int i = 0; i < group->mCount; ++i) {
        if (strcmp(group->mList[i]->id, memberId) == 0) {
            member = group->mList[i];
            break;
        }
    }
    if (!member) {
        fprintf(stderr, "Invalid command\n");
        return;
    }
    for (int i = 0; i < group->iCount; ++i) {
        if (group->iList[i]->id == itemId) {
            item = group->iList[i];
            break;
        }
    }

    if (!item) {
        fprintf(stderr, "Error: Item with ID '%d' not found.\n", itemId);
        return;
    }
    if (member->count >= member->capacity) {
        member->capacity *= 2;
        member->list = (SaleItem **)realloc(member->list, member->capacity * sizeof(SaleItem *));
        if (!member->list) {
            fprintf(stderr, "Memory allocation error during SaleItem resizing.\n");
            exit(1);
        }
    }
    SaleItem *sale = (SaleItem *)malloc(sizeof(SaleItem));
    if (!sale) {
        fprintf(stderr, "Memory allocation error for SaleItem.\n");
        exit(1);
    }
    sale->item = item;
    sale->numSold = numSold;
    member->list[member->count++] = sale;
    item->numSold += numSold;
}


/**
 * Main function for the fundraiser program.
 * @param argc number of command line arguments
 * @param argv pointer to array of command line arguments
 */
int main(int argc, char *argv[]) 
{
    if (argc != 3) {
        fprintf(stderr, "usage: fundraiser item-file member-file\n");
        return 1;
    }
    Group *group = makeGroup();
    readItems(argv[1], group);
    readMembers(argv[2], group);

    char *command = NULL;
    char buffer[256]; 

    while (1) {
        printf("cmd> ");
        if (fgets(buffer, sizeof(buffer), stdin) == NULL) {
            break;  
        }
        buffer[strcspn(buffer, "\n")] = '\0';
        command = buffer;
        command[strcspn(command, "\n")] = '\0';
        if (strncmp(command, "list item names", 15) == 0) {
            printf("%s\n", command); 
            sortItems(group, compareItemsByName);
            listItems(group, alwaysTrueItem, NULL); 
        } else if (strncmp(command, "list items", 10) == 0) {
            printf("%s\n", command); 
            sortItems(group, compareItemsById);
            listItems(group, alwaysTrueItem, NULL);
        } else if (strncmp(command, "list members", 12) == 0) {
            printf("%s\n", command); 
            sortMembers(group, compareMembersById);
            listMembers(group, alwaysTrueMember, NULL);
        } else if (strncmp(command, "list member names", 17) == 0) {
            printf("%s\n", command); 
            sortMembers(group, compareMembersByName);
            listMembers(group, alwaysTrueMember, NULL);  
        } else if (strncmp(command, "list member", 11) == 0) {
            char *memberId = command + 12;
            printf("list member %s\n", memberId);
            listItemsByMember(group, memberId);
        } else if (strncmp(command, "list topsellers", 15) == 0) {
            printf("%s\n", command); 
            sortMembers(group, compareMembersByTotalSales);
            listMembers(group, alwaysTrueMember, NULL);
        } else if (strncmp(command, "search item", 11) == 0) {
            char *searchString = command + 12;
            printf("search item %s\n", searchString);
            sortItems(group, compareItemsById);
            listItems(group, itemContainsName, searchString);
        } else if (strncmp(command, "search member ", 14) == 0) {
            char *searchString = command + 14; 
            printf("search member %s\n", searchString);
            sortMembers(group, compareMembersById);
            listMembers(group, memberContainsName, searchString);
        } else if (strncmp(command, "search ignorecase item", 22) == 0) {
            char *searchString = command + 23;
            printf("search ignorecase item %s\n", searchString);
            sortItems(group, compareItemsById);
            listItems(group, itemContainsNameIgnoreCase, searchString);
        } else if (strncmp(command, "search ignorecase member", 24) == 0) {
            char *searchString = command + 25;
            printf("search ignorecase member %s\n", searchString);
            sortMembers(group, compareMembersById);
            listMembers(group, memberContainsNameIgnoreCase, searchString);
        } else if (strncmp(command, "sale", 4) == 0) {
            char memberId[9];
            int itemId, numSold;
            if (sscanf(command + 5, "%s %d %d", memberId, &itemId, &numSold) != 3 || numSold <= 0) {
                printf("Invalid command\n");
            } else {
                Member *member = NULL;
                for (int i = 0; i < group->mCount; ++i) {
                    if (strcmp(group->mList[i]->id, memberId) == 0) {
                        member = group->mList[i];
                        break;
                    }
                }
                Item *item = NULL;
                for (int i = 0; i < group->iCount; ++i) {
                    if (group->iList[i]->id == itemId) {
                        item = group->iList[i];
                        break;
                    }
                }
                if (!member || !item) {
                    printf("%s\n", command); 
                    printf("Invalid command\n");
                } else {
                    printf("%s\n", command); 
                    recordSale(group, memberId, itemId, numSold);
                }
            }
        } else if (strncmp(command, "quit", 4) == 0) {
            printf("quit\n");
            break;
        } else {
            printf("%s\n", command); 
            printf("Invalid command\n");
        }
        printf("\n");  
    }
    freeGroup(group);  
    return 0;
}
