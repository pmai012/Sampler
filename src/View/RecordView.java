package View;

import javafx.scene.control.Button;
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


    public RecordView(){
        recordButtons = new HBox(20);
        record = new Button();
        stop = new Button();

        recordButtons.getChildren().addAll(record, stop);
        this.getChildren().add(recordButtons);

        record.getStyleClass().add("recordButton");
        stop.getStyleClass().add("stopButton");

    }
}
