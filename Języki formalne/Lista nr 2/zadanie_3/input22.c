%{
#include <iostream>
using namespace std;
extern int yylex();
bool flag = false;

%}
%option noyywrap
%%
"//".*							/** Ignoruj "one-line" comments */
"//"(.*\\\n)*.*					/** Ignoruj "multi-line one-line comments" */

"/**/"							/** Ignoruj "multi-line one-line comments" */
"/**"[^*]+[.\n]*"*/"			{if(flag == true) ECHO;} 	/** Dokumentacja */
"/*"[^*]+[.\n]*"*/"				{;}							/** Ignoruj "multi-line one-line comments" */

%%

int main(int argc, char* argv[]) {
	char option; // zmienna
	cout<<"T lub t pokaz dokumentacyjne.\n";
	cin >> option;
		if(option == 't' || option == 'T'){
			flag = true;
		}
		else {
			flag = false;
		}
	// wpisuj się ///////////
	////////////
	cout<<"Flaga -> "<<flag<<"\n";

	char name_file_input[] = "input.c";	
	char name_file_output[] = "output.c";
	/* ? tutaj wchodza /// */
	/*
	
	*/
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
	// kczy /* */
	printf("Ukoczono\n");
 
  return 0;
}