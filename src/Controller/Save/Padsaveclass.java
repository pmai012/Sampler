package Controller.Save;

import Controller.Controller;
import Model.Pad;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import ddf.minim.UGen;
import ddf.minim.spi.AudioOut;
import ddf.minim.ugens.FilePlayer;

import java.io.Serializable;
import java.util.List;
import java.util.Observer;


/**
 * Created by User on 23.01.2018.
 */
public class Padsaveclass implements Serializable {
    /**
     * Nur Serializierbare Objekte!!!!
     */
    private int startpoint = 0;
    private long endpoint;
    private String path ;
    private List<UGen> effekte;


    public Padsaveclass(Pad save){
        if (save != null) {
            startpoint = save.getStartpoint();
            endpoint = save.getEndpoint();
            path = save.getPath();
            effekte = save.getEffekte();
        }
    }

    public Pad loadPad(Observer observer, AudioOutput globalOut){
        if (path != null) {
            Pad ausgabe = new Pad(path, observer, globalOut);
            ausgabe.setStartpoint(startpoint);
            ausgabe.setEndpoint(endpoint);

        return ausgabe;
        } else{
            return null;
        }
    }
}
