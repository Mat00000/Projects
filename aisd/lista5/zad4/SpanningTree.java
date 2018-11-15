import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpanningTree {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        if(args.length != 1) System.exit(0);

        if(args[0].equals("-k")) algorythm = true;
        else if(args[0].equals("-p")) algorythm = false;

        if(algorythm == null){
            System.exit(0);
        }

        System.out.print("number of vertices: ");
        Scanner scan = new Scanner(System.in);
        int vNumber = scan.nextInt();
        System.out.print("number of edges: ");
        int eNumber = scan.nextInt();
        System.out.println("edge definition (u, v, w)");
        SpanningTree struct = new SpanningTree(vNumber, eNumber);
        String[] command;
        command = scan.nextLine().split(" ");
        for (int i = 0; i < eNumber; i++){
            command = scan.nextLine().split(" ");
            struct.addEdge(command[0], command[1], command[2], i);
        }
        run(struct);
        long endTime = System.currentTimeMillis() - startTime;
        System.err.println("Runtime: " + endTime + "ms");
    }

    static Boolean algorythm;
    int indexVertex;
    int indexEdge;
    Edge [] edges;
    List<Edge> spanEdges;
    double weight;
    PriorityQueue priorityQueue;

    List<Edge> []  arguments;

    public SpanningTree(int indexVertex, int indexEdge) {
        this.indexVertex        = indexVertex;
        this.indexEdge          = indexEdge;
        this.edges              = new Edge[indexEdge];
        this.weight             = 0;
        this.priorityQueue      = new PriorityQueue(indexEdge);
        this.spanEdges          = new ArrayList<>();
        this.arguments          = new List[indexVertex];
    }

    public void algorithmKruskal() {
        Edge edge;
        SetElement [] z = new SetElement[indexVertex];
        for(int i = 0; i < indexVertex; i++) {
            z[i] = new SetElement(i);
            Set.makeSet(z[i]);
        }
        for(int i = 0; i < indexEdge-1; i++) {
            edge = edges[priorityQueue.pop()];
            if(Set.findSet(z[edge.vertexNumber1]) != Set.findSet(z[edge.vertexNumber2])) {
                spanEdges.add(edge);
                weight+=edge.weight;
                Set.setUnion(z[edge.vertexNumber1], z[edge.vertexNumber2]);
            }
        }
    }

    public void algorithmPrima() {
        Boolean [] bool = new Boolean[indexVertex];
        Edge [] edges = new Edge[indexEdge];
        int count = 0;
        for(int i = 0; i < indexVertex; i++) {
            bool[i] = false;
        }
        PriorityQueue q = new PriorityQueue(indexEdge);
        int w = 0;
        bool[w] = true;
        for(int i = 0; i < indexVertex-1; i++) {
            for(int z = 0; z < arguments[w].size();z++) {
                int u = arguments[w].get(z).vertexNumber2;
                if(bool[u] == false) {
                    edges[count] = new Edge(w, u, arguments[w].get(z).weight);
                    q.insert(count, arguments[w].get(z).weight);
                    count++;
                }
            }
            Edge p = edges[q.pop()];
            while(bool[p.vertexNumber2] == true){
                p = edges[q.pop()];
            }
            spanEdges.add(p);
            bool[p.vertexNumber2] = true;
            w = p.vertexNumber2;
            weight+=p.weight;
        }
    }

    public void addEdge(String string, String string2, String string3, int i) {
        int w1 = Integer.parseInt(string);
        int w2 = Integer.parseInt(string2);
        double p = Double.parseDouble(string3);

        if(arguments[w1-1] == null)
            arguments[w1-1] = new ArrayList<>();
        arguments[w1-1].add(new Edge(w1-1,w2-1, p));
        if(arguments[w2-1] == null)
            arguments[w2-1] = new ArrayList<>();
        arguments[w2-1].add(new Edge(w2-1,w1-1, p));
        edges[i] = new Edge(w1-1,w2-1,p);
        priorityQueue.insert(i, p);
    }

    public void printInfo() {
        for(int i = 0; i < spanEdges.size(); i++)
            System.out.println("u = " + (spanEdges.get(i).vertexNumber1+1) + "  v = " + (spanEdges.get(i).vertexNumber2+1) + "  w = " + (spanEdges.get(i).weight));
        System.out.println("waga -> " + weight);

    }


    private static void run(SpanningTree struct) {
        long startTime = System.currentTimeMillis();
        if(algorythm == true)
            struct.algorithmKruskal();
        else
            struct.algorithmPrima();
        long endTime = System.currentTimeMillis() - startTime;
        struct.printInfo();
        System.err.println("Working time : " + endTime + "ms");
    }
}