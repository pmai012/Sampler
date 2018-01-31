package Controller;

import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SoundController {

    private String dirPath = null;
    private ArrayList<String> dirList;
    private ArrayList<String> pathList;


    public SoundController(String dirPath ){
        this.dirPath = dirPath;
        this.dirList = new ArrayList<String>();
        this.pathList = new ArrayList<String>();
        loadSoundfiles(System.getProperty("user.home").concat("//Music").concat("//" + "SamplerSoundfiles"));
    }

    public ArrayList<String> getDirList() {
        return dirList;
    }

    public String getPath(int index){
        return pathList.get(index);
    }

    public void loadSoundfiles(String dirPath){
        File directory = new File(dirPath);

        File[] soundList = directory.listFiles();
        if(soundList != null){
            for (File file:soundList) {
                if(file.isFile()){
                    if(file.getName().endsWith(".wav") || file.getName().endsWith(".mp3")){
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
         List<String> files = new ArrayList<String>() ;
         files.add(pathList.get(index));
        content.putFilesByPath(files);
         db.setContent(content);

     }
 };


}