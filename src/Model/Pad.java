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
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by User on 21.12.2017.
 */
public class Pad extends Observable {



    private int startpoint = 0;
    private long endpoint;
    private long presstime = -5;
    private boolean threadrun = false;
    private String path ;
    private AudioOutput audioOut;
    FilePlayer filePlayer;
    Minim minim;
    private Observer observer;
    private int starter = 0;




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


    public Pad(String pathtoSound, Observer observer, AudioOutput globalOut) {
        this.observer = observer;
        path = pathtoSound;
        minim = new SimpleMinim(true);
        audioOut = globalOut;
        filePlayer = new FilePlayer(minim.loadFileStream(pathtoSound, 1024, true));
        this.addObserver(observer);
        System.out.println("Observer: " + countObservers());

    }

    public void playSound() {

       if (starter < 1) {
           filePlayer.play(startpoint);
           filePlayer.patch(audioOut);
           starter++;
       }
    }


    public void playSound(UGen effect) {


        filePlayer.play(startpoint);
        filePlayer.patch(effect).patch(audioOut);
    }

    public void stop()
    {
        filePlayer.pause();
        starter = 0;
        filePlayer = new FilePlayer(minim.loadFileStream(path, 1024, true));

    }

    public void sendupdate(){
        setChanged();
        notifyObservers("pad");

    }

    public AudioOutput getOutput() {
        return audioOut;
    }


    public void setStartpoint(int time) {

        startpoint = time;
    }

    public boolean isPlaying(){

        if (starter >= 1){
            return true;
        }
        return false;
    }

    public void setEndpoint(long time) {
        endpoint = time;
    }



    public void threadstarten() {
        Thread time = new Thread() {
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
                try {
                    this.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        time.start();
    }


    public boolean isNull() {
        if (filePlayer == null){
            return true;
        }else{
            return false;
        }
    }
}