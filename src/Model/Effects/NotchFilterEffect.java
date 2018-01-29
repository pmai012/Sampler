package Model.Effects;

import Model.Pad;
import ddf.minim.UGen;
import ddf.minim.effects.NotchFilter;

/**
 * Created by Pascal on 17.01.2018.
 */
public class NotchFilterEffect extends Effect{

    private NotchFilter notchFilter;

    public NotchFilterEffect( float cutoff,float bandwidth, float sampleRate)
    {
        notchFilter = new NotchFilter(cutoff, bandwidth, sampleRate);
    }

    @Override
    public UGen getEffect() {
        return notchFilter;
    }

    @Override
    void patchToOutput(Pad actualPad) {actualPad.playSound(notchFilter);}
}
