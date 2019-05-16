#ifndef tasks_h
#define tasks_h

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>

void printArray(int array[], int size);
void fillArray(int array[], int size);
void checkArray(int array[], int size, bool type);
void randomArray(int array[], int size, int range);
void third_task(char* fileName, int k);
void second_task(char* fileName, int k);
void first_task(char* sortType, bool type);

#endif