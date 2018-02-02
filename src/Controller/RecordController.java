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

import java.io.File;
import java.io.FilenameFilter;
import java.util.Observable;
import java.util.Observer;


public class RecordController extends Observable{
    private AudioOutput recordInput;
    private Record record;
    private Observer observer;
    private RecordView view;
    private PadController padController;

    public RecordController(PadController ref, Observer observer, RecordView view)
    {
        this.padController = ref;
        this.record = new Record(padController.getGlobalOut(),observer);
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
            if (!record.getRecordPath().equals("")){
                String path = record.getRecordPath();
                record = null;
                record = new Record(padController.getGlobalOut(),observer, countRecording(path));
                record.startRecording();
            }
            else{
                record.startRecording();
            }
        }
    }
    public EventHandler<MouseEvent> stopClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (record.isRecording()){
                System.out.println("STOP");
                record.stopRecording();
            }
            record.sendupdate();
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

    public ChangeListener<String> checkValue = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if(newValue.matches("//d{1,3}")){
                return;
                //view.getBpmTf().setText(oldValue);
            }
            else{

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
    private String countRecording(String path){
        File file = new File(path);
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String accept = name.toLowerCase();
                if(accept.contains("recording") && accept.contains(".wav"))
                {
                    return true;
                }
                else {
                    return false;
                }
            }
        };
        String[] recordings = file.list(filter);
        return path.concat("//" + "recording("+recordings.length+").wav");
    }

}
