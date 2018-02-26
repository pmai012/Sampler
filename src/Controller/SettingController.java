package Controller;

import Controller.Save.SaveOpenDialog;
import Model.Pad;
import View.ManualView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class SettingController extends Observable {

    private Stage stage = null;
    private String savelocation;
    private File file;
    private RecordController recordController;
    private String saveFilelocation;
    private String saveSourcelocation;
    private Pad[] pads;
    private Observer observer;
    private ManualView manualView;

    public SettingController(Stage stage, Pad[] pads, Observer observer, RecordController recordController) {

        this.recordController = recordController;
        this.observer = observer;

        if (observer == null) {
            System.out.println("null Observer");
        }
        this.stage = stage;
        this.pads = pads;
    }
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


    public EventHandler<ActionEvent> cleanEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            clean();
        }
    };

  public String Savelocation(){
      SaveOpenDialog saveDialog = new SaveOpenDialog();
      saveDialog.setExtFilter("WAV - Datei","wav");
      saveFilelocation = saveDialog.Savedialog(stage, "Wo möchten sie die Aufnahme speichern?");
     return saveFilelocation;
  }

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


    public void clean() {

        for (int i = 0; i < 16; i++) {
            pads[i] = null;
            setChanged();
            notifyObservers("pad");
        }


    }

    public EventHandler<ActionEvent> openSourceLocation = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            SaveOpenDialog directionSourcedialog = new SaveOpenDialog();
            saveSourcelocation = directionSourcedialog.DirectionDialog(stage);
        }
    };

    public EventHandler<ActionEvent> openManualView = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            manualView = new ManualView();
        }
    };


}
