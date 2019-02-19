package sample.entity;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.paint.Color;
import sample.constraints.BallConstraints;
import sample.constraints.GlobalConstraints;

import java.util.Random;

/**
 * Created by Krzysztof 'impune_pl' on 18.02.2019.
 */
public class Ball
{
    protected double x;
    protected double y;

    protected Random rng;

    protected Color color;

    protected Vec2d velocity;

    private long lifetime;

    private boolean isDead;

    public Ball()
    {
        rng = new Random();
        y = rng.nextInt(GlobalConstraints.CANVAS_HEIGHT - BallConstraints.BALL_RADIUS * 2)
                + BallConstraints.BALL_RADIUS;
        x = rng.nextInt(GlobalConstraints.CANVAS_WIDTH - BallConstraints.BALL_RADIUS * 2)
                + BallConstraints.BALL_RADIUS;
        int directionX = rng.nextBoolean() ? -1 : 1;
        int directionY = rng.nextBoolean() ? -1 : 1;
        velocity = new Vec2d(rng.nextInt(3) * directionX,
                             rng.nextInt(3) * directionY);
        isDead = false;
        lifetime = 1;
    }

    public void tick()
    {
        isDead = shouldDie();
        randomizeVelocity();
        move();
        lifetime++;
    }

    private boolean shouldDie()
    {
        if(rng.nextInt((int)(lifetime + lifetime * 0.01))>lifetime)
            return true;
        return false;
    }

    public void chooseColorSource(CentralBall a, CentralBall b)
    {
        int distanceToA = (int)a.distance(x,y);
        int distanceToB = (int)b.distance(x,y);
        int max = distanceToA + distanceToB;
        if(rng.nextInt(max) > distanceToA)
            color = a.color;
        else
            color = b.color;
    }

    protected void randomizeVelocity()
    {
        int directionX = rng.nextBoolean() ? -1 : 1;
        int directionY = rng.nextBoolean() ? -1 : 1;
        if(rng.nextInt(BallConstraints.BALL_VELOCITY_CHANGE_BOUND) > BallConstraints.BALL_VELOCITY_CHANGE_MIN)
            velocity = new Vec2d(rng.nextInt(BallConstraints.BALL_VELOCITY_MAX_CHANGE )* directionX,
                                 rng.nextInt(BallConstraints.BALL_VELOCITY_MAX_CHANGE )* directionY);
    }

    public boolean isDead()
    {
        return  isDead;
    }

    protected void move()
    {
        if(x + velocity.x <= 0)
        {
            x = BallConstraints.BALL_RADIUS;
            velocity = bounce(GlobalConstraints.BOUNDARIES.LEFT);
            return;
        }
        else if(x + velocity.x >= GlobalConstraints.CANVAS_WIDTH - BallConstraints.BALL_RADIUS*2)
        {
            x = GlobalConstraints.CANVAS_WIDTH - BallConstraints.BALL_RADIUS*2;
            velocity = bounce(GlobalConstraints.BOUNDARIES.RIGHT);
            return;
        }
        else
            x += velocity.x;

        if(y + velocity.y <= 0)
        {
            y = BallConstraints.BALL_RADIUS;
            velocity = bounce(GlobalConstraints.BOUNDARIES.TOP);
            return;
        }
        else if(y + velocity.y >= GlobalConstraints.CANVAS_HEIGHT - BallConstraints.BALL_RADIUS*2)
        {
            y = GlobalConstraints.CANVAS_HEIGHT - BallConstraints.BALL_RADIUS*2;
            velocity = bounce(GlobalConstraints.BOUNDARIES.BOTTOM);
            return;
        }
        else
            y += velocity.y;
    }

    protected Vec2d bounce(GlobalConstraints.BOUNDARIES boundary)
    {
        switch(boundary)
        {
            case LEFT:
                return new Vec2d(-velocity.x, velocity.y);
            case TOP:
                return new Vec2d(velocity.x, -velocity.y);
            case RIGHT:
                return new Vec2d(-velocity.x, velocity.y);
            case BOTTOM:
                return new Vec2d(velocity.x, -velocity.y);
        }
        return new Vec2d();
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public Color color()
    {
        return color;
    }
}
