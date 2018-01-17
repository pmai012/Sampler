package Model;

import Controller.PadController;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import ddf.minim.AudioOutput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.UGen;
import ddf.minim.spi.AudioOut;
import ddf.minim.ugens.Delay;
import ddf.minim.ugens.FilePlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;


import java.io.IOException;

/**
 * Created by User on 21.12.2017.
 */
public class Pad {


    private Mp3File file;
    private int startpoint = 0;
    private long endpoint;
    private long presstime = -5;
    private boolean threadrun = false;

    private AudioOutput audioOut;
    FilePlayer filePlayer;
    Minim minim;

    public long getPresstime() {
        return presstime;
    }

    public void setPresstime(long presstime) {
        this.presstime = presstime;
    }

    public boolean isThreadrun() {
        return threadrun;
    }

    public void setThreadrun(boolean threadrun) {
        this.threadrun = threadrun;
    }








    public Pad(String pathtoSound) {
        minim = new SimpleMinim(true);
        audioOut = minim.getLineOut();
        filePlayer = new FilePlayer(minim.loadFileStream(pathtoSound, 1024, true));

    }
    public void playSound(){
        filePlayer.play(startpoint);
        filePlayer.patch(audioOut);
    }
    public void playSound(UGen effect){
        filePlayer.play(startpoint);
        filePlayer.patch(effect).patch(audioOut);
    }

    public void stop(){
       filePlayer.pause();
    }
    public AudioOutput getOutput(){
        return audioOut;
    }



    public void setStartpoint(int time) {

        startpoint = time;
    }

    public void setEndpoint(long time) {
        endpoint = time;
    }



    public Thread time = new Thread() {
            @Override
        public void run() {
                long start = System.currentTimeMillis();
                while (threadrun) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    presstime = System.currentTimeMillis() - start;

                }
            }

    };
}