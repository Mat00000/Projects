#include "sorts.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

void swap(int *a, int *b){
	int temp = *a;
	*a = *b;
	*b = temp;
	swapCounter++;
	//fprintf(stderr, "swap: %d <-> %d\n", *b, *a);
}

int maxIndex(int array[], int size, int start){
	int max = array[start];
	for(int i=start+1; i<size; i++){
		comparisonCounter++;
		//fprintf(stderr, "comparison: %d > %d\n", array[i], max);
		if(array[i]>max){
			max = array[i];
			start = i;
		}
	}
	return start;
}

int minIndex(int array[], int size, int start){
	int min = array[start];
	for(int i=start+1; i<size; i++){
		comparisonCounter++;
		//fprintf(stderr, "comparison: %d < %d\n", array[i], min);
		if(array[i]<min){
			min = array[i];
			start = i;
		}
	}
	return start;
}

void selectSort(int array[], int size, bool type){
	if(type){
		for(int i=0; i<size; i++){
			int value = minIndex(array, size, i);
			swap(&array[i], &array[value]);
		}
	}else{
		for(int i=0; i<size; i++){
			int value = maxIndex(array, size, i);
			swap(&array[i], &array[value]);
		}
	}
}

void insertSort(int array[], int start, int size, bool type){
	if(type){
		for(int i=start; i<size-1; i++){
			int j=i+1;
			comparisonCounter++;
			//fprintf(stderr, "comparison: %d < %d\n", array[j], array[j-1]);
			while(j>start && array[j]<array[j-1]){
				swap(&array[j], &array[j-1]);
				j--;
				comparisonCounter++;
				//fprintf(stderr, "comparison: %d < %d\n", array[j], array[j-1]);
			}
		}
	}else{
		for(int i=start; i<size-1; i++){
			int j=i+1;
			comparisonCounter++;
			//fprintf(stderr, "comparison: %d > %d\n", array[j], array[j-1]);
			while(j>start && array[j]>array[j-1]){
				swap(&array[j], &array[j-1]);
				j--;
				comparisonCounter++;
				//fprintf(stderr, "comparison: %d > %d\n", array[j], array[j-1]);
			}
		}

	}
}

void heapSort(int array[], int size, bool type){
	for(int i=1; i<size; i++){
		heapCheckUp(array, i, type);
	}

	for(int i=size-1; i>0; i--){
		swap(&array[i], &array[0]);
		heapCheckDown(array, 0, type, i-1);
	}
}

void heapCheckUp(int array[], int index, bool type){
	if(!type){
		comparisonCounter++;
		//fprintf(stderr, "comparison: %d < %d\n", array[index], array[(index-1)/2]);
		if(index>0 && array[index]<array[(index-1)/2]){
			swap(&array[index], &array[(index-1)/2]);
			heapCheckUp(array, (index-1)/2, type);
		}
	}else{
		comparisonCounter++;
		//fprintf(stderr, "comparison: %d > %d\n", array[index], array[(index-1)/2]);
		if(index>0 && array[index]>array[(index-1)/2]){
			swap(&array[index], &array[(index-1)/2]);
			heapCheckUp(array, (index-1)/2, type);
		}
	}
}

