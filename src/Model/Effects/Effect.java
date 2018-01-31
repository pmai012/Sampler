package Model.Effects;

import Model.Pad;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import ddf.minim.UGen;

/**
 * Created by Pascal on 05.01.2018.
 */
public interface Effect {


    String getName();
    UGen getEffect();
    void patchToOutput(Pad actualPad);
    double[] returnValues();
}
