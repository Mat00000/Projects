%{
#include <stdio.h>
extern int yylex();
%}

%option noyywrap
%%

\<!--([^-]|-[^-]|--+[^-\>])*-*--\>	fprintf(yyout, "");
--.*?--					fprintf(yyout, "");
.					ECHO;

%%

int main(int argc, char* argv[]) {

	char name_file_input[] = "input.html";	
	char name_file_output[] = "output.html";

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

	printf("Ukoczono\n");

}
