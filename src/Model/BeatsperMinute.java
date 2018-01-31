package Model;

import ddf.minim.AudioOutput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.ugens.Instrument;
import ddf.minim.ugens.Oscil;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

/**
 * Created by User on 30.01.2018.
 */
public class BeatsperMinute {
    private int bpm;
    private float breaktime;
    long breakmilli;
    AudioOutput output;
    boolean playing = false;
    Oscil oscil;
    AudioPlayer player;


    public BeatsperMinute(int Bpm, AudioOutput global) {
        super();
        output = global;
        setBpm(Bpm) ;//100;

        Minim minim = new SimpleMinim(true);
        player = minim.loadFile("F:\\GitHub\\Sampler\\src\\Sound\\KR Bass Drum.wav");
        oscil = new Oscil(20,0.8f);






    }


    public AudioOutput play() {
        playing = true;

        Thread play = new Thread() {
            @Override
            public void run() {


                while (playing) {
                    try {
                        Thread.sleep(breakmilli);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                 //  oscil.patch(output);
                    output.playNote("b");
                }
            }
        };
        play.start();
        return output;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm; //default bei 100
        breaktime = (60.0f / bpm);
        breakmilli = (long) (breaktime * 1000);
    }


    public void stop() {
        playing = false;
    }
}
