package Model;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import ddf.minim.AudioOutput;
import ddf.minim.AudioPlayer;
import ddf.minim.spi.AudioOut;
import ddf.minim.ugens.Delay;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;


import java.io.IOException;

/**
 * Created by User on 21.12.2017.
 */
public class Pad {

    private Mp3File file;
    private long startpoint;
    private long endpoint;
    private AudioOutput audioOut;


    public Pad() {
    }


    public AudioOutput getoutput(){
        return audioOut;
    }

    public void setMp3File(Mp3File file) {
        this.file = file;
    }

    public Mp3File getMp3File() {
        return file;
    }


    public void loadSound(String path) {
        try {
            file = new Mp3File(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
        SimpleMinim simpleMinim = new SimpleMinim(true);
        simpleMinim.loadMP3File(path);
        audioOut = simpleMinim.getLineOut();

    }

    public void setStartpoint(long time) {

        startpoint = time;
    }

    public void setEndpoint(long time) {
        endpoint = time;
    }
}
