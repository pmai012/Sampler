package Controller;

import Controller.Save.SaveOpenDialog;
import Model.Pad;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by User on 21.12.2017.
 */
public class SettingController extends Observable {

    private Stage stage = null;
    private String savelocation;
    private  File file;
    private RecordController recordController;
    private String saveFilelocation;
    private String saveSourcelocation;
    private  Pad[] pads;
    private Observer observer;


    public SettingController(Stage stage, Pad[] pads, Observer observer, RecordController recordController) {

        this.recordController = recordController;
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


    public EventHandler<ActionEvent> openSaveLocationEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            openSaveLocation();
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


    public void openSaveLocation() {
        SaveOpenDialog directionDialog = new SaveOpenDialog();
        savelocation = directionDialog.DirectionDialog(stage);
        recordController.setpath(savelocation);
    }

    public void save() {
        SaveOpenDialog saveDialog = new SaveOpenDialog();
        saveFilelocation = saveDialog.Savedialog(stage, "Wo möchten sie die Datei speichern?");
        saveDialog.save(pads, saveFilelocation, recordController.getPath());
    }


    public void open() {

        SaveOpenDialog openDialog = new SaveOpenDialog();
        file = openDialog.OpenDialog(stage);

        pads = openDialog.read(file.getAbsolutePath(), observer, null); //Muss noch bearbeitet werden!!!!
        setChanged();
        notifyObservers("padsladen");
        if (openDialog.getRecordpathfrompadsave() == null){
            recordController.setpath(System.getProperty("user.home").concat("//Music") + "/myrecording.wav");
        }else
        recordController.setpath(openDialog.getRecordpathfrompadsave());
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
            try{
                File file = new File("../Anleitung.csv");
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                if(file.length() == 0) {
                    bw.write("Sampler");
                    bw.newLine();
                    bw.write("ANLEITUNG:");
                    bw.newLine();
                    bw.write("Der Sampler stellt ihnen 16 Pads zur Verfügung, die Sie per Tastendruck " +
                            "\nabspielen können. Ziehen Sie ganz einfach ihre Sounddateien auf die Pads um " +
                            "\ndiese zu füllen. Nun können Sie ihre Eingabefolge aufnehmen indem Sie die Recordtaste" +
                            "\nbetätigen, ihre Folge drücken und dann auf die Stoptaste drücken. ");
                    bw.newLine();
                    bw.write("Viel Spaß mit noch mit dem Sampler. ");

                }
                bw.close();

            }catch (IOException e){
                e.printStackTrace();
                System.out.println("Anleitung konnte nicht erstellt werden.");
            }


        }
    };


}



