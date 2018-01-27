package Controller;

import Controller.Save.SaveOpenDialog;
import Model.Pad;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by User on 21.12.2017.
 */
public class SettingController extends Observable {

    Stage stage = null;
    String savelocation;
    File file;
    String saveFilelocation;
    String saveSourcelocation;
    Pad[] pads;
    Observer observer;


    public SettingController(Stage stage, Pad[] pads, Observer observer) {

        this.observer = observer;

        if (observer == null) {
            System.out.println("null Observer");
        }

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
            save();
        }
    };
    public EventHandler<ActionEvent> openEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            open();
        }
    };
        public void save() {
            SaveOpenDialog saveDialog = new SaveOpenDialog();
            saveFilelocation = saveDialog.Savedialog(stage, "Wo möchten sie die Datei speichern?");
            saveDialog.save(pads, saveFilelocation);
        }


        public void open() {

            SaveOpenDialog openDialog = new SaveOpenDialog();
            file = openDialog.OpenDialog(stage);

            pads = openDialog.read(file.getAbsolutePath(), observer, null); //Muss noch bearbeitet werden!!!!
            setChanged();
            notifyObservers("padsladen");
        }



    public Pad[] getpads() {
        return pads;
    }


    public EventHandler<ActionEvent> openSourceLocation = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            SaveOpenDialog directionSourcedialog = new SaveOpenDialog();
            saveSourcelocation = directionSourcedialog.DirectionDialog(stage);
        }
    };

    public EventHandler<ActionEvent> openManual = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

        }
    };


}



