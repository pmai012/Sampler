package Model.Effects;

import Model.Pad;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import ddf.minim.UGen;

/**
 * Created by Pascal on 05.01.2018.
 */
public abstract class Effect {

    Minim minim;
    AudioOutput out;

    public Effect()
    {
        minim = new Minim(this);
        out = minim.getLineOut();
    }

    abstract void patchToOutput(Pad actualPad);
}
