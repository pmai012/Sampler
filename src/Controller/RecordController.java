package Controller;

import Model.Record;
import ddf.minim.AudioOutput;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.Observable;


public class RecordController extends Observable{
    private AudioOutput recordInput;
    Record record;


    public RecordController(PadController ref)
    {
        record = new Record(ref.getGlobalOut());
    }

    public EventHandler<MouseEvent> recordClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            makerecord();
            setChanged();
            notifyObservers("record");

        }
    };

    public void setpath(String path){
        record.setRecordPath(path);
    }

    public String getPath(){
        return record.getRecordPath();
    }

    public void makerecord(){
        if (record.isRecording()){
            System.out.println("STOP");
            record.stopRecording();
        }
        else
        {
            System.out.println("START");
            record.startRecording();
        }
    }
    public EventHandler<MouseEvent> stopClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (record.isRecording()){
                System.out.println("STOP");
                record.stopRecording();



            }
        }
    };
}
