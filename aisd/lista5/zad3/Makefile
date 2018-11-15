JCC = javac

JFLAGS = -g

default: PriorityQueue.class ElementQueue.class AlgorithmDjikstra.class SpanningTree.class SetElement.class Edge.class Set.class

AlgorithmDjikstra.class : AlgorithmDjikstra.java
	$(JCC) $(JFLAGS) AlgorithmDjikstra.java
	
PriorityQueue.class : PriorityQueue.java
	$(JCC) $(JFLAGS) PriorityQueue.java
	
ElementQueue.class : ElementQueue.java
	$(JCC) $(JFLAGS) ElementQueue.java
	
Edge.class : Edge.java
	$(JCC) $(JFLAGS) Edge.java
	
Set.class : Set.java
	$(JCC) $(JFLAGS) Set.java
	
SetElement.class : SetElement.java
	$(JCC) $(JFLAGS) SetElement.java

SpanningTree.class : SpanningTree.java
	$(JCC) $(JFLAGS) SpanningTree.java

clean: 
	$(RM) *.class