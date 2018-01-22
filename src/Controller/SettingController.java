package Controller;

import View.MainFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Created by User on 21.12.2017.
 */
public class SettingController {

Stage stage = null;



    String savelocation;

    public SettingController(Stage stage){
        this.stage = stage;
    }

    public String getSavelocation() {
        return savelocation;
    }



    public EventHandler<ActionEvent> openSaveLocation = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            SaveOpenDialog directionDialog = new SaveOpenDialog();
            savelocation = directionDialog.DirectionDialog(stage,"Geben Sie den Speicherort an");

        }
    };


}



