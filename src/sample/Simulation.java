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
//TODO: Switch from canvas to individual javafx.scene.circle (one per ball, stored in ball object
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
    May be useful again after switching to circles
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

        CentralBall cb = new CentralBall();
        cb.tick();
        this.centralBalls.add(cb);
        cb = new CentralBall();
        cb.tick();
        this.centralBalls.add(cb);

        for (int i = 0; i < BallConstraints.BALL_STARTING_AMOUNT; i++)
        {
            Ball b = new Ball();
            b.chooseColorSource(centralBalls.get(0),centralBalls.get(1));
            this.balls.add(b);
        }

        timer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                /*secondsElapsed = (double)(now - lastGameTime) / 1000000000.0d;
                lastGameTime = now;*/

                //Comment for fancy drawings
                graphicsContext.clearRect(0, 0, GlobalConstraints.CANVAS_WIDTH, GlobalConstraints.CANVAS_HEIGHT);
                updateSimulation();
                renderSimulation();
            }
        };
    }

    private void updateSimulation()
    {
        if(rng.nextInt(100) > 80 && BallConstraints.BALL_MAX_AMOUNT > balls.size())
        {
            Ball b = new Ball();
            b.chooseColorSource(centralBalls.get(0),centralBalls.get(1));
            balls.add(b);
        }

        ArrayList<Ball> deadBalls = new ArrayList<Ball>();
        for(Ball b : balls)
        {
            b.tick();
            if(b.isDead() && balls.size() > BallConstraints.BALL_MIN_AMOUNT)
                deadBalls.add(b);
        }

        boolean oneOrMoreChangedColor = false;
        for (CentralBall cb : centralBalls)
        {
            cb.tick();
            if(cb.hasChangedColor())
                oneOrMoreChangedColor = cb.hasChangedColor();
        }

        balls.removeAll(deadBalls);

        if(oneOrMoreChangedColor)
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
            graphicsContext.setLineWidth(0.5);
            graphicsContext.strokeOval(b.getX(),
                                       b.getY(),
                                       BallConstraints.BALL_RADIUS*2,
                                       BallConstraints.BALL_RADIUS*2);
        }


        for (CentralBall cb: centralBalls)
        {
            graphicsContext.setFill(cb.color());
            graphicsContext.fillOval(cb.getX(),
                                     cb.getY(),
                                     BallConstraints.BALL_RADIUS*3,
                                     BallConstraints.BALL_RADIUS*3);
            graphicsContext.setLineWidth(2.0);
            graphicsContext.strokeOval(cb.getX(),
                                     cb.getY(),
                                     BallConstraints.BALL_RADIUS*3,
                                     BallConstraints.BALL_RADIUS*3);
        }
    }
}
