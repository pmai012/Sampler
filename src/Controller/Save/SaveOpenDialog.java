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
private FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pascal-Julian-Deniz-File (*.pjd)", "*.pjd");
private String recordpathfrompadsave;
    /**
     * Gibt ein Dialog zum Speichern von Dateien aus.
     *
     * @param stage Die Stage, wo der Dialog erscheinen soll
     * @param title Den Titel des DIaloges
     * @return Gibt den ausgewählten Speicherort zurück
     */
    public String Savedialog(Stage stage, String title) { //

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle(title);

        File defaultDirectory = new File(  System.getProperty("user.home").concat("//Desktop"));
        fileChooser.setInitialDirectory(defaultDirectory);
        File file = fileChooser.showSaveDialog(stage);

        if (file != null){
            return file.getAbsolutePath();
        }else{
           return "none";
        }

    }

    public String getRecordpathfrompadsave() {
        if (recordpathfrompadsave == null){
            return "";
        }else {
            return recordpathfrompadsave;
        }
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
        File defaultDirectory = new File(  System.getProperty("user.home").concat("//Desktop"));
        fileChooser.setInitialDirectory(defaultDirectory);
       fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("");
        return fileChooser.showOpenDialog(stage);
    }


    /**
     * Speichert Pads als File
     *
     * @param input PadArray
     * @param path  Speicherpfad
     */
    public void save(Pad[] input, String path, String recordpath) {



        ArrayList<Padsaveclass> savelist = new ArrayList<Padsaveclass>();

       for (Pad i : input) {
                savelist.add(new Padsaveclass(i,recordpath));
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

        recordpathfrompadsave = pad.get(0).getSavepath();
        Pad[] ausgabe = new Pad[pad.size()];

       for (int i = 0; i< pad.size(); i++){
           Minim minim = new SimpleMinim(true);
           AudioOutput out = minim.getLineOut();
           ausgabe[i] = pad.get(i).loadPad(observer,out);
       }
        return ausgabe;
    }





}