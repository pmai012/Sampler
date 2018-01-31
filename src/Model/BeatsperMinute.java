package Model;

import ddf.minim.AudioOutput;


/**
 * Created by User on 30.01.2018.
 */
public class BeatsperMinute {
    private int bpm;
    private float breaktime;
    long breakmilli;
    AudioOutput output;
    boolean playing = false;
    private String note ="C2";



    public BeatsperMinute(int Bpm, AudioOutput global) {
        super();
        output = global;
        setBpm(Bpm) ;//100;




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
                    output.playNote(200000.0f );//19912.1f
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
