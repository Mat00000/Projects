import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlgorithmDjikstra {
    int indexVertex;
    int indexEdge;
    int indexStartVertex;
    List<Edge> [] list;
    int [] prev;
    double [] distance;

    public AlgorithmDjikstra(int indexVertex, int indexEdge) {
        this.indexVertex    = indexVertex;
        this.indexEdge      = indexEdge;
        this.list           = new List[indexVertex];
        this.prev           = new int[indexVertex];
        this.distance       = new double[indexVertex];
    }

    private void addEdge(int weight1, int weight2, double p, int x) {
        if(list[weight1 - 1] == null) {
            list[weight1 - 1] = new ArrayList<>();
        }
        list[weight1 - 1].add(new Edge(weight1-1, weight2-1, p));
    }

    private double findEdge(List<Edge> lEdge, int vertex) {
        int count = 0;
        while(lEdge.get(count).vertexNumber2 != vertex) {
            count++;
        }
        return lEdge.get(count).weight;
    }

    private void setStartEdge(int startVertex) {
        this.indexStartVertex = startVertex-1;
    }

    private void algorithmDjikstra() {
        Boolean [] bool = new Boolean[indexVertex];
        int u;
        int intTemp;
        double doubleTemp;
        PriorityQueue priorityQueue = new PriorityQueue(indexVertex);

        for(int i = 0; i < indexVertex; i++){
            bool[i] = false;
            priorityQueue.insert(i, Integer.MAX_VALUE);
            distance[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        priorityQueue.priority(indexStartVertex, 0);
        distance[indexStartVertex] = 0;

        while(priorityQueue.empty() != 1){
            //q.print();
            u = priorityQueue.pop();
            bool[u] = true;
            for(int i = 0; i < list[u].size(); i++){
                intTemp = list[u].get(i).vertexNumber2;
                if(bool[intTemp] == false){
                    doubleTemp = distance[u]+list[u].get(i).weight;
                    if(doubleTemp < distance[intTemp]){
                        priorityQueue.priority(intTemp, doubleTemp);
                        distance[intTemp] = doubleTemp;
                        prev[intTemp] = u;
                    }
                }
            }
        }
    }



    private void printInfo() {
        int q, w;
        for(int i = 0; i < indexVertex; i++) {
            q = i;
            w = i;
            System.err.println("Droga dla wierzchołka " + (i+1) + " o wadze " + distance[i]);
            while(prev[q] != -1) {
                q = prev[q];
                System.err.println("V" + (q+1) + " -- V" + (w+1) + " Waga: " + findEdge(list[q], w));
                w = q;
            }
        }
        for(int i = 0; i < indexVertex; i++) {
            System.out.println((i+1) + " -> " + distance[i]);
        }
    }

    static public void main(String[] args) {
        long runTime = System.currentTimeMillis();
        Scanner scan = new Scanner(System.in);
        String[] command;

        System.out.print("number of vertices: ");
        int numberOfVertices = scan.nextInt();
        System.out.print("number of edges: ");
        int numberOfEdges = scan.nextInt();
        System.out.println("edge definition (u, v, w)");

        AlgorithmDjikstra struct = new AlgorithmDjikstra(numberOfVertices, numberOfEdges);
        command = scan.nextLine().split(" ");

        for(int i = 0; i < numberOfEdges; i++) {
            command = scan.nextLine().split(" ");
            struct.addEdge(Integer.parseInt(command[0]), Integer.parseInt(command[1]), Double.parseDouble(command[2]), i);
        }

        System.out.print("start vertex: ");
        int startVertex = scan.nextInt();
        struct.setStartEdge(startVertex);

        // TIME & RUN
        long startTime = System.currentTimeMillis();
        struct.algorithmDjikstra();
        long endTime = System.currentTimeMillis() - startTime;
        System.err.println("Time algorithm: " + endTime + "ms");
        struct.printInfo();
        long endRunTime = System.currentTimeMillis() - runTime;
        System.err.println("Run time: " + endRunTime + "ms");
    }



}
