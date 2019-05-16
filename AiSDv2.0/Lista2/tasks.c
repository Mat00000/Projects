#include "tasks.h"
#include "sorts.h"

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>

void printArray(int array[], int size){
	for(int i=0; i<size; i++){
		printf("%d ", array[i]);
	}
		printf("\n");
}

void randomArray(int array[], int size, int range){
	srand(time(NULL));
	for(int i=0; i<size; i++){
		array[i] = rand()%range;
	}
}


void first_task(char* sortType, bool type){
	comparisonCounter = 0;
	swapCounter = 0;
	clock_t start, end;
	double timeUsed;

	int size;
	scanf("%d", &size);
	int array[size];
	for(int i=0; i<size; i++){
		scanf("%d", &array[i]);
	}

	if(strcmp(sortType, "select")==0){
		start = clock();
		selectSort(array, size, type);
		end = clock();
	}else if(strcmp(sortType, "insert")==0){
		start = clock();
		insertSort(array, 0, size, type);
		end = clock();
	}else if(strcmp(sortType, "heap")==0){
		start = clock();
		heapSort(array, size, type);
		end = clock();
	}else if(strcmp(sortType, "quick")==0){
		start = clock();
		quickSort(array, 0, size, type);
		end = clock();
	}else if(strcmp(sortType, "mquick")==0){
		start = clock();
		modifiedQuickSort(array, 0, size, type);
		end = clock();
	}

	timeUsed = ((double) (end - start)) / CLOCKS_PER_SEC;
	fprintf(stderr, "comparisons: %d\nswaps: %d\ntime: %f\n", comparisonCounter, swapCounter, timeUsed);
	printf("number of elements: %d\n", size);
	printArray(array, size);
}

void second_task(char* fileName, int k){
	char fullName[50];
	snprintf(fullName, 50, "%s.txt", fileName);

	clock_t start, end;
	FILE * fp;
	double timeUsed;
	fp = fopen(fullName, "w+");

	for(int n=100; n<=10000; n=n+100){
		int mainArray[n];
		for(int i=0; i<k; i++){
			randomArray(mainArray, n, 10000);
			int temp[n];

			comparisonCounter = 0;
			swapCounter = 0;
			memcpy(temp, mainArray, sizeof(temp));
			start=clock();
			selectSort(temp, n, true);
			end=clock();
			timeUsed = ((double) (end - start)) / CLOCKS_PER_SEC;
			fprintf(fp, "%d - %d, %d, %f\n", n, comparisonCounter, swapCounter, timeUsed);

			comparisonCounter = 0;
			swapCounter = 0;
			memcpy(temp, mainArray, sizeof(temp));
			start=clock();
			insertSort(temp, 0, n, true);
			end=clock();
			timeUsed = ((double) (end - start)) / CLOCKS_PER_SEC;
			fprintf(fp, "%d - %d, %d, %f\n", n, comparisonCounter, swapCounter, timeUsed);

			comparisonCounter = 0;
			swapCounter = 0;
			memcpy(temp, mainArray, sizeof(temp));
			start=clock();
			heapSort(temp, n, true);
			end=clock();
			timeUsed = ((double) (end - start)) / CLOCKS_PER_SEC;
			fprintf(fp, "%d - %d, %d, %f\n", n, comparisonCounter, swapCounter, timeUsed);

			comparisonCounter = 0;
			swapCounter = 0;
			memcpy(temp, mainArray, sizeof(temp));
			start=clock();
			quickSort(temp, 0, n, true);
			end=clock();
			timeUsed = ((double) (end - start)) / CLOCKS_PER_SEC;
			fprintf(fp, "%d - %d, %d, %f\n", n, comparisonCounter, swapCounter, timeUsed);
		}
	}
	fclose(fp);
}

void third_task(char* fileName, int k){
	char fullName[50];
	snprintf(fullName, 50, "%s.txt", fileName);

	clock_t start, end;
	FILE * fp;
	double timeUsed;
	fp = fopen(fullName, "w+");

	for(int n=100; n<=10000; n=n+100){
		int mainArray[n];
		for(int i=0; i<k; i++){
			randomArray(mainArray, n, 10000);
			int temp[n];

			comparisonCounter = 0;
			swapCounter = 0;
			memcpy(temp, mainArray, sizeof(temp));
			start=clock();
			quickSort(temp, 0, n, true);
			end=clock();
			timeUsed = ((double) (end - start)) / CLOCKS_PER_SEC;
			fprintf(fp, "%d - %d, %d, %f\n", n, comparisonCounter, swapCounter, timeUsed);

			comparisonCounter = 0;
			swapCounter = 0;
			memcpy(temp, mainArray, sizeof(temp));
			start=clock();
			modifiedQuickSort(temp, 0, n, true);
			end=clock();
			timeUsed = ((double) (end - start)) / CLOCKS_PER_SEC;
			fprintf(fp, "%d - %d, %d, %f\n", n, comparisonCounter, swapCounter, timeUsed);
		}
	}
	fclose(fp);
}