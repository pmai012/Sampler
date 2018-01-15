import ddf.minim.AudioOutput;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

/**
 * Created by Julian on 11.01.2018.
 */
public class Player  {

    private AudioOutput output;
    SimpleAudioPlayer player;
    SimpleMinim minim;



    public void setFile(String path){
        minim = new SimpleMinim(true);
        player = minim.loadMP3File(path);
        output = minim.getLineOut();

    }

    public AudioOutput getOutput() {
        return output;
    }
}
