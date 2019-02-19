package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import sample.constraints.GlobalConstraints;
import sample.entity.Ball;
import sample.entity.CentralBall;

import java.util.ArrayList;

/**
 * Created by Krzysztof 'impune_pl' on 19.02.2019.
 */
public class Simulation
{
    private boolean isRunning;

    private AnimationTimer timer;

    private Canvas canvas;
    private GraphicsContext graphicsContext;

    private ArrayList<Ball> balls;

    private long lastGameTime;
    private double secondsElapsed;

    public void start()
    {
        if(isRunning)
            return;
        //start the loop
        isRunning = true;
    }

    public void stop()
    {
        if(!isRunning)
            return;
        timer.stop();
    }

    public Simulation(final Canvas canvas)
    {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.balls = new ArrayList<Ball>();

        CentralBall tmp = new CentralBall();
        this.balls.add(tmp);
        tmp = new CentralBall();
        this.balls.add(tmp);

        timer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                secondsElapsed = (double)(now - lastGameTime) / 1000000000.0d;
                lastGameTime = now;
                graphicsContext.clearRect(0, 0, GlobalConstraints.CANVAS_WIDTH, GlobalConstraints.CANVAS_HEIGHT);
                updateSimulation();
                renderSimulation();
            }
        };
    }

    private void updateSimulation()
    {
        for(Ball b : balls)
        {
            b.tick();
        }
    }

    private void renderSimulation()
    {
        for (Ball b :balls)
        {

        }
    }
}
