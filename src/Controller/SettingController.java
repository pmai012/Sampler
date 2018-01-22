package Controller;

import Model.Pad;
import View.MainFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by User on 21.12.2017.
 */
public class SettingController {

    Stage stage = null;
    String savelocation;
    File file;
    String saveFilelocation;
    String saveSourcelocation;
    Pad[] pads;


    public SettingController(Stage stage, Pad[] pads){
        this.stage = stage;
        this.pads = pads;
}

    public String getSavelocation() {
        return savelocation;
    }



    public EventHandler<ActionEvent> openSaveLocation = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            SaveOpenDialog directionDialog = new SaveOpenDialog();
            savelocation = directionDialog.DirectionDialog(stage);

        }
    };

    public EventHandler<ActionEvent> save = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            SaveOpenDialog saveDialog = new SaveOpenDialog();
            saveFilelocation = saveDialog.Savedialog(stage,"Wo möchten sie die Datei speichern?" );
            saveDialog.save(pads,saveFilelocation);

        }
    };

    public EventHandler<ActionEvent> open = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            SaveOpenDialog openDialog = new SaveOpenDialog();
            file = openDialog.OpenDialog(stage);

        pads = openDialog.read(file.getAbsolutePath());

        }
    };

    public EventHandler<ActionEvent> openSourceLocation = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            SaveOpenDialog directionSourcedialog = new SaveOpenDialog();
            saveSourcelocation = directionSourcedialog.DirectionDialog(stage);
        }
    };

    public  EventHandler<ActionEvent> openManual = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

        }
    };


}