void heapCheckDown(int array[], int index, bool type, int size){
	if(type){
		comparisonCounter++;
		//fprintf(stderr, "comparison: %d <= %d\n", index*2+2, size);
		if((index*2+2)<=size){
			comparisonCounter++;
			//fprintf(stderr, "comparison: %d < %d*\n", array[index], array[index*2+1]);
			if(array[index]<array[index*2+1] || array[index]<array[index*2+2]){
				comparisonCounter++;
				//fprintf(stderr, "comparison: %d < %d*\n", array[index*2+1], array[index*2+2]);
				if(array[index*2+1]<array[index*2+2]){
					swap(&array[index], &array[index*2+2]);
					heapCheckDown(array, index*2+2, type, size);
				}else{
					swap(&array[index], &array[index*2+1]);
					heapCheckDown(array, index*2+1, type, size);
				}
			}
		}else if((index*2+1)<=size){
			comparisonCounter++;
			//fprintf(stderr, "comparison: %d < %d**\n", array[index], array[index*2+1]);
			if(array[index]<array[index*2+1]){
				swap(&array[index], &array[index*2+1]);
				heapCheckDown(array, index*2+1, type, size);
			}
		}
	}else{
		comparisonCounter++;
		//fprintf(stderr, "comparison: %d <= %d\n", index*2+2, size);
		if((index*2+2)<=size){
			comparisonCounter++;
			//fprintf(stderr, "comparison: %d > %d*\n", array[index], array[index*2+1]);
			if(array[index]>array[index*2+1] || array[index]>array[index*2+2]){
				comparisonCounter++;
				//fprintf(stderr, "comparison: %d < %d*\n", array[index*2+1], array[index*2+2]);
				if(array[index*2+1]<array[index*2+2]){
					swap(&array[index], &array[index*2+1]);
					heapCheckDown(array, index*2+1, type, size);
				}else{
					swap(&array[index], &array[index*2+2]);
					heapCheckDown(array, index*2+2, type, size);
				}
			}
		}else if((index*2+1)<=size){
			comparisonCounter++;
			//fprintf(stderr, "comparison: %d > %d**\n", array[index], array[index*2+1]);
			if(array[index]>array[index*2+1]){
				swap(&array[index], &array[index*2+1]);
				heapCheckDown(array, index*2+1, type, size);
			}
		}
	}
}

void quickSort(int array[], int start, int end, bool type){
	comparisonCounter++;
	//fprintf(stderr, "comparison: %d > %d\n", end-start, 1);
	if(end-start>1){
		int m = partition(array, start, end, type);
		quickSort(array, start, m, type);
		quickSort(array, m+1, end, type);
	}
}

int partition(int array[], int start, int end, bool type){
	int counter = start;
	if(type){
		for(int i=start; i<end-1; i++){
			comparisonCounter++;
			//fprintf(stderr, "comparison: %d > %d\n", array[end-1], array[i]);
			if(array[end-1]>array[i]){
				swap(&array[i], &array[counter]);
				counter++;
			}
		}
	}else{
		for(int i=start; i<end-1; i++){
			comparisonCounter++;
			//fprintf(stderr, "comparison: %d < %d\n", array[end-1], array[i]);
			if(array[end-1]<array[i]){
				swap(&array[i], &array[counter]);
				counter++;
			}
		}
	}
	swap(&array[end-1], &array[counter]);
	return counter;
}

void modifiedQuickSort(int array[], int start, int end, bool type){
	if(end-start>8){
		int m = modifiedPartition(array, start, end, type);
		modifiedQuickSort(array, start, m, type);
		modifiedQuickSort(array, m+1, end, type);
	}else{
		insertSort(array, start, end, type);
	}
}

int modifiedPartition(int array[], int start, int end, bool type){
	int median;
	if(array[start]>array[end-1] && array[start]>array[(start+end)/2]){
		if(array[end-1] > array[(start+end)/2])
			median = end-1;
		else
			median = (start/end)/2;
	}else if(array[start] > array[(start+end)/2] || array[start] > array[end-1]){
		median = start;
	}else if(array[(start+end)/2] > array[end-1]){
		median = end-1;
	}else{
		median = (start+end)/2;
	}

	int counter = start;
	if(type){
		for(int i=start; i<end-1; i++){
			comparisonCounter++;
			//fprintf(stderr, "comparison: %d > %d\n", array[end-1], array[i]);
			if(array[median]>array[i]){
				swap(&array[i], &array[counter]);
				counter++;
			}
		}
	}else{
		for(int i=start; i<end-1; i++){
			comparisonCounter++;
			//fprintf(stderr, "comparison: %d < %d\n", array[end-1], array[i]);
			if(array[median]<array[i]){
				swap(&array[i], &array[counter]);
				counter++;
			}
		}
	}
	swap(&array[end-1], &array[counter]);
	return counter;
}
