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

    Random rng;

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
        if(x + velocity.x < GlobalConstraints.CANVAS_WIDTH)
        {
            x = 0 + GlobalConstraints.BALL_RADIUS;

        }
        else if(x + velocity.x > GlobalConstraints.CANVAS_HEIGHT)
        {
            x = GlobalConstraints.CANVAS_WIDTH - GlobalConstraints.BALL_RADIUS;

        }
        else
            x += velocity.x;

        if(y + velocity.y < GlobalConstraints.CANVAS_WIDTH)
        {
            y = 0 + GlobalConstraints.BALL_RADIUS;

        }
        else if(y + velocity.y > GlobalConstraints.CANVAS_HEIGHT)
        {
            y = GlobalConstraints.CANVAS_WIDTH - GlobalConstraints.BALL_RADIUS;

        }
        else
            y += velocity.y;
    }

    private Vec2d bounce(GlobalConstraints.BOUNDARIES boundary)
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
