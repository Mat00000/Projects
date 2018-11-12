%{
#include <stdio.h>
extern int yylex();
int count_words = 0;
int count_lines = 0;
%}

%option noyywrap
%%

^([^ \t\n]+)	{ECHO; count_words++;} 				// Zliczanie słów między białymi znakami, wypisanie wyrazów
^([ \t]+)	{count_words++;}				// Zliczanie słów, słowo po białym znaku
^([ \t]*\n) 	{;} 						// Ignorowanie pustych lini z tabulatorem przed
^(\n+)		{;}						// Ignorowanie pustych lini
\n		{fprintf(yyout, "\n"); count_lines++;} 		// Zliczanie lini w tekście
[ \t]+\n	{fprintf(yyout, "\n"); count_lines++;} 		// Jeden lub wiele tabulatorów zakończone next line zmień na next line
[ \t]+		{fprintf(yyout, " "); count_words++;}		// Usuń wiele tabulatorów na rzecz białego znaku

%%

int main(int argc, char* argv[]) {
	char name_file_input[] = "input.txt";	
	char name_file_output[] = "output.txt";

	FILE *input_file = fopen(name_file_input, "r");
	FILE *output_file = fopen(name_file_output, "wt");
	
	if(!input_file) {
		printf("ERROR: Brak pliku inputowego\n");
		return -1;
	}
	
	yyin = input_file;
	yyout = output_file;
	yylex();

	fclose(input_file);
	fclose(output_file);
	printf("Liczba lini w txt: %d\n", count_lines);
	printf("Liczba wyrazow w txt: %d\n", count_words);
	printf("Ukonczono\n");

}
