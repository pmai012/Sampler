package View;

import Controller.PadController;
import Controller.RecordController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by User on 21.12.2017.
 */
public class RecordView extends Pane {

    private VBox rootRV;
    private HBox recordButtons;
    private Button record;
    private Button stop;
    private RecordController recordController;



    public RecordView(PadController padController){

        rootRV = new VBox(20);
        Label time = new Label("--:--/--:--");
        recordButtons = new HBox(50);
        record = new Button();
        stop = new Button();

        rootRV.setAlignment(Pos.BASELINE_CENTER);
        recordButtons.getChildren().addAll(record, stop);
        this.getChildren().add(rootRV);
        rootRV.getChildren().addAll(time, recordButtons);
        record.getStyleClass().add("recordButton");
        stop.getStyleClass().add("stopButton");
        time.getStyleClass().add("title");
        recordController = new RecordController(record,stop, padController);

    }
}
