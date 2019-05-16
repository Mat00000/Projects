#include "tasks.h"
#include "sorts.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>

int comparisonCounter;
int swapCounter;

int main(int argc, char** argv) {
	if(strcmp(argv[1], "--type") == 0){
		bool type = !strcmp(argv[3], "--asc");
		first_task(argv[2], type);
	}else if(strcmp(argv[2], "--type") == 0){
		bool type = !strcmp(argv[1], "--asc");
		first_task(argv[3], type);
	}else if(strcmp(argv[1], "--stat") == 0){
		second_task(argv[2], atoi(argv[3]));
	}else if(strcmp(argv[1], "--mstat") == 0){
		third_task(argv[2], atoi(argv[3]));
	}
	return 0;
}