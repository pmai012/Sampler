package Model.Effects;

import Model.Pad;
import ddf.minim.UGen;
import ddf.minim.ugens.*;

public class DelayEffect implements Effect {

    private Delay delay;
    private float decay;
    private float rate;
    private String name;

    public DelayEffect(float rate, float decay){
        this.rate = rate;
        this.decay = decay;
        delay = new Delay( rate, decay,false,true);
        name = "Delay";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UGen getEffect() {
        return delay;
    }

    @Override
    public void patchToOutput(Pad actualPad) {
        actualPad.playSound(delay);
    }

    @Override
    public double[] returnValues() {
        double[] values = new double[]{decay,rate};
        return values;
    }
}
