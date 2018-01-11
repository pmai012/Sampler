import ddf.minim.AudioOutput;
import ddf.minim.AudioPlayer;
import ddf.minim.spi.AudioOut;
import ddf.minim.spi.AudioRecordingStream;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

/**
 * Created by Julian on 11.01.2018.
 */
public class Player extends SimpleMinim {
    private AudioOutput audioOutput;
    private SimpleMinim minim;

    public void setFile(String path){
        minim.loadMP3File(path);
        super = minim;
    }

    public AudioOutput getAudioOutput() {

        return audioOutput;
    }

}
