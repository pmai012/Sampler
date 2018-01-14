import ddf.minim.AudioOutput;
import ddf.minim.AudioPlayer;
import ddf.minim.spi.AudioOut;
import ddf.minim.spi.AudioRecordingStream;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.application.Platform;

/**
 * Created by Julian on 11.01.2018.
 */
public class Player  {
    private AudioOutput audioOutput;
    SimpleAudioPlayer player;
    SimpleMinim minim;

    public void setFile(String path){
        minim = new SimpleMinim(true);
        player = minim.loadMP3File(path);

    }

    public AudioOutput getAudioOutput() {

        return audioOutput;
    }

}
