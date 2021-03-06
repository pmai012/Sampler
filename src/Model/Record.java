package Model;
import Controller.PadController;
import ddf.minim.AudioOutput;
import ddf.minim.AudioRecorder;
import ddf.minim.Minim;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

import java.util.Observable;
import java.util.Observer;


/**
 * Created by User on 21.12.2017.
 */
public class Record extends Observable{

    private AudioRecorder record;
    private Minim minim;
    private String recordPath = "";
    private Observer observer;

    public Record(AudioOutput soundToRecord, Observer observer, String path){
        this.minim = new SimpleMinim(true);
        this.record = minim.createRecorder(soundToRecord, path);
        this.observer = observer;
        this.addObserver(this.observer);
    }
   /* public Record(AudioOutput soundToRecord, Observer observer){
        this.minim = new SimpleMinim(true);
        this.record = minim.createRecorder(soundToRecord, recordPath);
        this.observer = observer;
        this.addObserver(this.observer);
    }*/
    public void setRecordPath(String path){
        this.recordPath = path;
    }

    public boolean isRecording() {return record.isRecording();}

    public void startRecording() {
        record.beginRecord();
    }

    public void stopRecording() {
        record.endRecord();
        record.save();
    }
    public String getRecordPath(){
        return this.recordPath;
    }

    public void sendupdate(){
        if(isRecording()){
            setChanged();
            notifyObservers("record");
        }
        else{
            setChanged();
            notifyObservers("notrecord");
        }
    }
}
