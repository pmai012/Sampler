package Controller;


import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.*;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class SoundController {
    private ObservableList<String> soundObList;
    private String dirPath = null;
    private ArrayList<String> dirList;
    private ArrayList<String> pathList;


    public SoundController(String dirPath) {
        this.soundObList = soundObList;
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
                    try {
                        File s = new File(System.getProperty("user.home").concat("//Music").concat("//SamplerSoundfiles//" + files.get(i).getName()));
                        if (!Files.exists(s.toPath())) {
                            Files.copy(Paths.get(files.get(i).getAbsolutePath()),
                                    (Paths.get(System.getProperty("user.home").concat("//Music").concat("//SamplerSoundfiles//" + files.get(i).getName()))));
                            soundObList.add(files.get(i).getName());
                            dirList.add(files.get(i).getName());
                            pathList.add(files.get(i).getAbsolutePath());
                        }else{
                            FileAlreadyExistsException e = new FileAlreadyExistsException(null);
                            throw e ;
                        }

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

    public EventHandler<MouseEvent> mouseEventEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            String path = System.getProperty("user.home").concat("\\Music").concat("\\SamplerSoundfiles");
            if (event.getButton() == MouseButton.SECONDARY) {
                String system = System.getProperty("os.name").toLowerCase();
                if (system.contains("win")) {
                    //Windows
                    try {
                        Runtime.getRuntime().exec("explorer.exe " + path);
                    } catch (IOException e) {
                        e.printStackTrace();
                }

                } else if (system.contains("osx")) {
                    //MAc System
                    try {
                        Runtime.getRuntime().exec("open " + path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                   }

                }



        }
    };


    public EventHandler<KeyEvent> keyinput = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            ListView soundlistview = (ListView) event.getSource();
            if (soundlistview.getSelectionModel().isSelected(soundlistview.getSelectionModel().getSelectedIndex())) {

                if (event.getCode() == KeyCode.DELETE) {
                    int index = soundlistview.getSelectionModel().getSelectedIndex();
                    soundObList.remove(index);
                    Path path = Paths.get(pathList.get(index));
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    dirList.remove(index);
                    pathList.remove(index);
                }


            }
        }
    };

    public void createSounddir(ObservableList<String> soundObList) {
        this.soundObList = soundObList;
        String dirpath = "SamplerSoundfiles";

        File[] fileArray = new File[3];
        fileArray[0] = new File("src/Sound/F7 Bass 1.wav");
        fileArray[1] = new File("src/Sound/HiHat 1.wav");
        fileArray[2] = new File("src/Sound/Snare 1.wav");

        File sounddir = new File(System.getProperty("user.home").concat("//Music").concat("//" + dirpath));


        for (File f : fileArray) {
            if (f != null) {
                try {
                    Path path = Paths.get(System.getProperty("user.home").concat("//Music").concat("//" + dirpath + "//" + f.getName()));
                    //(System.getProperty("user.home").concat("//Music").concat("//" + dirpath + "//" + f.getName()))


                    Files.copy(f.toPath(),
                            (Paths.get(System.getProperty("user.home").concat("//Music").concat("//" + dirpath + "//" + f.getName()))));


                    this.dirList.add(f.getName());
                    this.pathList.add(f.getAbsolutePath());

                } catch (FileAlreadyExistsException e) {


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}