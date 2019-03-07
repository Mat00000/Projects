run: program
	./compiler input.txt

program: compiler.y compiler.l
	bison -d compiler.y 
	lex compiler.l 
	g++ -o compiler lex.yy.c compiler.tab.c -lm

clean:
	rm compiler.tab.c compiler.tab.h lex.yy.c $(OBJ) compiler