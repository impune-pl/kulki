package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;

import sample.constraints.BallConstraints;


public class Controller
{

    @FXML
    private Slider BallVelocityChangeChanceSlider;

    @FXML
    private Label BallVelocityChangeChanceValue;

    @FXML
    private Slider BallColorChangeChanceSlider;

    @FXML
    private Label BallColorChangeChanceValue;

    @FXML
    private ToggleButton DrawingModeToggle;

    @FXML
    private TextField BallsCountTextField;

    @FXML
    private Label CurrentBallCountLabel;

    @FXML
    private Canvas MainCanvas;

    private Simulation simulation;

    private boolean workingOnVelocitySlider;
    private boolean workingOnColorChangeSlider;

    @FXML
    void initialize()
    {
        simulation = new Simulation(MainCanvas);
        BallColorChangeChanceValue.textProperty().bind(BallColorChangeChanceSlider.valueProperty()
                                                                                  .divide(100.0d)
                                                                                  .asString("%.2f"));

        BallVelocityChangeChanceValue.textProperty().bind(BallVelocityChangeChanceSlider.valueProperty()
                                                                                        .divide(100.0d)
                                                                                        .asString("%.2f"));

        BallColorChangeChanceSlider.setValue(100-BallConstraints.CENTRAL_BALL_COLOR_CHANGE_MIN);
        BallColorChangeChanceSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(workingOnVelocitySlider)return;
            workingOnVelocitySlider = true;
            int x = (int) BallColorChangeChanceSlider.getValue();
            BallConstraints.CENTRAL_BALL_COLOR_CHANGE_MIN = 100 - x;
            BallColorChangeChanceSlider.adjustValue((double)x);
            workingOnVelocitySlider=false;
        });

        BallVelocityChangeChanceSlider.setValue(100-BallConstraints.BALL_VELOCITY_CHANGE_MIN);
        BallVelocityChangeChanceSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(workingOnColorChangeSlider)return;
            workingOnColorChangeSlider = true;
            int x = (int) BallVelocityChangeChanceSlider.getValue();
            BallConstraints.BALL_VELOCITY_CHANGE_MIN = 100 - x;
            BallVelocityChangeChanceSlider.adjustValue((double) x);
            workingOnColorChangeSlider=false;
        });

        BallsCountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("1?\\d{0,4}"))
            {
                BallsCountTextField.setText(oldValue);
            }
        });

        CurrentBallCountLabel.textProperty().bind(simulation.ballCount.asString());
    }

    @FXML
    private void addBalls()
    {
        int ballCount;
        if(BallsCountTextField.getText().length() > 0)
            ballCount = Integer.parseInt(BallsCountTextField.getText());
        else
            ballCount = 1;
        if(ballCount != -1)
            simulation.addBalls(ballCount);
    }

    @FXML
    private void removeBalls()
    {
        int ballCount;
        if(BallsCountTextField.getText().length() > 0)
            ballCount = Integer.parseInt(BallsCountTextField.getText());
        else
            ballCount = 1;
        if(ballCount != -1)
            simulation.removeBalls(ballCount);
    }

    @FXML
    private void startSimulation()
    {
        if(DrawingModeToggle.isSelected())
            simulation.setDrawMode(true);
        else
            simulation.setDrawMode(false);
        simulation.start();
    }

    @FXML
    private void stopSimulation()
    {
        simulation.stop();
    }

    @FXML
    private void toggleDrawMode()
    {
        simulation.setDrawMode(DrawingModeToggle.isSelected());
    }
}
