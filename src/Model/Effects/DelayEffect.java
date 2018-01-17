package Model.Effects;

import Model.Pad;
import ddf.minim.UGen;
import ddf.minim.ugens.*;

public class DelayEffect extends Effect {

    Delay delay;

    public DelayEffect(float rate, float decay){
        delay = new Delay( rate, decay,false,true);
    }

    @Override
    void patchToOutput(Pad actualPad) {
        actualPad.playSound(delay);
    }
}
