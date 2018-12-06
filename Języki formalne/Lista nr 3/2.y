%{
    #include <stdio.h>
    #include<math.h>
    void yyerror(const char *);
    int yylex(void);
    int err = 0;
%}
%token NUM
/* Ustalenie wiązań dla operatorów i nadanie im priorytetów. Kolejność lini odpowiada wzrostowi priorytetu */
%left '+' '-'
%left '*' '/' '%'
%right '^'
%right MINUS
%%
/* Tworzenie struktury reguł gramatycznych */
input   : /* empty */
        |  input '\n'
        |  input  exp '\n' { printf("\nWynik: %d\n",$2); }
        ;

exp:	NUM		{printf("%d ", $1);}
| exp '+' exp   { $$ = $1 + $3; printf("+ ") ;}
| exp '-' exp   { $$ = $1 - $3; printf("- ") ;}
| exp '*' exp   { $$ = $1 * $3; printf("* ") ;}
| exp '/' exp	{ 
	if($3 > 0) { $$ = $1 / $3; printf("/ "); }
	else { yyerror("D"); err=1; }
}
| exp '%' exp { 
	if($3>0) { $$ = $1 % $3; printf("%% "); }
	else { yyerror("M"); err=1; }
}
| exp '^' exp			{ $$ = pow($1,$3); printf("^ "); }
|'-' exp %prec MINUS	{ $$ = -$2; printf("-%d ", $2); }
| '(' exp ')'			{ $$ = $2; }
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