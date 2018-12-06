%{
    #include <stdio.h>
    #include <math.h>
    #include <string.h>
    #include <stdlib.h>

    void yyerror(const char *);
    int yylex(void);
    int err = 0;
    char* result;
    char str[12];

        char* connect(const char *s1, const char *s2)
{
    char *result = malloc(strlen(s2) + 1);
    strcpy(result, s1);
    strcat(result, s2);
    return result;
    
}

%}
%token NUM
/* Ustalenie wiązań dla operatorów i nadanie im priorytetów. Kolejność lini odpowiada wzrostowi priorytetu */
%left '+' '-'
%left '*' '/' '%'
%right '^'
%right MINUS
%%
/* Tworzenie struktury reguł gramatycznych */
input:
    |   input '\n'
    |   input exp '\n' { 
            if(err==0){
                printf("%s\nResult: %d\n", result , $1); result = "";
            }
            else{
                result = "";
                err = 0;
            }
    }
    | error '\n' { err = 0; result = "";}
    ;


exp:	NUM		{$$ = $1; sprintf(str, "%d ", $1); result = connect(result, str);}
| exp '+' exp   {$$ = $1 + $3; result = connect(result, "+ ");}
| exp '-' exp   {$$ = $1 - $3; result = connect(result, "- ");}
| exp '*' exp   {$$ = $1 * $3; result = connect(result, "* ");}
| exp '/' exp	{ 
	if($3 > 0){
        $$ = $1 / $3;
		result = connect(result, "/ ");
	}
	else{
		yyerror("D");
		err = 1;		
	}
}
| exp '%' exp { 
	if($3 > 0){
		$$ = $1 % $3;
		result = connect(result, "% ");
	}
	else{
		yyerror("M");
		err=1;		
	}
}
| '-' exp  %prec MINUS  { $$ = -$2; sprintf(str, "-%d ", $2); result = connect(result, str);}
| exp '^' exp			{ $$ = pow($1, $3); result = connect(result, "^ ");}
| '(' exp ')'			{ $$ = $2;}
;

%%


void yyerror (char const *s){
	if (*s=='D')
		fprintf (stderr, "\nError division by zero!\n");
	if (*s=='M')
		fprintf (stderr, "\nError modulo by zero!\n");
	else
  		fprintf (stderr, "\nError.\n");
}
int main(void) {
    yyparse();
}