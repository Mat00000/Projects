package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;

public class MenuController {
    @FXML
    private Label label;

    public void done(){
        label.setText(Integer.toString(Main.k) + " " + Integer.toString(Main.modify));
    }
}
