package Controller;

import Model.Record;
import ddf.minim.AudioOutput;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.Observable;
import java.util.Observer;


public class RecordController extends Observable{
    private AudioOutput recordInput;
    private Record record;
    private Observer observer;

    public RecordController(PadController ref, Observer observer)
    {
        this.record = new Record(ref.getGlobalOut(),observer);
        this.observer = observer;
    }

    public EventHandler<MouseEvent> recordClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            makerecord();
            record.sendupdate();
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
