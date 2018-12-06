%{
#include "bison.tab.h"
#include <stdlib.h>
void yyerror(char *);
%}

/* 
    zmienna globalna yylval służy do przekazywania parserowi atrybutu tokenu 
    (wartości semantycznej); przekazywana jest w momencie przekazania tokenu(NUM) do parsera 
*/

%%
^\#.*\n                     { ; }
\\\n                        { ; }
[0-9]+ 		                { yylval = atoi(yytext); return NUM; }	    /*Cyfry*/

[-+()=/*\n\^%]              { return *yytext; printf("%s", yytext); }   /*Operatory*/
[ \t]+                      { ; }
#.*\n 	                    { ; } 
([^-+()=/*\n\^%]|\D)+       { printf("Erorr sign: '%c'\n", yytext[0]); }
%%

int yywrap(void) {
    return 1;
}