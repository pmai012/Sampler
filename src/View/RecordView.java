package View;

import Controller.PadController;
import Controller.RecordController;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Observer;

public class RecordView extends Pane {

    private VBox rootRV;
    private HBox recordButtons;
    private HBox recordInfos;
    private HBox recordBPM;
    private Button record;
    private Button stop;
    private Label time;
    private Label bpm1;
    private Label bpm2;
    private TextField bpmTf;
    private RecordController recordController;
    private Observer observer;
    private PadController padController;
    private Tooltip bpmTip;
    private Tooltip recordTip;

    public RecordView(PadController padController, Observer obs){
        this.padController = padController;
        observer = obs;
        rootRV = new VBox(20);
        time = new Label("--:--/--:--");
        bpm1 = new Label("120");
        bpm2 = new Label("BPM");
        bpmTf = new TextField(bpm1.getText());
        bpmTf.setPrefWidth(40);
        recordInfos = new HBox(20);
        recordButtons = new HBox(30);
        recordBPM = new HBox(10);
        record = new Button();
        stop = new Button();
        bpmTip = new Tooltip("Doppelklick zum ändern");
        recordTip = new Tooltip("Drücken zum aufnehmen");
        bpmTip.getStyleClass().add("tooltip");
        recordTip.getStyleClass().add("tooltip");
        bpm1.textProperty().bind(bpmTf.textProperty());
        bpm1.setTooltip(bpmTip);
        record.setTooltip(recordTip);
        rootRV.setAlignment(Pos.BASELINE_CENTER);
        recordInfos.getChildren().addAll(time, recordBPM);
        recordBPM.getChildren().addAll(bpm1,bpm2);
        recordButtons.getChildren().addAll(record, stop);
        this.getChildren().add(rootRV);
        rootRV.getChildren().addAll(recordInfos, recordButtons);
        recordButtons.getStyleClass().add("recordButtons");
        recordInfos.getStyleClass().add("recordInfos");
        recordBPM.getStyleClass().add("recordBPM");
        record.getStyleClass().add("recordButton");
        stop.getStyleClass().add("stopButton");
        time.getStyleClass().addAll("title","time");
        bpm1.getStyleClass().addAll("title","time");
        bpm2.getStyleClass().addAll("title","time","textP");

        recordController = new RecordController(padController, observer,this);

        record.addEventHandler(MouseEvent.MOUSE_CLICKED, recordController.recordClicked);
        stop.addEventHandler(MouseEvent.MOUSE_CLICKED, recordController.stopClicked);
        bpm1.addEventHandler(MouseEvent.MOUSE_CLICKED, recordController.changeBPM);
        bpmTf.focusedProperty().addListener(recordController.changeBPMback);
        bpmTf.textProperty().addListener(recordController.checkValue);
        bpmTf.setOnKeyPressed(recordController.changeBPMbackEnter);
    }

    public RecordController getRecordController() {
        return recordController;
    }

    public Label getBpm2() { return bpm2; }

    public TextField getBpmTf() { return bpmTf; }

    public void changeBPMview (){

        if(recordBPM.getChildren().contains(bpm1)){
            recordBPM.getChildren().removeAll(bpm1,bpm2);
            recordBPM.getChildren().addAll(bpmTf,bpm2);
            bpmTf.requestFocus();
            recordBPM.setSpacing(10);
        } else if(recordBPM.getChildren().contains(bpmTf)){
            recordBPM.getChildren().removeAll(bpmTf,bpm2);
            recordBPM.getChildren().addAll(bpm1, bpm2);
            recordBPM.setSpacing(10);
            int value = Integer.parseInt(bpmTf.getText());
            padController.setbeat(value);

        }

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
