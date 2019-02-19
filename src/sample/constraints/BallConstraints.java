package sample.constraints;

/**
 * Created by Krzysztof 'impune_pl' on 19.02.2019.
 */
public class BallConstraints
{
    public final static int BALL_RADIUS = 5;
    public final static int BALL_VELOCITY_CHANGE_BOUND = 100; //max value from RNG
    public final static int BALL_VELOCITY_CHANGE_MIN = 80; //will change velocity if value > than this
    public final static int BALL_VELOCITY_MAX_CHANGE = 3; //max velocity in one direction (x or y)
    public final static int CENTRAL_BALL_COLOR_CHANGE_BOUND = 100; //max value from RNG
    public final static int CENTRAL_BALL_COLOR_CHANGE_MIN = 98; //will change color if value > than this
    public final static int BALL_MAX_AMOUNT = 200;
    public final static int BALL_STARTING_AMOUNT = 100;
}
