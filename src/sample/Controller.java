package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;



public class Controller
{
    Simulation simulation;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Canvas MainCanvas;

    @FXML
    private Button StartButton;

    @FXML
    private Button StopButton;

    @FXML
    void initialize()
    {
        simulation = new Simulation(MainCanvas);
    }

    @FXML
    private void startSimulation()
    {
        simulation.start();
    }

    @FXML
    private void stopSimulation()
    {
        simulation.stop();
    }
}
