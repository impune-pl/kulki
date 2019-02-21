package sample.constraints;

/**
 * Created by Krzysztof 'impune_pl' on 19.02.2019.
 */
public class BallConstraints
{
    public final static int BALL_RADIUS = 5;
    public final static double BALL_OUTLINE = 0.5;
    public final static int BALL_VELOCITY_CHANGE_BOUND = 100; //max value from RNG
    public final static int BALL_VELOCITY_CHANGE_MIN = 80; //will change velocity if value > than this
    public final static int BALL_VELOCITY_MAX_CHANGE = 3; //max velocity in one axis counting from 0 (bounds are n and -n) (x or y)
    public final static int BALL_MAX_AMOUNT = 30000; //max amount at given time
    public final static int BALL_MIN_AMOUNT = 30000; //will never drop below that amount
    public final static int BALL_STARTING_AMOUNT = 30000; //staring amount

    public final static double CENTRAL_BALL_OUTLINE = 2.0;
    public final static int CENTRAL_BALL_COLOR_CHANGE_BOUND = 100; //max value from RNG
    public final static int CENTRAL_BALL_COLOR_CHANGE_MIN = 98; //will change color if value > than this
    public final static int CENTRAL_BALL_AMOUNT = 2; //amount of central balls, WARNING: DO NOT CHANGE, DO NOT USE, NOT IMPLEMENTED YET
    public final static int CENTRAL_BALLS_MIN_FRAMES_BETWEEN_COLOR_CHANGE = 100; //regulates framelapse between color changes of central balls
}
