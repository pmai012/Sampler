package Model.Effects;

import ddf.minim.UGen;
import ddf.minim.ugens.*;

public class DelayEffect extends Effect {

    Delay delay;

    public DelayEffect(float rate, float decay){

        super();
        delay = new Delay( rate, decay,true,true);

    }

    @Override
    void patchToOutput(UGen tone) {
        tone.patch(delay).patch(out);
    }
}
