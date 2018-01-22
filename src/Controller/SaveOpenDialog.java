package Controller;

import Model.Pad;
import ddf.minim.UGen;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by User on 22.01.2018.
 */
public class SaveOpenDialog {


    /**
     * Gibt ein Dialog zum Speichern von Dateien aus.
     *
     * @param stage Die Stage, wo der Dialog erscheinen soll
     * @param title Den Titel des DIaloges
     * @return Gibt den ausgewählten Speicherort zurück
     */
    public String Savedialog(Stage stage, String title) { //

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        File file = fileChooser.showSaveDialog(stage);

        return file.getAbsolutePath();
    }


    /**
     * Gibt ein Dialog für die Auswahl eines Ordners an
     *
     * @param stage Die Stage, wo der Dialog erscheinen soll
     * @return Gibt den Absoluten Pfad zurueck
     */
    public String DirectionDialog(Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            return file.getAbsolutePath();
        } else {
            return "none";
        }
    }


    /**
     * Öffnet einen Opendialog
     *
     * @param stage Nur die Stage wo der Dialog ausgegeben werden soll
     * @return Die ausgewählte File
     */
    public File OpenDialog(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(stage);
    }


    /**
     * Speichert Pads als File
     *
     * @param input PadArray
     * @param path  Speicherpfad
     */
    public void save(Pad[] input, String path) {


        ArrayList<Pad> savelist = new ArrayList<Pad>();

       for (Pad i : input) {
                savelist.add(i);
            }
        OutputStream pads = null;


        try {
            pads = new FileOutputStream(path);
            ObjectOutputStream padoutput = new ObjectOutputStream(pads);
            padoutput.writeObject(savelist);

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                pads.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    public Pad[] read(String path) {

        ArrayList<Pad> pad = new ArrayList<Pad>();
        InputStream inputStream = null;


        try {
            inputStream = new FileInputStream(path);
            ObjectInputStream stream = new ObjectInputStream(inputStream);

           pad = (ArrayList<Pad>)  stream.readObject();

        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
System.out.println();
       Pad[] ausgabe = (Pad[]) pad.toArray();
        return ausgabe;
    }
}