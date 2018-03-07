package Model;

import Model.Effects.Effect;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import ddf.minim.UGen;
import ddf.minim.ugens.FilePlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.scene.input.KeyCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by User on 21.12.2017.
 */
public class Pad extends Observable implements Serializable {


    private KeyCode shortcut;
    private int startpoint = 0;
    private long endpoint;
    private long presstime = -5;
    private boolean threadrun = false;
    private String path;
    private AudioOutput audioOut;
    private FilePlayer filePlayer;
    private Minim minim;
    private Observer observer;
    private int starter = 0;
    private List<UGen> effekte;
    private List<Effect> effects;
    private long lenght;

    public String getPath(){
        return path;
    }

    public List<UGen> getEffekte(){
        return effekte;
    }

    public void addEffect(Effect effect){
        if (effekte == null) {
            effects = new ArrayList<Effect>();
            effekte = new ArrayList<UGen>();
            effects.add(effect);
            effekte.add(effect.getEffect());
            sendupdate();
        }
    }
    public void deleteEffect(){
        if (effekte != null) {
            effekte.remove(0);
            effects.remove(0);
            effekte = null;
            effects = null;
            sendupdate();
        }
    }
    public Effect getEffect(){

        return effects.get(0);
    }

    public KeyCode getShortcut() {
        return shortcut;
    }
    public void setShortcut(KeyCode keyCode){
        this.shortcut = keyCode;
    }
    public int getStartpoint() {
        return startpoint;
    }
    public long getEndpoint() {
        return endpoint;
    }
    public long getLenght(){
        return lenght;
    }
    public long getPresstime() {
        return presstime;
    }
    public void setThreadrun(boolean threadrun) {
        this.threadrun = threadrun;
    }


    public void clear() {
        startpoint = 0;
        endpoint = Long.MAX_VALUE;
        presstime = -5;
        threadrun = false;
        path = null;
        audioOut = null;
        filePlayer = null;
        starter = 0;
        if (effekte.size() != 0) {
            effekte.clear();
        }
    }
//Paddummy nur zum Informieren der Mainframe
    public Pad (Observer observer, String msg){
        addObserver(observer);
        setChanged();
        notifyObservers(msg);
    }
    public Pad(String pathtoSound, Observer observer, AudioOutput globalOut) {
        this.observer = observer;
        path = pathtoSound;
        minim = new SimpleMinim(true);
        audioOut = globalOut;
        filePlayer = new FilePlayer(minim.loadFileStream(pathtoSound, 1024, true));
        this.addObserver(this.observer);
        System.out.println("Observer: " + countObservers());
        lenght = filePlayer.length();
    }
    public void playSound() {

        if (this.hasEffects()) {
            playSound(effekte.get(0));
        } else {
            if (starter < 1) {
                filePlayer.play(startpoint);
                filePlayer.patch(audioOut);
                starter++;
            }
        }
    }
    public void playSound(UGen effect) {

        if (starter < 1) {
            filePlayer.play(startpoint);
            filePlayer.patch(effect).patch(audioOut);
            starter++;
        }
    }
    public void stop() {
        filePlayer.pause();
        starter = 0;
        filePlayer = new FilePlayer(minim.loadFileStream(path, 1024, true));

    }
    public void sendupdate() {
        setChanged();
        notifyObservers("pad");
    }
    public void setStartpoint(int time) {

        startpoint = time;
    }

    public boolean isPlaying() {

        if (starter >= 1) {
            return true;
        }
        return false;
    }

    public void setEndpoint(long time) {
        endpoint = time;
    }

    public boolean hasEffects() {
        if (this.effekte != null) {
            return !this.effekte.isEmpty();
        }
        return false;
    }
    public void threadstarten() {
        Thread time = new Thread() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                while (threadrun) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    presstime = System.currentTimeMillis() - start;
                }
                try {
                    this.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        time.start();
    }
}