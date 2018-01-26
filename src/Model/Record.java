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
    private String defaultPath = System.getProperty("user.home").concat("//Music") + "/myrecording.wav";
    private String recordPath;


    public Record(AudioOutput soundToRecord){
        minim = new SimpleMinim(true);
        record = minim.createRecorder(soundToRecord, defaultPath);
    }

    public void setRecordPath(String path){
        this.recordPath = path;
    }

    public boolean isRecording() {return record.isRecording();}

    public void startRecording() {record.beginRecord();}

    public void stopRecording() {
        record.endRecord();
        record.save();
    }
}
