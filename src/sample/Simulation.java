package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.constraints.BallConstraints;
import sample.constraints.GlobalConstraints;
import sample.entity.Ball;
import sample.entity.CentralBall;

import java.util.ArrayList;
import java.util.Random;

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
    private ArrayList<CentralBall> centralBalls;
    /*Would be useful if i decided to move balls based on time instead of frames (lines 68 and 69 too)
    private long lastGameTime;
    private double secondsElapsed;
    */
    private Random rng;

    public void start()
    {
        if(isRunning)
            return;

        //Optional additional actions before starting

        timer.start();
        isRunning = true;
    }

    public void stop()
    {
        if(!isRunning)
            return;
        timer.stop();
        isRunning = false;
    }

    public Simulation(final Canvas canvas)
    {
        this.rng = new Random();
        this.canvas = canvas;

        this.graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setStroke(Color.BLACK);

        this.balls = new ArrayList<Ball>();
        this.centralBalls = new ArrayList<CentralBall>();

        this.centralBalls.add(new CentralBall());
        this.centralBalls.add(new CentralBall());

        for (int i = 0; i < BallConstraints.BALL_STARTING_AMOUNT; i++)
        {
            this.balls.add(new Ball());
        }

        timer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                /*secondsElapsed = (double)(now - lastGameTime) / 1000000000.0d;
                lastGameTime = now;*/
                graphicsContext.clearRect(0, 0, GlobalConstraints.CANVAS_WIDTH, GlobalConstraints.CANVAS_HEIGHT);
                updateSimulation();
                renderSimulation();
            }
        };
    }

    private void updateSimulation()
    {
        if(rng.nextInt(100) > 80 && BallConstraints.BALL_MAX_AMOUNT > balls.size())
            balls.add(new Ball());

        ArrayList<Ball> deadBalls = new ArrayList<Ball>();
        for(Ball b : balls)
        {
            b.tick();
            if(b.isDead())
                deadBalls.add(b);
        }
        for (CentralBall cb : centralBalls)
        {
            cb.tick();
        }

        balls.removeAll(deadBalls);

        if(centralBalls.get(0).hasChangedColor() || centralBalls.get(1).hasChangedColor())
        {
            for (Ball b: balls)
            {
                b.chooseColorSource(centralBalls.get(0), centralBalls.get(1));
            }
        }
    }

    private void renderSimulation()
    {
        for (Ball b :balls)
        {
            graphicsContext.setFill(b.color());
            graphicsContext.fillOval(b.getX(),
                                     b.getY(),
                                     BallConstraints.BALL_RADIUS*2,
                                     BallConstraints.BALL_RADIUS*2);
            graphicsContext.setLineWidth(1.0);
            graphicsContext.strokeOval(b.getX(),
                                       b.getY(),
                                       BallConstraints.BALL_RADIUS*2,
                                       BallConstraints.BALL_RADIUS*2);
        }


        for (CentralBall b: centralBalls)
        {
            graphicsContext.setFill(b.color());
            graphicsContext.fillOval(b.getX(),
                                     b.getY(),
                                     BallConstraints.BALL_RADIUS*3,
                                     BallConstraints.BALL_RADIUS*3);
            graphicsContext.setLineWidth(2.0);
            graphicsContext.strokeOval(b.getX(),
                                     b.getY(),
                                     BallConstraints.BALL_RADIUS*3,
                                     BallConstraints.BALL_RADIUS*3);
        }
    }
}
