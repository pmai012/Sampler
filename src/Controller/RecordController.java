package Controller;


import Model.Record;
import ddf.minim.AudioOutput;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by User on 21.12.2017.
 */
public class RecordController {
    private AudioOutput recordInput;
    Record record;


    public RecordController(PadController ref)
    {
        record = new Record(ref.getGlobalOut());
    }
    public EventHandler<MouseEvent> recordClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
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
    };
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
