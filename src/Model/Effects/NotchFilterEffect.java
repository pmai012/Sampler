package Model.Effects;

import Model.Pad;
import ddf.minim.UGen;
import ddf.minim.effects.NotchFilter;

/**
 * Created by Pascal on 17.01.2018.
 */
public class NotchFilterEffect implements Effect{

    private NotchFilter notchFilter;
    private String name;
    private float cutoff;
    private float bandwidth;
    private float sampleRate;

    public NotchFilterEffect( float cutoff,float bandwidth, float sampleRate)
    {
        notchFilter = new NotchFilter(cutoff, bandwidth, sampleRate);
        name = "NotchFilter";
        this.cutoff = cutoff;
        this.bandwidth = bandwidth;
        this.sampleRate = sampleRate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UGen getEffect() {
        return notchFilter;
    }

    @Override
    public void patchToOutput(Pad actualPad) {actualPad.playSound(notchFilter);}

    @Override
    public double[] returnValues() {
        return new double[]{cutoff,bandwidth,sampleRate};
    }
}
