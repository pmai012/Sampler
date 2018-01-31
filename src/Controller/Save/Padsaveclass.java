package Controller.Save;

/**
 * Created by User on 25.01.2018.
 */

import Controller.RecordController;
import Model.Pad;
import ddf.minim.AudioOutput;
import ddf.minim.UGen;
import javafx.scene.input.KeyCode;

import java.io.Serializable;
import java.util.List;
import java.util.Observer;


class Padsaveclass implements Serializable {
    /**
     * Nur Serializierbare Objekte!!!!
     */
    private int startpoint = 0;
    private long endpoint;
    private String path ;
    private List<UGen> effekte;
    private String savepath;
    private KeyCode keyCode;



    public Padsaveclass(Pad save, String recordpath){
        if (save != null) {
            startpoint = save.getStartpoint();
            endpoint = save.getEndpoint();
            path = save.getPath();
            effekte = save.getEffekte();
            savepath = recordpath;
            this.keyCode = save.getShortcut();
        }
    }

  public String getPath(){
        return path;
  }

    public String getSavepath(){
        return savepath;
    }

    public Pad loadPad(Observer observer, AudioOutput out){
        if (path != null) {


            Pad ausgabe = new Pad(path, observer,out);
            ausgabe.setStartpoint(startpoint);
            ausgabe.setEndpoint(endpoint);
            ausgabe.setShortcut(keyCode);
            return ausgabe;
        } else{
            return null;
        }
    }
}
