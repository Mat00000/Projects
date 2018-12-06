program: bison.y flex.l
	bison -d bison.y 
	lex flex.l 
	gcc -o calculator lex.yy.c bison.tab.c

clean:
	rm bison.tab.c bison.tab.h lex.yy.c $(OBJ) calculator