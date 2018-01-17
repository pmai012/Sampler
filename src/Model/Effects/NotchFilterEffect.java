package Model.Effects;

import Model.Pad;
import ddf.minim.effects.NotchFilter;

/**
 * Created by Pascal on 17.01.2018.
 */
public class NotchFilterEffect extends Effect{

    private NotchFilter notchFilter;

    public NotchFilterEffect(float bandwidth, float cutoff, float sampleRate)
    {
        notchFilter = new NotchFilter(cutoff, bandwidth, sampleRate);
    }

    @Override
    void patchToOutput(Pad actualPad) {actualPad.playSound(notchFilter);}
}
