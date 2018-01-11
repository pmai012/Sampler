package View;

import ddf.minim.AudioOutput;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import ddf.minim.spi.AudioOut;
import ddf.minim.ugens.Delay;
import ddf.minim.ugens.Oscil;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

/**
 * Created by User on 08.01.2018.
 */
public class test {

    public static void main(String[] args){
        SimpleMinim minim = new SimpleMinim(true);
        AudioPlayer player = minim.loadFile("C:\\Users\\User\\Music\\Eminem\\03 - Die Alone [feat. Kobe] [Explicit].mp3");
        AudioOutput out = minim.getLineOut();
        Delay delay = new Delay(0.9f, 0.9f, true,true);
        Oscil oscil = new Oscil(50f,0.8f);
        oscil.patch(delay).patch(out);


    }
}
