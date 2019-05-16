#ifndef sorts_h
#define sorts_h

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

extern int comparisonCounter;
extern int swapCounter;

void selectSort(int array[], int size, bool type);
void insertSort(int array[], int start, int size, bool type);
void heapSort(int array[], int size, bool type);
void quickSort(int array[], int start, int end, bool type);
void modifiedQuickSort(int array[], int start, int end, bool type);

void swap(int *a, int *b);
int maxIndex(int array[], int size, int start);
int minIndex(int array[], int size, int start);
void heapCheckUp(int array[], int index, bool type);
void heapCheckDown(int array[], int index, bool type, int size);
int partition(int array[], int start, int end, bool type);
int modifiedPartition(int array[], int start, int end, bool type);

#endif
