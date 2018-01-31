package Model.Effects;

import Model.Pad;
import ddf.minim.UGen;
import ddf.minim.ugens.BitCrush;

/**
 * Created by Pascal on 17.01.2018.
 */
public class BitCrushEffect implements Effect {
    private BitCrush bitcrush;
    private String name;
    private float bitRes;
    private float bitRate;

    public BitCrushEffect(float bitRes, float bitRate)
    {
        this.bitcrush = new BitCrush(bitRes, bitRate);
        name = "BitCrush";
        this.bitRes = bitRes;
        this.bitRate = bitRate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UGen getEffect() {
        return bitcrush;
    }

    @Override
    public void patchToOutput(Pad actualPad) {
        actualPad.playSound(bitcrush);
    }

    @Override
    public double[] returnValues() {
        return new double[]{bitRes,bitRate};
    }
}
