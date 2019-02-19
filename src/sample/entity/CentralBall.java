package sample.entity;

import javafx.scene.paint.Color;
import sample.constraints.BallConstraints;
import sample.constraints.ColorConstraints;

/**
 * Created by Krzysztof 'impune_pl' on 18.02.2019.
 */
public class CentralBall extends Ball
{
    private boolean hasChangedColor;

    private long framesSinceLastColorChange;

    public CentralBall()
    {
        super();
        hasChangedColor = true;
        randomizeColorChange();
        framesSinceLastColorChange = 0;
    }

    public void tick()
    {
        hasChangedColor = false;
        if(rng.nextInt(BallConstraints.CENTRAL_BALL_COLOR_CHANGE_BOUND) > BallConstraints.CENTRAL_BALL_COLOR_CHANGE_MIN && framesSinceLastColorChange > 100)
            randomizeColorChange();
        framesSinceLastColorChange++;
        super.randomizeVelocity();
        super.move();
    }

    private void randomizeColorChange()
    {
        this.color = Color.web(ColorConstraints.colors[rng.nextInt(ColorConstraints.colors.length)]);
        hasChangedColor = true;
        framesSinceLastColorChange = 0;
    }

    public boolean hasChangedColor()
    {
        return hasChangedColor;
    }

    public boolean isDead()
    {
        return false;
    }

    public double distance(double x, double y)
    {
        return Math.sqrt(Math.pow(this.x - x, 2.0d) + Math.pow(this.y - y, 2.0d));
    }

    public void chooseColorSource(CentralBall a, CentralBall b)
    {
        return;
    }
}
