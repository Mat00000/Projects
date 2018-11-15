#include <stdio.h>
#include <stdlib.h>
#include <time.h>


typedef struct Element{
	int number;
	struct Element *next;
	struct Element *prev;
} Element;

typedef struct List{
	Element *head;
	Element *tail;
	int count;
} List;

int lengthList(List* list){
	return (list->count);
}

void addElement(List* list, int new){
	list->count++;
	Element* pointer = (Element*)malloc(sizeof(Element));
	pointer->number = new;
	pointer->next = NULL;
	if(list->head == NULL) {
		list->head = pointer;
		list->tail = pointer;
		pointer->prev = NULL;
	}
	else {
		pointer->prev = list->tail;
		list->tail->next = pointer;
		list->tail = pointer;
	}
}

int seekElement(List* list, int index) {
	int length = lengthList(list);
	if((index > lengthList(list)) || (index < 1)) {
		printf("Error: Wrong Data\n");
	}
	
	if((length/2) <= index) {
		Element* pointer = list->tail;
		for(int i = length; i > index; i--) {
			if(i == index) {
				return pointer->number;
			}
			else {
				pointer = pointer->prev;
			}
		}
	} 
	else {
		Element* pointer = list->head;
		for(int i = 0; i < index; i++) {
			if(i == index) {
				return pointer->number;
			}
			else {
				pointer = pointer->next;
			}
		}
	}
}

void printList(List* list){
  if(list == NULL){
    printf("EMPTY\n");
  }else{
	int length = lengthList(list);
	printf("Lenght: %d\n", length);
    Element* pointer = list->head;
    for(int i = 0; i < length; i++){
      printf("%d ", pointer->number);
      pointer = pointer->next;
    }
  }
  printf("\n");
}

int main(){
	int option, index, i, random;
	List* list1 = (List*)malloc(sizeof(List));
	list1->count = 0;
	list1->head = NULL;
	list1->tail = list1->head;
	
	srand(time(NULL));
	clock_t start_t, stop_t, total_t;

	for(int i = 1; i <=1000; i++){
		addElement(list1, i);
	}
	
	
	while(option != 1) {
		printf("Seek Element: ");
		scanf("%d", &index);
		start_t = clock();
		for(i = 0; i < 1000000; i++) {
			seekElement(list1, index);
		}	
		stop_t = clock();
		total_t = (stop_t - start_t);
		double total = (double)total_t/CLOCKS_PER_SEC;
		printf("Total Time: %f\n", total);
		printf("Do you want test again? 0/1\n");
		scanf("%d", &option);	
	}

	
	/*
	printf("Seek random Elements\n");
	start_t = clock();
	for(i= 0; i < 1000000; i++) {
		random = rand() % 1000 + 1;
		seekElement(list1, random);
	}
	stop_t = clock();
	total_t = (stop_t - start_t);
	double total = (double)total_t/CLOCKS_PER_SEC;
	printf("Total Time: %f\n", total);
	*/

	
	
	addElement(list1, 5);
	addElement(list1, 3);
	
	printf("-------------\n"
		   "Thanks 4 use\n"
		   "By Mateusz Laskowski\n");
	
  return 0;
}