package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;


public class Controller
{
    @FXML
    public Button StartButton;
    @FXML
    public Button StopButton;

    private Simulation simulation;

    @FXML
    private Canvas MainCanvas;

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
