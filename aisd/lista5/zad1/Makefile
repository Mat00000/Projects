JCC = javac

JFLAGS = -g

default: PriorityQueue.class ElementQueue.class

PriorityQueue.class : PriorityQueue.java
	$(JCC) $(JFLAGS) PriorityQueue.java
	
ElementQueue.class : ElementQueue.java
	$(JCC) $(JFLAGS) ElementQueue.java

clean: 
	$(RM) *.class