package sample;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
class Simulation
{
    private boolean isRunning;

    private AnimationTimer timer;

    private GraphicsContext graphicsContext;

    private ArrayList<Ball> balls;
    private ArrayList<CentralBall> centralBalls;
    private Random rng;

    IntegerProperty ballCount;

    private boolean drawMode;

    void start()
    {
        if(isRunning)
            return;
        timer.start();
        isRunning = true;
    }

    void stop()
    {
        if(!isRunning)
            return;
        timer.stop();
        isRunning = false;
    }

    Simulation(final Canvas canvas)
    {
        this.ballCount = new SimpleIntegerProperty();
        this.rng = new Random();

        this.graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setStroke(Color.BLACK);

        this.balls = new ArrayList<>();
        this.centralBalls = new ArrayList<>();

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

        ballCount.set(0);

        timer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                if(!drawMode)
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

        ArrayList<Ball> deadBalls = new ArrayList<>();
        for(Ball b : balls)
        {
            b.tick();
            if(b.isDead() && balls.size() > BallConstraints.BALL_MIN_AMOUNT)
                deadBalls.add(b);
        }
        balls.removeAll(deadBalls);

        boolean oneOrMoreChangedColor = false;
        for (CentralBall cb : centralBalls)
        {
            cb.tick();
            if(cb.hasChangedColor())
                oneOrMoreChangedColor = cb.hasChangedColor();
        }

        ballCount.set(balls.size());
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
            graphicsContext.setLineWidth(BallConstraints.BALL_OUTLINE);
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
            graphicsContext.setLineWidth(BallConstraints.CENTRAL_BALL_OUTLINE);
            graphicsContext.strokeOval(cb.getX(),
                                     cb.getY(),
                                     BallConstraints.BALL_RADIUS*3,
                                     BallConstraints.BALL_RADIUS*3);
        }
    }

    void removeBalls(int ballCount)
    {
        while(balls.size()>0 && ballCount > 0 && balls.size() > BallConstraints.BALL_MIN_AMOUNT)
        {
            balls.remove(0);
            ballCount--;
        }
        renderSimulation();
    }

    void addBalls(int ballCount)
    {
        while(ballCount > 0 && balls.size() < BallConstraints.BALL_MAX_AMOUNT)
        {
            Ball b = new Ball();
            b.chooseColorSource(centralBalls.get(0),centralBalls.get(1));
            balls.add(b);
            ballCount--;
        }
        renderSimulation();
    }

    void setDrawMode(boolean b)
    {
        drawMode = b;
    }
}
