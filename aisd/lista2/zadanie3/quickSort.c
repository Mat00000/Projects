#include <stdio.h>
#include <stdbool.h>
#include "sorts.h"
#include "global.h"


void swaper(int* a, int* b) {
	swap++;
	fprintf(stderr, "Swap %d with %d\n", *a, *b);
    int t = *a;
    *a = *b;
    *b = t;
}

int partitionI (int table[], int left, int right) {
    int pivot = table[right];   
    int i = (left-1);  
 
    for (int j = left; j <= right-1; j++){	
		count++;
		fprintf(stderr, "Comparison %d with %d\n", pivot, table[j]);
        if (table[j] <= pivot)
        {
            i++; 
            swaper(&table[i], &table[j]);
        }
    }
    swaper(&table[i + 1], &table[right]);
    return (i + 1);
}

void quickSortI(int table[], int left, int right) {
    if (left < right) {
        int n = partitionI(table, left, right);
        quickSortI(table, left, n-1);
        quickSortI(table, n+1, right);
    }
}

int partitionD(int table[], int left, int right) {
    int pivot = table[right];   
    int i = (left-1);  
 
    for (int j = left; j <= right-1; j++){	
		count++;
		fprintf(stderr, "Comparison %d with %d\n", pivot, table[j]);
        if (table[j] >= pivot)
        {
            i++; 
            swaper(&table[i], &table[j]);
        }
    }
    swaper(&table[i + 1], &table[right]);
    return (i + 1);
}

void quickSortD(int table[], int left, int right) {
    if (left < right) {
        int n = partitionD(table, left, right);
        quickSortD(table, left, n-1);
        quickSortD(table, n+1, right);
    }
}