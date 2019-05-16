import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WalkingTree {
    private static long beforeUsedMemory;
    private static long afterUsedMemory;
    private static long actualMemoryUsed;
    int vNumber;
    int eNumber;
    Edge [] edges;
    List<Edge> spanEdges;
    double weight;
    PriorityQueue q;
    List<Edge> []  adj;

    public WalkingTree(int vNumber, int eNumber) {
        this.vNumber        = vNumber;
        this.eNumber        = eNumber;
        this.edges          = new Edge[eNumber];
        this.weight         = 0;
        this.q              = new PriorityQueue(eNumber);
        this.spanEdges      = new ArrayList<>();
        this.adj            = new List[vNumber];
    }

    public void addEdge(String string, String string2, String string3, int i) {
        int w1 = Integer.parseInt(string);
        int w2 = Integer.parseInt(string2);
        double p = Double.parseDouble(string3);

        edges[i]=new Edge(w1-1,w2-1,p);
        q.insert(i, p);
        if(adj[w1-1] == null)
            adj[w1-1] = new ArrayList<>();
        adj[w1-1].add(new Edge(w1-1,w2-1,p));
        if(adj[w2-1] == null)
            adj[w2-1] = new ArrayList<>();
        adj[w2-1].add(new Edge(w2-1,w1-1,p));
    }

    public Edge findMinEdge(List<Edge> l,Boolean [] T){
        int size = l.size();
        if(size < 1)
            return null;
        int z = 0;
        while(z < size && T[l.get(z).vertexNumber2] != false){
            z++;
        }
        if(z >= size)
            return null;
        Edge counter = l.get(z);
        double temp;

        for(int i = z+1; i < l.size(); i++){
            if(T[l.get(i).vertexNumber2] == false){
                temp = l.get(i).weight;
                if(temp<counter.weight)
                    counter= l.get(i);
            }
        }
        return counter;
    }

    public Edge findRandEdge(List<Edge> l, Boolean [] T){

        List<Edge> p = new ArrayList<>();
        int size = l.size();
        for(int i = 0; i < size; i++)
            p.add(l.get(i));
        Random rand = new Random();
        int value = rand.nextInt(size);
        while(T[p.get(value).vertexNumber2] != false){
            p.remove(value);
            size--;
            value = rand.nextInt(size);
        }
        Edge counter=p.get(value);

        return counter;
    }

    public void eulerAlgorithm() {

        SpanningTree Tree = new SpanningTree(vNumber, eNumber);
        Tree.edges=this.edges;
        Tree.arguments=this.adj;
        Tree.priorityQueue=this.q;
        Tree.algorithmKruskal();

        this.adj = new List[vNumber];
        Edge ed;
        eNumber=Tree.spanEdges.size();
        for(int i=0;i<eNumber;i++){
            ed=Tree.spanEdges.get(i);
            if(adj[ed.vertexNumber1]==null)
                adj[ed.vertexNumber1] = new ArrayList<>();
            adj[ed.vertexNumber1].add(new Edge(ed.vertexNumber1,ed.vertexNumber2,ed.weight));
            if(adj[ed.vertexNumber2]==null)
                adj[ed.vertexNumber2] = new ArrayList<>();
            adj[ed.vertexNumber2].add(new Edge(ed.vertexNumber2,ed.vertexNumber1,ed.weight));
        }

        int w = Tree.spanEdges.get(0).vertexNumber1;
        findEuler(w, -1);
        System.out.println("Euler algorithm ");
    }

    public void findEuler(int w,int bef){
        int i = 0;
        while(i<adj[w].size()){
            Edge edge = adj[w].get(i);
            if(edge.vertexNumber2!=bef){
                weight+=edge.weight;
                spanEdges.add(edge);
                //System.out.println(w + "  " + edge.v2);
                findEuler(edge.vertexNumber2,w);
                spanEdges.add(adj[edge.vertexNumber2].get(0));
                adj[w].remove(i);
            } else i++;
        }
    }
    public void greedyAlgorithm() {
        int w = edges[q.top()].vertexNumber1;
        Boolean [] visit = new Boolean[vNumber];
        for(int i=0;i<vNumber;i++)
            visit[i]=false;
        visit[w]=true;
        int  n,before;

        for(int i=0;i<vNumber-1;i++){
            before = w;
            Edge x = findMinEdge(adj[before],visit);
            w = x.vertexNumber2;

            spanEdges.add(new Edge(before, w, x.weight));
            weight+=x.weight;
            visit[w]=true;
        }
        System.out.println("Greedy algorithm ");
    }

    public void doRandomAlgorithm() {
        int w = edges[q.top()].vertexNumber1;
        Boolean [] visit = new Boolean[vNumber];
        for(int i=0;i<vNumber;i++)
            visit[i]=false;
        visit[w]=true;
        int  n,before;

        for(int i=0;i<vNumber-1;i++){
            before = w;
            Edge x = findRandEdge(adj[before],visit);
            w = x.vertexNumber2;

            spanEdges.add(new Edge(before, w, x.weight));
            weight+=x.weight;
            visit[w]=true;
        }
        System.out.println("Random algorithm ");

    }

    public void printOutput(long M, long  T) {
        for(int i = 0; i < spanEdges.size(); i++) {
            System.err.println((spanEdges.get(i).vertexNumber1 + 1) + " " + (spanEdges.get(i).vertexNumber2 + 1) + " " + spanEdges.get(i).weight);
        }
        System.out.println("Steps: " + spanEdges.size() + " Weight: " + weight + " Memory: " + M + " Time: " + T + " ");
        weight = 0;
        spanEdges.clear();
    }

    public static void test() {
        int counter = 0;
        Random rand = new Random();
        for(int i = 5; i < 501; i *= 10){
            WalkingTree struct = new WalkingTree(i,((i*(i-1))/2));
            for(int z = 1; z <= i; z++)
                for(int g = z+1; g <= i; g++){
                    int waga = rand.nextInt(60)+ 60;
                    struct.addEdge(Integer.toString(z),Integer.toString(g),Integer.toString(waga),counter);
                    counter++;
                }
            counter = 0;
            run(struct);
        }
    }

    public static void main(String[] args) {
//        test();

        System.out.print("number of vertices: ");
        Scanner reader = new Scanner(System.in);
        int vNumber = reader.nextInt();
        int eNumber = (vNumber*(vNumber-1))/2;
        System.out.println("edge definition (u, v, w)");
        WalkingTree struct = new WalkingTree(vNumber,eNumber);
        String[] command;
        command = reader.nextLine().split(" ");
        for (int i = 0; i < eNumber; i++){
            command = reader.nextLine().split(" ");
            struct.addEdge(command[0],command[1],command[2],i);
        }

        run(struct);
    }

    private static void run(WalkingTree struct) {
        long startTime = System.currentTimeMillis();
        beforeUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        struct.doRandomAlgorithm();
        long endTime = System.currentTimeMillis() - startTime;
        afterUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        actualMemoryUsed = afterUsedMemory - beforeUsedMemory;
        struct.printOutput(actualMemoryUsed, endTime);

        beforeUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTime = System.currentTimeMillis();
        struct.greedyAlgorithm();
        endTime = System.currentTimeMillis() - startTime;
        afterUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        actualMemoryUsed = afterUsedMemory - beforeUsedMemory;
        struct.printOutput(actualMemoryUsed, endTime);

        beforeUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTime = System.currentTimeMillis();
        struct.eulerAlgorithm();
        endTime = System.currentTimeMillis() - startTime;
        afterUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        actualMemoryUsed = afterUsedMemory - beforeUsedMemory;
        struct.printOutput(actualMemoryUsed, endTime);
    }


}
