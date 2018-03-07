package Model.Effects;

import Model.Pad;
import ddf.minim.UGen;
import ddf.minim.ugens.Gain;


public class GainEffect implements Effect{
    private Gain gain;
    private String name;
    private float dBValue;

    public GainEffect(){
        this(0.f);
    }

    public GainEffect(float dBValue){
        this.gain = new Gain(dBValue);
        this.name = "Gain";
        this.dBValue = dBValue;

    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public UGen getEffect() {
        return gain;
    }

    @Override
    public void patchToOutput(Pad actualPad) {
            actualPad.playSound(gain);
    }

    @Override
    public double[] returnValues() {
        return new double[]{dBValue};
    }
}
