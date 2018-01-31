package Model.Effects;

import Model.Pad;
import ddf.minim.UGen;
import ddf.minim.ugens.Flanger;

/**
 * Created by Pascal on 17.01.2018.
 */
public class FlangerEffect implements Effect{
    private Flanger flanger;
    private String name;
    private float delay;
    private float rate;
    private float depth;
    private float feedback;
    private float wet;
    private float dry;

    public FlangerEffect(float delay, float rate, float depth, float feedback, float wet, float dry)
    {
        flanger = new Flanger(delay,rate,depth,feedback,wet,dry);
        this.delay = delay;
        this.rate = rate;
        this.depth = depth;
        this.feedback = feedback;
        this.wet = wet;
        this.dry = dry;
        name = "Flanger";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UGen getEffect() {
        return flanger;
    }

    @Override
    public void patchToOutput(Pad actualPad) {
            actualPad.playSound(flanger);
    }

    @Override
    public double[] returnValues() {

        return new double[]{delay,rate,depth,feedback,wet,dry};
    }
}
