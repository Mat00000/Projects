all: zad2.o insertionSort.o mergeSort.o quickSort.o dualPivotSort.o
	gcc zad2.o insertionSort.o mergeSort.o quickSort.o dualPivotSort.o -o main

zad2.o: zad2.c insertionSort.c mergeSort.c quickSort.c dualPivotSort.c sorts.h global.h
	gcc zad2.c -c -o zad2.o

insertionSort.o: insertionSort.c global.h sorts.h
	gcc insertionSort.c -c -o insertionSort.o

mergeSort.o: mergeSort.c global.h sorts.h
	gcc mergeSort.c -c -o mergeSort.o

quickSort.o: quickSort.c global.h sorts.h
	gcc quickSort.c -c -o quickSort.o

dualPivotSort.o: dualPivotSort.c global.h sorts.h
	gcc dualPivotSort.c -c -o dualPivotSort.o

clear:
	rm -f *.o main
