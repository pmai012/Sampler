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
                    output.playNote(200000.0f );
                }
            }
        };
        play.start();
        return output;
    }

    public int getBpm(){
      return bpm;
    };

    public void setBpm(int bpm) {
        this.bpm = bpm; //default bei 100
        breaktime = (60.0f / bpm);
        breakmilli = (long) (breaktime * 1000);
    }


    public void stop() {
        playing = false;
    }
}
