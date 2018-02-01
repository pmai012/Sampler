package Controller;

import Model.Record;
import View.RecordView;
import ddf.minim.AudioOutput;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.util.Observable;
import java.util.Observer;


public class RecordController extends Observable{
    private AudioOutput recordInput;
    private Record record;
    private Observer observer;
    private RecordView view;

    public RecordController(PadController ref, Observer observer, RecordView view)
    {
        this.record = new Record(ref.getGlobalOut(),observer);
        this.observer = observer;
        this.view = view;
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
    public EventHandler<MouseEvent> changeBPM = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if(event.getButton() == MouseButton.PRIMARY){
                if(event.getClickCount() == 2){
                    view.changeBPMview();
                }
            }
        }
    };

    public ChangeListener<Boolean> changeBPMback = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if(newValue){
                System.out.println("focus");
            }
            else{
                System.out.println("not focus");
                view.changeBPMview();
            }
        }
    };

    public EventHandler<KeyEvent> changeBPMbackEnter = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode().equals(KeyCode.ENTER)){
                view.getBpm2().requestFocus();
            }
        }
    };

}
