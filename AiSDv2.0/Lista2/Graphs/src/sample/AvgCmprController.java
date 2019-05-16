package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.util.ArrayList;

public class AvgCmprController {

    @FXML
    private LineChart<?, ?> lineChart;

    public void drop(){
        lineChart.getData().clear();
    }

    public void drawSelect() {
        ArrayList<Node> array = null;
        XYChart.Series series = new XYChart.Series();
        try {
            array = Logic.getSelect(Main.path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 100; i <= 10000; i = i + 100) {
            series.getData().add(new XYChart.Data(Integer.toString(i), Logic.averageComparisons(array, i, Main.k)));
        }

        series.setName("Select");
        lineChart.getData().addAll(series);
    }

    public void drawInsert(){
        ArrayList<Node> array = null;
        XYChart.Series series = new XYChart.Series();
        try {
            array = Logic.getInsert(Main.path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=100; i<=10000; i=i+100){
            series.getData().add(new XYChart.Data(Integer.toString(i), Logic.averageComparisons(array, i, Main.k)));
        }

        series.setName("Insert");
        lineChart.getData().addAll(series);
    }

    public void drawHeap(){
        ArrayList<Node> array = null;
        XYChart.Series series = new XYChart.Series();
        try {
            array = Logic.getHeap(Main.path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=100; i<=10000; i=i+100){
            series.getData().add(new XYChart.Data(Integer.toString(i), Logic.averageComparisons(array, i, Main.k)));
        }

        series.setName("Heap");
        lineChart.getData().addAll(series);
    }

    public void drawQuick(){
        ArrayList<Node> array = null;
        XYChart.Series series = new XYChart.Series();
        try {
            array = Logic.getQuick(Main.path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=100; i<=10000; i=i+100){
            series.getData().add(new XYChart.Data(Integer.toString(i), Logic.averageComparisons(array, i, Main.k)));
        }

        series.setName("Quick");
        lineChart.getData().addAll(series);
    }

    public void drawQuick2(){
        ArrayList<Node> array = null;
        XYChart.Series series = new XYChart.Series();
        try {
            array = Logic.getQuick2(Main.path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=100; i<=10000; i=i+100){
            series.getData().add(new XYChart.Data(Integer.toString(i), Logic.averageComparisons(array, i, Main.k)));
        }

        series.setName("Quick");
        lineChart.getData().addAll(series);
    }

    public void drawModifiedQuick(){
        ArrayList<Node> array = null;
        XYChart.Series series = new XYChart.Series();
        try {
            array = Logic.getModifiedQuick(Main.path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=100; i<=10000; i=i+100){
            series.getData().add(new XYChart.Data(Integer.toString(i), Logic.averageComparisons(array, i, Main.k)));
        }

        series.setName("Modified Quick");
        lineChart.getData().addAll(series);
    }
}
