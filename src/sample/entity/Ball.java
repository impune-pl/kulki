package sample.entity;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.paint.Color;
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

    public Ball()
    {
        rng = new Random();
        y = rng.nextInt(GlobalConstraints.CANVAS_HEIGHT - GlobalConstraints.BALL_RADIUS * 2)
                + GlobalConstraints.BALL_RADIUS;
        x = rng.nextInt(GlobalConstraints.CANVAS_WIDTH - GlobalConstraints.BALL_RADIUS * 2)
                + GlobalConstraints.BALL_RADIUS;
        velocity = new Vec2d(rng.nextInt(3)+1,
                             rng.nextInt(3)+1);
    }

    public void tick()
    {
        //losowanie zmiany kierunku w zależności od pozycji centrów - jak zrobić?
        //losowanie zmiany prędkości
        move();
    }

    protected void move()
    {
        if(x + velocity.x < 0)
        {
            x = GlobalConstraints.BALL_RADIUS;
            bounce(GlobalConstraints.BOUNDARIES.LEFT);
        }
        else if(x + velocity.x > GlobalConstraints.CANVAS_WIDTH)
        {
            x = GlobalConstraints.CANVAS_WIDTH - GlobalConstraints.BALL_RADIUS;
            bounce(GlobalConstraints.BOUNDARIES.RIGHT);
        }
        else
            x += velocity.x;

        if(y + velocity.y < 0)
        {
            y = GlobalConstraints.BALL_RADIUS;
            bounce(GlobalConstraints.BOUNDARIES.TOP);
        }
        else if(y + velocity.y > GlobalConstraints.CANVAS_HEIGHT)
        {
            y = GlobalConstraints.CANVAS_WIDTH - GlobalConstraints.BALL_RADIUS;
            bounce(GlobalConstraints.BOUNDARIES.BOTTOM);
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
}
