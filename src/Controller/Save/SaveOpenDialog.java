package Controller.Save;

import Model.Pad;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Observer;

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



        ArrayList<Padsaveclass> savelist = new ArrayList<Padsaveclass>();

       for (Pad i : input) {
                savelist.add(new Padsaveclass(i));
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


    public Pad[] read(String path, Observer observer, AudioOutput audioOutput) {

        ArrayList<Padsaveclass> pad = new ArrayList<Padsaveclass>();
        InputStream inputStream = null;


        try {
            inputStream = new FileInputStream(path);
            ObjectInputStream stream = new ObjectInputStream(inputStream);

           pad = (ArrayList<Padsaveclass>)  stream.readObject();

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
        System.out.println("Pads wurden geladen");


       Pad[] ausgabe = new Pad[pad.size()];

       for (int i = 0; i< pad.size(); i++){
           Minim minim = new SimpleMinim(true);
           AudioOutput out = minim.getLineOut();
           ausgabe[i] = pad.get(i).loadPad(observer,out);
       }
        return ausgabe;
    }





}