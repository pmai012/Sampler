package Controller;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by User on 22.01.2018.
 */
public class SaveOpenDialog {


    /**
     * Gibt ein Dialog zum Speichern von Dateien aus.
     * @param stage Die Stage, wo der Dialog erscheinen soll
     * @param title Den Titel des DIaloges
     * @return Gibt den ausgewählten Speicherort zurück
     */
    public String Savedialog(Stage stage, String title){ //

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Speicherort");
       File file = fileChooser.showSaveDialog(stage);
     System.out.println(file.getAbsolutePath());

        return file.getAbsolutePath();
    }


    /**
     * Gibt ein Dialog für die Auswahl eines Ordners an
     * @param stage Die Stage, wo der Dialog erscheinen soll
     * @param title Den Titel des Dialoges
     * @return Gibt den Absoluten Pfad zurueck
     */
    public String DirectionDialog(Stage stage, String title){
         DirectoryChooser directoryChooser = new DirectoryChooser();
        File file =
                directoryChooser.showDialog(stage);
       return file.getAbsolutePath();
    }


    /**
     * Öffnet einen Opendialog
     * @param stage Nur die Stage wo der Dialog ausgegeben werden soll
     * @return Die ausgewählte File
     */
    public File OpenDialog(Stage stage){
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(stage);
    }
}
