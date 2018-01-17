package Controller;

import ddf.minim.AudioOutput;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import org.tritonus.share.sampled.file.AudioOutputStream;

/**
 * Created by Julian on 11.01.2018.
 */
public class Player  {

    private AudioOutput output;
    private SimpleAudioPlayer player;
    private SimpleMinim minim;


    /**
     * Setzt eine Mp3 file fest.
     * @param path Pfad des jeweiligen Titel
     */
    public void setFile(String path){
        minim = new SimpleMinim(true);
        player = minim.loadMP3File(path);
        output = minim.getLineOut();

    }


    /**
     * getOutput gibt den aktuellen AudioOutput her
     * @return Output
     */
    public AudioOutput getOutput() {
        return output;
    }

    public void setAudioOutput(AudioOutput audioOutput){
        this.output = audioOutput;
    }


}
