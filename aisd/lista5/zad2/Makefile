JCC = javac

JFLAGS = -g

default: AlgorithmDjikstra.class PriorityQueue.class ElementQueue.class

AlgorithmDjikstra.class : AlgorithmDjikstra.java
	$(JCC) $(JFLAGS) AlgorithmDjikstra.java
	
PriorityQueue.class : PriorityQueue.java
	$(JCC) $(JFLAGS) PriorityQueue.java
	
ElementQueue.class : ElementQueue.java
	$(JCC) $(JFLAGS) ElementQueue.java

clean: 
	$(RM) *.class