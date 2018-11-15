#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include "sorts.h"
#include "global.h"

void printTable(int n, int table[]) {
	for(int i = 0; i < n; i++) {
		printf("%d ", table[i]);
	}
}

void isSorted(bool c, int table[], int n) {
	if(c) {
		for(int x = 0; x < n-1; x++) {
			if(table[x] > table[x+1]) {
			printf("Error sort\n");
			}
		}
	}
	else {
		for(int x = 0; x < n-1; x++) {
			if(table[x] < table[x+1]) {
			printf("Error sort\n");
			}
		}
	}
}

int main(int argc, char* argv[]) {
	double total;
	clock_t start_t, stop_t, total_t;
	int n, k, random;
	bool comp;
	FILE *fp;
	srand (time(NULL));
	
	if((strcmp(argv[1], "--type")) == 0 && (strcmp(argv[3], "--comp")) == 0 && (strcmp(argv[5], "--stat")) == 0) {
		char filename[100];
		scanf("%d", &k);
		strcpy(filename, argv[6]);
		fp = fopen(filename, "w");
		for(int n = 100; n <= 10000; n = n+100) {
			int table[n];
			fprintf(fp, "Size n = %d\n", n);
			for(int h = 0; h < k; h++) {
				for(int i = 0; i < n; i++) {
					random = rand() % 9999999;
					table[i] = random;
				}
				if(strcmp(argv[2], "insert") == 0) {
					if(strcmp(argv[4], ">=") == 0) {
						comp = true;
						count = 0;
						swap = 0;
						start_t = clock();
						insertionSort(n, table, comp);
						stop_t = clock();
						total_t = stop_t - start_t;
						total = ((float)total_t/CLOCKS_PER_SEC);
						fprintf(fp, "Insert >= -- Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
						isSorted(true, table, n);
					}
					else if(strcmp(argv[4], "<=") == 0) {
						comp = false;
						count = 0;
						swap = 0;	
						start_t = clock();					
						insertionSort(n, table, comp);
						stop_t = clock();
						total_t = stop_t - start_t;
						total = ((float)total_t/CLOCKS_PER_SEC);
						fprintf(fp, "Insert <= -- Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
						isSorted(false, table, n);
					}
					else {
						printf("ERROR");
						return -1;
					}
				}
				else if(strcmp(argv[2], "merge") == 0) {
					if(strcmp(argv[4], ">=") == 0) {
						count = 0;
						swap = 0;
						start_t = clock();
						mergeSortI(table, 0, n-1);
						stop_t = clock();
						total_t = stop_t - start_t;
						total = ((float)total_t/CLOCKS_PER_SEC);
						fprintf(fp, "Merge >= -- Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
						isSorted(true, table, n);
					}
					else if(strcmp(argv[4], "<=") == 0) {
						count = 0;
						swap = 0;
						start_t = clock();
						mergeSortD(table, 0, n-1);
						stop_t = clock();
						total_t = stop_t - start_t;
						total = ((float)total_t/CLOCKS_PER_SEC);
						fprintf(fp, "Merge <= -- Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
						isSorted(false, table, n);
						
					}
					else {
						printf("ERROR");
						return -1;
					}
				}
				else if(strcmp(argv[2], "quick") == 0) {
					if(strcmp(argv[4], ">=") == 0) {
						count = 0;
						swap = 0;
						start_t = clock();
						quickSortI(table, 0, n-1);
						stop_t = clock();
						total_t = stop_t - start_t;
						total = ((float)total_t/CLOCKS_PER_SEC);
						fprintf(fp, "Quick >= -- Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
						isSorted(true, table, n);
					}
					else if(strcmp(argv[4], "<=") == 0) {
						count = 0;
						swap = 0;
						start_t = clock();
						quickSortD(table, 0, n-1);
						stop_t = clock();
						total_t = stop_t - start_t;
						total = ((float)total_t/CLOCKS_PER_SEC);
						fprintf(fp, "Quick <= -- Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
						isSorted(false, table, n);
					}
					else {
						printf("ERROR");
						return -1;
					}
				}
				else if(strcmp(argv[2], "dual") == 0) {
					if(strcmp(argv[4], ">=") == 0) {
						count = 0;
						swap = 0;
						start_t = clock();
						dualPivotSortI(table, 0, n-1);
						stop_t = clock();
						total_t = stop_t - start_t;
						total = ((float)total_t/CLOCKS_PER_SEC);
						fprintf(fp, "Dual >= -- Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
						isSorted(true, table, n);
					}
					else if(strcmp(argv[4], "<=") == 0) {
						count = 0;
						swap = 0;
						start_t = clock();
						dualPivotSortD(table, 0, n-1);
						stop_t = clock();
						total_t = stop_t - start_t;
						total = ((float)total_t/CLOCKS_PER_SEC);
						fprintf(fp, "Dual <= -- Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
						isSorted(false, table, n);
					}
					else {
						printf("ERROR");
						return -1;
					}
				}
			}
		}
		fclose(fp);
	}
	else if((strcmp(argv[1], "--type")) == 0 && (strcmp(argv[3], "--comp")) == 0 && (strcmp(argv[3], "--comp")) !=0) {
		scanf("%d", &n);
		int table[n];
		
		if(n < 1) {
			return -1;
		}
		else if(n == 1) {
			scanf("%d", &table[0]);
			printTable(n, table);
			return 0;
		}
		else {
			for(int i = 0; i < n; i++) {
			scanf("%d", &table[i]);
			}
			if(strcmp(argv[2], "insert") == 0) {
				if(strcmp(argv[4], ">=") == 0) {
					comp = true;
					count = 0;
					swap = 0;
					start_t = clock();
					insertionSort(n, table, comp);
					stop_t = clock();
					total_t = stop_t - start_t;
					total = ((float)total_t/CLOCKS_PER_SEC);
					printf("Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
					isSorted(true, table, n);
					printTable(n, table);
				}
				else if(strcmp(argv[4], "<=") == 0) {
					comp = false;
					count = 0;
					swap = 0;	
					start_t = clock();					
					insertionSort(n, table, comp);
					stop_t = clock();
					total_t = stop_t - start_t;
					total = ((float)total_t/CLOCKS_PER_SEC);
					printf("Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
					isSorted(false, table, n);
					printTable(n, table);
				}
				else {
					printf("ERROR");
					return -1;
				}
			}
			else if(strcmp(argv[2], "merge") == 0) {
				if(strcmp(argv[4], ">=") == 0) {
					count = 0;
					swap = 0;
					start_t = clock();
					mergeSortI(table, 0, n-1);
					stop_t = clock();
					total_t = stop_t - start_t;
					total = ((float)total_t/CLOCKS_PER_SEC);
					printf("Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
					isSorted(true, table, n);
					printTable(n, table);
				}
				else if(strcmp(argv[4], "<=") == 0) {
					count = 0;
					swap = 0;
					start_t = clock();
					mergeSortD(table, 0, n-1);
					stop_t = clock();
					total_t = stop_t - start_t;
					total = ((float)total_t/CLOCKS_PER_SEC);
					printf("Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
					isSorted(false, table, n);
					printTable(n, table);
					
				}
				else {
					printf("ERROR");
					return -1;
				}
			}
			else if(strcmp(argv[2], "quick") == 0) {
				if(strcmp(argv[4], ">=") == 0) {
					count = 0;
					swap = 0;
					start_t = clock();
					quickSortI(table, 0, n-1);
					stop_t = clock();
					total_t = stop_t - start_t;
					total = ((float)total_t/CLOCKS_PER_SEC);
					printf("Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
					isSorted(true, table, n);
					printTable(n, table);
				}
				else if(strcmp(argv[4], "<=") == 0) {
					count = 0;
					swap = 0;
					start_t = clock();
					quickSortD(table, 0, n-1);
					stop_t = clock();
					total_t = stop_t - start_t;
					total = ((float)total_t/CLOCKS_PER_SEC);
					printf("Swaps: %d  |  Comparisons: %d  |  Time: %f\n", swap, count, total);
					isSorted(false, table, n);
					printTable(n, table);
				}
				else {
					printf("ERROR");
					return -1;
				}
			}
		}
	}
	return 0;
}