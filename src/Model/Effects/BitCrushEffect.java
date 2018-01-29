package Model.Effects;

import Model.Pad;
import ddf.minim.UGen;
import ddf.minim.ugens.BitCrush;

/**
 * Created by Pascal on 17.01.2018.
 */
public class BitCrushEffect extends Effect {
    private BitCrush bitcrush;

    public BitCrushEffect(float bitRes, float bitRate)
    {
        this.bitcrush = new BitCrush(bitRes, bitRate);
    }

    @Override
    public UGen getEffect() {
        return bitcrush;
    }

    @Override
    void patchToOutput(Pad actualPad) {
        actualPad.playSound(bitcrush);
    }
}
