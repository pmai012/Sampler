package Controller;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class SoundController {

    private String dirPath = null;
    private ArrayList<String> dirList;
    private ArrayList<String> pathList;


    public SoundController(String dirPath) {
        this.dirPath = dirPath;
        this.dirList = new ArrayList<String>();
        this.pathList = new ArrayList<String>();
        loadSoundfiles(System.getProperty("user.home").concat("//Music").concat("//" + "SamplerSoundfiles"));
    }

    public ArrayList<String> getDirList() {
        return dirList;
    }

    public String getPath(int index) {
        return pathList.get(index);
    }

    public void loadSoundfiles(String dirPath) {
        File directory = new File(dirPath);

        File[] soundList = directory.listFiles();
        if (soundList != null) {
            for (File file : soundList) {
                if (file.isFile()) {
                    if (file.getName().endsWith(".wav") || file.getName().endsWith(".mp3")) {
                        this.dirList.add(file.getName());
                        this.pathList.add(file.getAbsolutePath());
                    }
                }
            }
        }


    }


    public EventHandler<MouseEvent> take = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            ListView soundlistview = (ListView) event.getSource();
            Dragboard db = soundlistview.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();


            int index = soundlistview.getSelectionModel().getSelectedIndex();
            System.out.println(index);
            List<String> files = new ArrayList<String>();
            files.add(pathList.get(index));
            content.putFilesByPath(files);
            db.setContent(content);

        }
    };

    public EventHandler<DragEvent> acceptdrag = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    };


    /**
     * getData get the Datainto the list
     */
    public EventHandler<DragEvent> getData = new EventHandler<DragEvent>() {

        @Override
        public void handle(DragEvent event) {

            Dragboard dragboard = event.getDragboard();

            List<File> files = dragboard.getFiles();


            for (int i = 0; i < files.size(); i++) {
                String path = files.get(i).toPath().toString();

                if (path.endsWith(".mp3") || path.endsWith(".wav")) {

                    Path source = files.get(i).toPath();
                    FileSystem fs = FileSystems.getDefault();
                    String musicpath = System.getProperty("user.home").concat("//Music").concat("//" + "SamplerSoundfiles");
                    Path target = fs.getPath(musicpath);
                    try {

                        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

                    } catch (FileAlreadyExistsException e) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Datei bereits vorhanden");
                        alert.setHeaderText("Die Datei exestiert bereits");
                        alert.setContentText("'" + files.get(i).getName() + "' exestiert in dem SamplerSoundfiles Ordner bereits");

                        alert.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            ;
        }
    };

    public void createSounddir(){

        String dirpath = "SamplerSoundfiles";
        Path abspath = Paths.get(System.getProperty("user.home").concat("//Music").concat("//" + dirpath));
        File sounddir = new File(System.getProperty("user.home").concat("//Music").concat("//"+dirpath));
        System.out.println(sounddir.mkdir());
        if(System.getProperty("user.home").concat("//Music").concat("//"+dirpath).isEmpty()){
            try{
                Files.copy(Paths.get("Sound/F7 Bass 1.wav"),
                        (Paths.get(System.getProperty("user.home").concat("//Music").concat("//"+dirpath))));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}