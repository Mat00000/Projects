int main(int argc, char* argv[]) {
	const char option = 'd';
	bool flag = false;
	// Jeżeli argument wchodzący == 'd' to usuwamy
	if(argc > 1) {
		if(*(argv[1]) == option) {
			flag = true;
		}
	}//
	// 
	/*
	a normalnie 	
	*/
	
	char name_file_input[] = "input.c";	
	char name_file_output[] = "output.c";

	FILE *input_file = fopen(name_file_input, "r");
	FILE *output_file = fopen(name_file_output, "wt");
	
	if(!input_file) {
		printf("ERROR: Brak pliku inputowego\n");
		return -1;
	}// tutaj działa
	/** Dokumentacyjny */
	yyin = input_file;
	yyout = output_file;
	yylex();
	
	fclose(input_file);
	fclose(output_file);
	/**
		ciekawe
	*/
	printf("Ukoczono\n");
	/* ta
	*/
	/*
	cos jest napisane tak dla siebie ale jednak dokumentacyjnie
	
	*/
	////////////////////
	///
	return 0;