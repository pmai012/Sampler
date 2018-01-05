package Model.Effects;

import ddf.minim.ugens.*;

public class DelayEffect extends Effect {

    Delay delay;

    public DelayEffect(float rate, float decay){

        super();
        delay = new Delay( rate, decay,true,true);
        delay.patch(out);
    }
}
