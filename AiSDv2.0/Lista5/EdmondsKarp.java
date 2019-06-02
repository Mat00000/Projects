import java.util.ArrayDeque;
import java.util.Queue;
import java.io.*;

public class EdmondsKarp {

    private long[][] flow; //max flow beetween i and j verticles
    private long[][] capacity; // edge capacity
    private int[] parent; //parent
    private boolean[] visited; //just for checking if visited
    @SuppressWarnings("unused")
    private int n, m;

    public EdmondsKarp(int numOfVerticles, int numOfEdges) {
        this.n = numOfVerticles;
        this.m = numOfEdges;
        this.flow = new long[n][n];
        this.capacity = new long[n][n];
        this.parent = new int[n];
        this.visited = new boolean[n];
    }

    public void addEdge(int from, int to, long capacity) {
        assert capacity >= 0;
        this.capacity[from][to] += capacity;
    }

    /**
     * Get maximum flow.
     * 
     * @param s source
     * @param t target
     * @return maximum flow
     */
    public long getMaxFlow(int s, int t) {
        while (true) {
            final Queue<Integer> Q = new ArrayDeque<Integer>();
            Q.add(s);

            for (int i = 0; i < this.n; ++i)
                visited[i] = false;
            visited[s] = true;

            boolean check = false;
            int current;
            while (!Q.isEmpty()) {
                current = Q.peek();
                if (current == t) {
                    check = true;
                    break;
                }
                Q.remove();
                for (int i = 0; i < n; ++i) {
                    if (!visited[i] && capacity[current][i] > flow[current][i]) {
                        visited[i] = true;
                        Q.add(i);
                        parent[i] = current;
                    }
                }
            }
            if (check == false) 
                break;

            long temp = capacity[parent[t]][t] - flow[parent[t]][t];
            for (int i = t; i != s; i = parent[i])
                temp = Math.min(temp, (capacity[parent[i]][i] - flow[parent[i]][i]));

            for (int i = t; i != s; i = parent[i]) {
                flow[parent[i]][i] += temp;
                flow[i][parent[i]] -= temp;
            }
        }

        long result = 0;
        for (int i = 0; i < n; ++i)
            result += flow[s][i];
        return result;
    }
	
	public void showGraph(int numOfVerticles) throws IOException {
		File file = new File("data.txt");
		FileOutputStream fos = new FileOutputStream(file);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		//System.out.println("param n := " + numOfVerticles + ";");
		bw.write("param n := " + numOfVerticles + ";");
		bw.newLine();
		//System.out.println("param : E :   a :=");
		bw.write("param : E :   a :=");
		bw.newLine();
		for(int i = 0; i < numOfVerticles; i++) {
			for(int j = 0; j < numOfVerticles; j++) {
				// System.out.println((i+1) +" " + (j+1) + "    " + capacity[i][j]);
				bw.write((i+1) +" " + (j+1) + "    " + capacity[i][j]);
				bw.newLine();
			}
		}
		//System.out.println(";");
		bw.write(";");
		bw.newLine();
		bw.close();
	}
}