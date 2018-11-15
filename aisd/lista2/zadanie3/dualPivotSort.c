#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "sorts.h"
#include "global.h"
 
void dualPivotSortI(int* table, int left, int right) {
    if (left < right) {
        int l_pivot, r_pivot; 
        r_pivot = partI(table, left, right, &l_pivot);
        dualPivotSortI(table, left, l_pivot-1);
        dualPivotSortI(table, l_pivot+1, r_pivot-1);
        dualPivotSortI(table, r_pivot+1, right);
    }
}
 
int partI(int* table, int left, int right, int* l_pivot) {
    if (table[left] > table[right]) {					// want r_pivot > l_pivot
        swaper(&table[left], &table[right]);
	}
	
    int a = left+1;			
    int b = right-1;
	int x = left+1; 
	int p = table[left]; 								// p is left pivot
	int q = table[right];								// q is right pivot
    while (x <= b) {
		count++;
		fprintf(stderr, "Comparison %d with %d\n", p, table[x]);
			if (table[x] < p) {							// if elements are less than left pivot
            swaper(&table[x], &table[a]);
            a++;
        }
        else if (table[x] >= q) {
			count++;
			fprintf(stderr, "Comparison %d with %d\n", q, table[b]);
            while (table[b] > q && x < b) {
                b--;
			}
            swaper(&table[x], &table[b]);
            b--;
			count++;
			fprintf(stderr, "Comparison %d with %d\n", q, table[b]);
            if (table[x] < p) {
                swaper(&table[x], &table[a]);
                a++;
            }
        }
        x++;
    }
    a--;
    b++;

    swaper(&table[left], &table[a]);
    swaper(&table[right], &table[b]);
 
    *l_pivot = a; 										// return indexes of pivots !WARNING!
 
    return b;
}


void dualPivotSortD(int* table, int left, int right) {
    if (left < right) {
        int l_pivot, r_pivot; 
        r_pivot = partD(table, left, right, &l_pivot);
        dualPivotSortD(table, left, l_pivot-1);
        dualPivotSortD(table, l_pivot+1, r_pivot-1);
        dualPivotSortD(table, r_pivot+1, right);
    }
}
 
int partD(int* table, int left, int right, int* l_pivot) {
    if (table[left] < table[right]) {					// want l_pivot > r_pivot
        swaper(&table[left], &table[right]);
	}
	
    int a = left+1;			
    int b = right-1;
	int x = left+1; 
	int p = table[left]; 								// p is left pivot
	int q = table[right];								// q is right pivot
    while (x <= b) {
		count++;
		fprintf(stderr, "Comparison %d with %d\n", p, table[x]);
        if (table[x] > p) {								// if elements are more than left pivot
            swaper(&table[x], &table[a]);
            a++;
        }
        else if (table[x] <= q) {
			count++;
			fprintf(stderr, "Comparison %d with %d\n", q, table[b]);
            while (table[b] < q && x < b) {
                b--;
			}
            swaper(&table[x], &table[b]);
            b--;
			count++;
			fprintf(stderr, "Comparison %d with %d\n", q, table[b]);
            if (table[x] > p) {
                swaper(&table[x], &table[a]);
                a++;
            }
        }
        x++;
    }
    a--;
    b++;

    swaper(&table[left], &table[a]);
    swaper(&table[right], &table[b]);
 
    *l_pivot = a; 										// return indexes of pivots !WARNING!
 
    return b;
}