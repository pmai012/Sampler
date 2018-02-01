package Model;

import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

import javax.sound.midi.MidiDevice;


/**
 * Created by User on 30.01.2018.
 */
public class BeatsperMinute {
    private int bpm;
    private float breaktime;
    long breakmilli;
    AudioOutput output;
    boolean playing = false;


    public BeatsperMinute(int Bpm, AudioOutput global) {
        super();
        Minim minim = new SimpleMinim(true);
        output = minim.getLineOut();
        setBpm(Bpm);//100;
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
                    output.setTempo(bpm);


                    output.playNote(21000);


                }
            }
        };
        play.start();
        return output;
    }

    public int getBpm() {
        return bpm;
    }

    ;

    public void setBpm(int bpm) {
        this.bpm = bpm; //default bei 100
        breaktime = (60.0f / bpm);
        breakmilli = (long) (breaktime * 1000);
    }


    public void stop() {
        playing = false;
    }
}
