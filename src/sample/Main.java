package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.constraints.GlobalConstraints;

public class Main extends Application {

    AnimationTimer simulationLoop;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle(GlobalConstraints.APPLICATION_NAME);
        scene = new Scene(root, GlobalConstraints.SCENE_WIDTH, GlobalConstraints.SCENE_HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        //simulationLoop.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
