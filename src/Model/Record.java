package Model;
import Controller.PadController;
import Controller.Save.SaveOpenDialog;
import Controller.SettingController;
import ddf.minim.AudioOutput;
import ddf.minim.AudioRecorder;
import ddf.minim.Minim;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;


/**
 * Created by User on 21.12.2017.
 */
public class Record extends Observable{

    private AudioRecorder record;
    private Minim minim;
    private String defaultPath = System.getProperty("user.home").concat("//Music") + "/SamplerSoundfiles/myrecording.wav";
    private Observer observer;



    public Record(AudioOutput soundToRecord, Observer observer, String path){
        this.minim = new SimpleMinim(true);
        this.record = minim.createRecorder(soundToRecord, path);
        this.observer = observer;
        this.addObserver(this.observer);
    }

    public Record(AudioOutput soundToRecord, Observer observer){
        this.minim = new SimpleMinim(true);
        this.record = minim.createRecorder(soundToRecord, defaultPath);
        this.observer = observer;
        this.addObserver(this.observer);

    }


    public boolean isRecording() {return record.isRecording();}

    public void startRecording() {record.beginRecord();}

    public void stopRecording() {
        record.endRecord();


        record.save();
    }
    public String getDefaultPath(){
            return this.defaultPath;
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
