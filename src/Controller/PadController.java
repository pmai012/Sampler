package Controller;

import Model.Pad;
import View.PadView;
import ddf.minim.AudioOutput;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by User on 21.12.2017.
 */
public class PadController {
    private final int ANZAHL = 16;
    private SimpleMinim minim = new SimpleMinim(true);
    AudioOutput globalOut;
    private Pad pad[] = new Pad[ANZAHL];
    private Button button[] = new Button[ANZAHL];
    private Observer observer;

    public PadController(Button[] pads, Observer observer) {
        pad = new Pad[ANZAHL];
        globalOut = minim.getLineOut();
    this.observer = observer;

        for (int i = 0; i < ANZAHL; i++) {
            button[i] = pads[i];

        }


    }



    public Pad[] getPad() {
        return pad;
    }



    public int[] whoisnotnull() {

       boolean[] isnotnull = new boolean[ANZAHL];
        for (int i = 0; i < pad.length; i++) {


          if (pad[i] != null ) { //&& !pad[i].isNull()
              isnotnull[i] = true;
              System.out.println(i);
            }
        }
        int trues = 0;
        for (int i = 0; i < isnotnull.length; i++) {
            if (isnotnull[i] == true){
                trues++;
            }
        }
        int[] ausgabe = new int[trues];
        int pos = 0;
        for (int i = 0; i < isnotnull.length; i++) {
            if (isnotnull[i] == true){
                ausgabe[pos] = i;
                pos++;
            }
        }
        return ausgabe;
    }

    public void setPad(Pad[] pad){
        this.pad = pad;
    }

    public EventHandler<MouseEvent> pressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            for (int i = 0; i < ANZAHL; i++) {
                if (event.getSource().equals(button[i])) {

                   startpressing(pad[i]);
                }
            }
        }
    };


    public void startpressing(Pad pad){




                if (pad != null) {

                    if (pad.isPlaying()){
                        pad.stop();
                        return;
                    }



                    pad.setThreadrun(true);

                    pad.threadstarten();
                    pad.playSound();



                }

    }



    public void padclick(Pad pad){
        if (pad == null) {
            return;
        }
        pad.setThreadrun(false);
    }


    public EventHandler<MouseEvent> rightclick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            for (int i = 0; i < ANZAHL; i++) {
                if (event.getSource().equals(button[i])) {

                    padclick(pad[i]);


                    //LINKSKLICK
                    if (event.getButton() == MouseButton.PRIMARY) {
                        System.out.println(i + " linksclick");
                        if (pad[i] != null) {

                            if (pad[i].getPresstime() > 500) {

                                pad[i].stop();
                            }


                        }

                        return;
                    }


                    //RECHTSCLICK
                    if (event.getButton() == MouseButton.SECONDARY) {
                        System.out.println(i + " rechtsclick");
                        return;
                    }

                }
            }
        }
    };


    /**
     * Acceptdrag allows to drop something
     */
    public EventHandler<DragEvent> acceptdrag = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    };


    /**
     * getData get the Data. it has to be mp3 or wav. If you put more files it looks for the first wav or mp3
     */
    public EventHandler<DragEvent> getData = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {

            for (int i = 0; i < ANZAHL; i++) {
                if (event.getSource().equals(button[i])) {
                    Dragboard dragboard = event.getDragboard();

                    if (dragboard.hasFiles()) {

                        List<File> list = dragboard.getFiles();

                        for (int f = 0; f < list.size(); f++) {
                            String path = list.get(f).toPath().toString();


                            if (path.endsWith(".mp3") || path.endsWith(".wav")) {
                                pad[i] = new Pad(path, observer, globalOut);
                                pad[i].sendupdate();

                                System.out.println(path);
                                return;
                            }
                        }

                    }
                }
            }
        }
    };
    public AudioOutput getGlobalOut()
    {
        return this.globalOut;
    }


}
