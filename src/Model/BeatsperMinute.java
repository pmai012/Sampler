package Model;

import ddf.minim.AudioOutput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.ugens.FilePlayer;
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
    AudioPlayer player;
    Minim minim;
    Oscil bam;
    boolean playing = false;

    public BeatsperMinute(int Bpm, AudioOutput global) {
        super();
        output = global;
        setBpm(Bpm);
        minim = new SimpleMinim(true);
        player = minim.loadFile("F:\\GitHub\\Sampler\\src\\Sound\\KR Bass Drum.wav");

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
                    player.play();
                }
            }
        };
        play.start();
        return null;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
        breaktime = 60 / bpm;
        breakmilli = (long) (breaktime * 1000);
    }


    public void stop() {
        playing = false;
    }
}
