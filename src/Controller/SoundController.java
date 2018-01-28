package Controller;

import java.io.File;
import java.util.ArrayList;


public class SoundController {

    private String dirPath = null;
    private ArrayList<String> dirList;

    public SoundController(String dirPath){
        this.dirPath = dirPath;
        this.dirList = new ArrayList<String>();
        loadSoundfiles("/Users/deniz/IdeaProjects/Sampler/src/Sound");
        // muss noch allgemein geschrieben werden
    }

    public ArrayList<String> getDirList() {
        return dirList;
    }

    public void loadSoundfiles(String dirPath){
        File directory = new File(dirPath);

        File[] soundList = directory.listFiles();
        if(soundList != null){
            for (File file:soundList) {
                if(file.isFile()){
                    if(file.getName().endsWith(".wav") || file.getName().endsWith(".mp3")){
                        this.dirList.add(file.getName());
                    }
                }
            }
        }


    }


}