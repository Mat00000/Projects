import java.util.Scanner;

public class Main {
    static Functionality fun;

    public static void main(String[] args) {
        int numberOfInsert = 0;
        int numberOfLoad = 0;
        int numberOfDelete = 0;
        int numberOfFind = 0;
        int numberOfMin = 0;
        int numberOfMax = 0;
        int numberOfSuccessor = 0;
        int numberOfInorder = 0;

        long startTimeMillis = System.currentTimeMillis();
        long startTimeNano = System.nanoTime();

        Scanner reader = new Scanner(System.in);
        String[] input;

        if((args.length == 4 || args.length == 2)) {
			if(args[0].equals("--type")) {
					switch(args[1]) {
						case "bst":
							fun = new BinarySearchTree();
							break;
						case "rbt":
							fun = new RedBlackTree();
							break;
						case "splay":
							fun = new SplayTree();
							break;
					}
			}
			else {
            System.err.println("ERROR");
            System.exit(0);
			}
        }
        else {
            System.err.println("ERROR");
            System.exit(0);
        }

        int numberOfOperations = reader.nextInt();
        input = reader.nextLine().split(" ");

        while(numberOfOperations > 0) {
			System.err.println("TU?");
            input = reader.nextLine().split(" ");
            if(input.length != 2 && input.length != 1) {
                System.err.println("ERROR");
                System.exit(0);
            }
			System.out.println(input);
            switch (input[0]) {
                case "insert":
                    fun.insert(input[1]);
                    numberOfInsert++;
                    break;
                case "load":
                    fun.load(input[1]);
                    numberOfLoad++;
                    break;
                case "delete":
                    fun.delete(input[1]);
                    numberOfDelete++;
                    break;
                case "find":
                    fun.find(input[1]);
                    numberOfFind++;
                    System.out.println();
                    break;
                case "min":
                    fun.min();
                    numberOfMin++;
                    System.out.println();
                    break;
                case "max":
                    fun.max();
                    numberOfMax++;
                    System.out.println();
                    break;
                case "successor":
                    fun.successor(input[1]);
                    numberOfSuccessor++;
                    System.out.println();
                    break;
                case "inorder":
                    fun.inorder();
                    numberOfInorder++;
                    System.out.println();
                    break;
                }
            numberOfOperations--;
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

        // System.err: Number of operations
        System.err.println("Number of inserts: " + (numberOfInsert + BinarySearchTree.moreNumberOfInsert + RedBlackTree.moreNumberOfInsert));
        System.err.println("Number of deletions: " + numberOfDelete);
        System.err.println("Number of loads: " + numberOfLoad);
        System.err.println("Number of finds: " + numberOfFind);
        System.err.println("Number of min: " + numberOfMin);
        System.err.println("Number of max: " + numberOfMax);
        System.err.println("Number of inorders: " + numberOfInorder);

        // System.err: Size of the structure
        System.err.println("Max size of structure: " + (RedBlackTree.maxSize + BinarySearchTree.maxSize));
        System.err.println("Final size of structure: " + (RedBlackTree.maxSize + BinarySearchTree.maxSize - BinarySearchTree.reduceSize - RedBlackTree.reduceSize));

    }

}
