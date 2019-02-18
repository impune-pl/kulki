package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;



public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="MainCanvas"
    private Canvas MainCanvas; // Value injected by FXMLLoader

    @FXML // fx:id="StartButton"
    private Button StartButton; // Value injected by FXMLLoader

    @FXML // fx:id="StopButton"
    private Button StopButton; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {


    }
}
