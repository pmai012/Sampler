package View;

import Controller.PadController;
import Controller.RecordController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Observer;

public class RecordView extends Pane {

    private VBox rootRV;
    private HBox recordButtons;
    private HBox recordInfos;
    private Button record;
    private Button stop;
    private Label time;
    private Label bpm1;
    private Label bpm2;
    private TextField bpmTf;
    private RecordController recordController;
    private Observer observer;

    public RecordView(PadController padController, Observer obs){
        observer = obs;
        rootRV = new VBox(20);
        time = new Label("--:--/--:--");
        bpm1 = new Label("100");
        bpm2 = new Label("BPM");
        bpmTf = new TextField(bpm1.getText());
        recordInfos = new HBox(5);
        recordButtons = new HBox(30);
        record = new Button();
        stop = new Button();

        rootRV.setAlignment(Pos.BASELINE_CENTER);
        recordInfos.getChildren().addAll(time, bpm1,bpm2);
        recordButtons.getChildren().addAll(record, stop);
        this.getChildren().add(rootRV);
        rootRV.getChildren().addAll(recordInfos, recordButtons);
        recordButtons.getStyleClass().add("recordButtons");
        recordInfos.getStyleClass().add("recordInfos");
        record.getStyleClass().add("recordButton");
        stop.getStyleClass().add("stopButton");
        time.getStyleClass().addAll("title","time");
        bpm1.getStyleClass().addAll("title","time");
        bpm2.getStyleClass().addAll("title","time","textP");

        recordController = new RecordController(padController, observer);

        record.addEventHandler(MouseEvent.MOUSE_CLICKED, recordController.recordClicked);
        bpm1.addEventHandler(MouseEvent.MOUSE_CLICKED, recordController.changeBPM);
    }

    public RecordController getRecordController() {
        return recordController;
    }


    public void update(Object arg) {
        String command = (String) arg;
        System.out.println("recorder");
        if(command.equals("record")){
            record.getStyleClass().add("recordButtonUsed");
            record.getStyleClass().remove("recordButton");

        }
        if(command.equals("notrecord")){
            record.getStyleClass().add("recordButton");
            record.getStyleClass().remove("recordButtonUsed");


        }
    }
}
