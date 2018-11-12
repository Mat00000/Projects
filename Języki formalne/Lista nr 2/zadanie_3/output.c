int main(int argc, char* argv[]) {
	const char option = 'd';
	bool flag = false;
	
	if(argc > 1) {
		if(*(argv[1]) == option) {
			flag = true;
		}
	}
	
	
	
	char name_file_input[] = "input.c";	
	char name_file_output[] = "output.c";

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
	
	
	
	
	return 0;