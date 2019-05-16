package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Logic {
    public static ArrayList<Node> readData(String fileName) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        ArrayList<Node> data = new ArrayList<>();
        String line;

        while((line = bufferedReader.readLine()) != null){
            int size = Integer.parseInt(line.split(" - ")[0]);
            line = line.split(" - ")[1];
            int cmp = Integer.parseInt(line.split(", ")[0]);
            int swp = Integer.parseInt(line.split(", ")[1]);
            double time = Double.parseDouble(line.split(", ")[2]);
            data.add(new Node(size, cmp, swp, time));
        }

        bufferedReader.close();

        return data;
    }

    public static double averageComparisons(ArrayList<Node> array, int n, int k){
        int i = 0;
        double sum = 0;
        while(array.get(i).size != n){
            i++;
        }
        for(int j=i; j<i+k; j++){
            sum = sum + array.get(j).comparisons;
        }
        return sum/k;
    }

    public static double averageSwaps(ArrayList<Node> array, int n, int k){
        int i = 0;
        double sum = 0;
        while(array.get(i).size != n){
            i++;
        }
        for(int j=i; j<i+k; j++){
            sum = sum + array.get(j).swaps;
        }
        return sum/k;
    }

    public static double averageTime(ArrayList<Node> array, int n, int k){
        int i = 0;
        double sum = 0;
        while(array.get(i).size != n){
            i++;
        }
        for(int j=i; j<i+k; j++){
            sum = sum + array.get(j).time;
        }
        return sum/k;
    }

    public static ArrayList<Node> getSelect(String fileName) throws IOException {
        ArrayList<Node> data = readData(fileName);
        ArrayList<Node> mainData = new ArrayList<>();

        for(int i=0; i<data.size(); i=i+4){
            mainData.add(data.get(i));
        }
        return mainData;
    }

    public static ArrayList<Node> getInsert(String fileName) throws IOException {
        ArrayList<Node> data = readData(fileName);
        ArrayList<Node> mainData = new ArrayList<>();

        for(int i=1; i<data.size(); i=i+4){
            mainData.add(data.get(i));
        }
        return mainData;
    }

    public static ArrayList<Node> getHeap(String fileName) throws IOException {
        ArrayList<Node> data = readData(fileName);
        ArrayList<Node> mainData = new ArrayList<>();

        for(int i=2; i<data.size(); i=i+4){
            mainData.add(data.get(i));
        }
        return mainData;
    }

    public static ArrayList<Node> getQuick(String fileName) throws IOException {
        ArrayList<Node> data = readData(fileName);
        ArrayList<Node> mainData = new ArrayList<>();

        for(int i=3; i<data.size(); i=i+4){
            mainData.add(data.get(i));
        }
        return mainData;
    }

    public static ArrayList<Node> getQuick2(String fileName) throws IOException {
        ArrayList<Node> data = readData(fileName);
        ArrayList<Node> mainData = new ArrayList<>();

        for(int i=0; i<data.size(); i=i+2){
            mainData.add(data.get(i));
        }
        return mainData;
    }

    public static ArrayList<Node> getModifiedQuick(String fileName) throws IOException {
        ArrayList<Node> data = readData(fileName);
        ArrayList<Node> mainData = new ArrayList<>();

        for(int i=1; i<data.size(); i=i+2){
            mainData.add(data.get(i));
        }
        return mainData;
    }
}
