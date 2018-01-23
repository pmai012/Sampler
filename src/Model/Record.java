package Model;
import Controller.PadController;
import ddf.minim.AudioOutput;
import ddf.minim.AudioRecorder;
import ddf.minim.Minim;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;


/**
 * Created by User on 21.12.2017.
 */
public class Record {

    private AudioRecorder record;
    private Minim minim;
    private String recordPath;


    public Record(AudioOutput soundToRecord){
        minim = new SimpleMinim(true);
        record = minim.createRecorder(soundToRecord, recordPath);
    }

    public void setRecordPath(String path){
        this.recordPath = path;
    }
}
