package Model.Effects;

import Model.Pad;
import ddf.minim.ugens.Flanger;

/**
 * Created by Pascal on 17.01.2018.
 */
public class FlangerEffect extends Effect{
    private Flanger flanger;

    public FlangerEffect(float delay, float rate, float depth, float feedback, float wet, float dry)
    {
        flanger = new Flanger(delay,rate,depth,feedback,wet,dry);
    }
    @Override
    void patchToOutput(Pad actualPad) {
            actualPad.playSound(flanger);
    }
}
