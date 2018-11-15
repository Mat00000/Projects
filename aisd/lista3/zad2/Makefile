all: zad2.o insertionSort.o
	gcc zad2.o insertionSort.o -o main

zad2.o: zad2.c insertionSort.c function.h global.h
	gcc zad2.c -c -o zad2.o

insertionSort.o: insertionSort.c global.h function.h
	gcc insertionSort.c -c -o insertionSort.o

clear:
	rm -f *.o main
