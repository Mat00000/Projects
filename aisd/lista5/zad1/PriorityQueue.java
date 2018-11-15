import java.util.Scanner;

public class PriorityQueue {

    int heapsize;
    ElementQueue [] elementQueue;

    public static void main(String[] args) {
        int M;      // liczba operacji
        String[] command;
        Scanner scan = new Scanner(System.in);

        if (args.length == 1) {
            M = Integer.parseInt(args[0]);
        }
        else return;

        PriorityQueue priorityQueue = new PriorityQueue(M);

        while(M > 0) {
            command = scan.nextLine().split(" ");
            if(command.length == 1 || command.length == 2 || command.length == 3) {
                switch (command[0]) {
                    case "insert":
                        priorityQueue.insert(Integer.parseInt(command[1]), Double.parseDouble(command[2]));
                        break;
                    case "empty":
                        priorityQueue.isEmpty();
                        break;
                    case "top":
                        if(priorityQueue.top() == 0) {
                            System.out.println();
                        }
                        else {
                            System.out.println(priorityQueue.top());
                        }
                        break;
                    case "pop":
                        priorityQueue.printPop();
                        break;
                    case "priority":
                        priorityQueue.priority(Integer.parseInt(command[1]), Double.parseDouble(command[2]));
                        break;
                    case "print":
                        priorityQueue.print();
                        System.out.println();
                        break;
                }
                M--;
            }
            else {
                System.err.println("Error argument");
                System.exit(0);
            }
        }



    }


    // ------------------------------------------------------ //

    PriorityQueue(int M) {
        elementQueue = new ElementQueue[M];
        heapsize = 0;
    }


    private void heapify(int h) {
        int l = h*2;
        int r = l+1;

        if(l < heapsize) {
            if(r < heapsize) {
                if(elementQueue[r].getPriority() > elementQueue[l].getPriority()) {
                    if (elementQueue[h].getPriority() > elementQueue[l].getPriority()) {
                        ElementQueue element = elementQueue[h];
                        elementQueue[h] = elementQueue[l];
                        elementQueue[l] = element;
                        heapify(l);
                    }
                }
                else if(elementQueue[h].getPriority() > elementQueue[r].getPriority()) {
                    ElementQueue tempElement = elementQueue[h];
                    elementQueue[h] = elementQueue[r];
                    elementQueue[r] = tempElement;
                    heapify(r);
                }
            }
            else {
                if(elementQueue[h].getPriority() > elementQueue[l].getPriority()) {
                    ElementQueue tempElement = elementQueue[h];
                    elementQueue[h] = elementQueue[l];
                    elementQueue[l] = tempElement;
                    heapify(l);
                }
            }
        }
    }

    public void priority(int z, double priority) {
        for(int i = 0; i < heapsize; i++) {
            if(elementQueue[i].getValue() == z) {
                if(elementQueue[i].getPriority() > priority) {
                    elementQueue[i].setPriority(priority);
                    int parent = i/2;
                    elementQueue[i].setPriority(priority);
                    ElementQueue tempElement = elementQueue[parent];
                    int temp = i;
                    while(tempElement.getPriority() > elementQueue[temp].getPriority()) {
                        elementQueue[parent] = elementQueue[temp];
                        elementQueue[temp] = tempElement;
                        temp = parent;
                        parent = parent/2;
                        tempElement = elementQueue[parent];
                    }
                }
            }
        }
    }

    public void insert(int z, double priority) {
        if(priority < 0) {
            return;
        }

        int i = heapsize;
        heapsize++;
        while(i > 0 && elementQueue[i/2].getPriority() > priority) {
            elementQueue[i] = elementQueue[i/2];
            i = i/2;
        }
        elementQueue[i] = new ElementQueue(z, priority);
    }

    public int empty() {
        if(heapsize == 0) return 1;
        else return 0;
    }

    public int top() {
        if(empty() == 0) return elementQueue[0].getValue();
        else return 0;
    }

    public int pop() {
        if(empty() == 0){
            int element = elementQueue[0].getValue();
            elementQueue[0] = elementQueue[heapsize-1];
            heapsize--;
            heapify(0);
            return element;
        }
        else
            return 0;
    }

    private void print() {
        for(int i = 0; i < heapsize; i++) {
            System.out.printf("(%d, %.1f)", elementQueue[i].getValue(), elementQueue[i].getPriority());
        }
    }


    private void isEmpty() {
        if(empty() == 1) System.out.println("1");
        else System.out.println("0");
    }

    private void printPop() {
        int elementPop = pop();
        if(elementPop != 0) System.out.println(elementPop);
        else System.out.println();
    }

}
