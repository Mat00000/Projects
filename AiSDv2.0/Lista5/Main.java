import java.util.Random;

public class Main {

    public static void main(String[] args) {

        long startTimeMillis = System.currentTimeMillis();
        long startTimeNano = System.nanoTime();

        if((args.length == 4 || args.length == 2) && args[0].equals("--size")) {
			int k = Integer.parseInt(args[1]); // k-dimensional hypercube
            if(k < 0 || k > 16) {
				// errror
				System.err.println("Error: invalid size [0-16]");
				System.exit(0);
			}
			else {
				int numOfVerticles = (int)Math.pow(2, k);
				int numOfEdges = k * (int)Math.pow(2, k);
				int source = 0;
				int target = numOfVerticles - 1; // 2^k-1
				
				System.out.println(numOfVerticles);
				System.out.println(numOfEdges);
				
				// create k-dimensional hypercube
				EdmondsKarp hypercube = new EdmondsKarp(numOfVerticles, numOfEdges);
				
				HammingCode hamCode = new HammingCode();
				
				for(int i = 0; i < numOfVerticles; i++) {
					for(int j = 0; j < numOfVerticles; j++) {
						int l = (int)Math.max(hamCode.errorCorrection(i), hamCode.errorCorrection(j));
						int capacity = new Random().nextInt((int)Math.pow(2, l) + 1) + 1; // [1, 2^l]
						// init capacity in hypercube
						hypercube.addEdge(i, j, capacity);
					}
				}
				// print magnifying paths of k-dimensional hypercube
				System.out.println("Result: " + hypercube.getMaxFlow(source, target));
			}
        }
        else {
            System.err.println("Error: input error");
            System.exit(0);
        }
        


        long executionTimeMillis = System.currentTimeMillis() - startTimeMillis;
        long executionTimeNano = System.nanoTime() - startTimeNano;

        // System.err: Running time
        if((executionTimeMillis) > 0) {
            System.err.println("Running time: " + (executionTimeMillis) + " milliseconds");
        }
        else {
            System.err.println("Running time: " + executionTimeNano + " nanoseconds / " + (executionTimeNano/1000000) + " milliseconds");
        }

    }

}