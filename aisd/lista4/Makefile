JCC = javac

JFLAGS = -g

default: Main.class BinarySearchTree.class RedBlackTree.class Functionality.class

Main.class : Main.java
	$(JCC) $(JFLAGS) Main.java
	
BinarySearchTree.class: BinarySearchTree.java
	$(JCC) $(JFLAGS) BinarySearchTree.java
	
RedBlackTree.class: RedBlackTree.java
	$(JCC) $(JFLAGS) RedBlackTree.java

Functionality.class: Functionality.java
	$(JCC) $(JFLAGS) Functionality.java
	
clean:
	$(RM) *.class
	
