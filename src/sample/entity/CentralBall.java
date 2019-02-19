package sample.entity;

/**
 * Created by Krzysztof 'impune_pl' on 18.02.2019.
 */
public class CentralBall extends Ball
{
    public CentralBall()
    {
        super();
    }

    public void tick()
    {

        //WAŻNE  - losowanie zmiany koloru
        //losowanie zmiany prędkości z super
        super.move();
    }
}
