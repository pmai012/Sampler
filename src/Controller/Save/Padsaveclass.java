package Controller.Save;

/**
 * Created by User on 25.01.2018.
 */

import Controller.RecordController;
import Model.Pad;
import ddf.minim.AudioOutput;
import ddf.minim.UGen;

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



    public Padsaveclass(Pad save, String recordpath){
        if (save != null) {
            startpoint = save.getStartpoint();
            endpoint = save.getEndpoint();
            path = save.getPath();
            effekte = save.getEffekte();
            savepath = recordpath;
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

            return ausgabe;
        } else{
            return null;
        }
    }
}
