package Controller;

import Model.BeatsperMinute;
import Model.Effects.Effect;
import Model.Pad;
import View.EffectView;
import View.PadView;
import ddf.minim.AudioOutput;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
//import javafx.scene.control.TextInputDialog;
import javafx.scene.input.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import static javafx.scene.input.ScrollEvent.SCROLL;
import static javafx.scene.input.ScrollEvent.SCROLL_STARTED;


public class PadController {
    private final int ANZAHL = 16;
    private SimpleMinim minim = new SimpleMinim(true);
    AudioOutput globalOut;
    private Pad pad[] = new Pad[ANZAHL];
    private Button button[] = new Button[ANZAHL];
    private Observer observer;
    private PadView view;
    private EffectView effectView;
    private int padIndex;
    private BeatsperMinute beat;


    public PadController(Button[] pads, Observer observer, PadView view) {
        pad = new Pad[ANZAHL];
        this.view = view;
        globalOut = minim.getLineOut();
        this.observer = observer;
        beat = new BeatsperMinute(100, globalOut);

        for (int i = 0; i < ANZAHL; i++) {
            button[i] = pads[i];

        }


    }




    public void startbeat() {
        beat.play();
    }

    public void stopbeat(){
        beat.stop();
    }

    public void setbeat(int beatsperminute){
        beat.setBpm(beatsperminute);
    }

    /**
     * Gibt Pad an der Position des übergebenen Index - 1 zurück.
     * - 1, weil der Index für die Padbeschriftung von 1 - 16 gewählt wurde.
     *
     * @param index
     * @return
     */
    public Pad getPadAtIndex(int index) {
        return pad[index - 1];
    }

    public Pad[] getPad() {
        return pad;
    }


    public int[] whoisnotnull() {

        boolean[] isnotnull = new boolean[ANZAHL];
        for (int i = 0; i < pad.length; i++) {


            if (pad[i] != null) { //&& !pad[i].isNull()
                isnotnull[i] = true;
                System.out.println(i);
            }
        }
        int trues = 0;
        for (int i = 0; i < isnotnull.length; i++) {
            if (isnotnull[i] == true) {
                trues++;
            }
        }
        int[] ausgabe = new int[trues];
        int pos = 0;
        for (int i = 0; i < isnotnull.length; i++) {
            if (isnotnull[i] == true) {
                ausgabe[pos] = i;
                pos++;
            }
        }
        return ausgabe;
    }

    public void setPad(Pad[] pad) {
        this.pad = pad;
    }


    public EventHandler<ActionEvent> metronoman = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            startbeat();
        }

    };



    public EventHandler<ActionEvent> metronomaus = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            stopbeat();
        }

    };

    public EventHandler<MouseEvent> pressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            for (int i = 0; i < ANZAHL; i++) {
                if (event.getSource().equals(button[i])) {

                    startpressing(i);
                }
            }
        }
    };





    public String beattoString(){
        String beats = Integer.toString(beat.getBpm());
        return beats +" BPM";
    }

    public void setMetronom (int bpm){
        beat.setBpm(bpm);

    }

    public void startpressing(int number) {


        if (pad[number] != null) {

            if (pad[number].isPlaying()) {
                pad[number].stop();
                return;
            }


            pad[number].setThreadrun(true);

            pad[number].threadstarten();
            pad[number].playSound();


        }

    }

    public void cleanpads(){
        for (int i = 0; i < 16; i++){
            pad[i] = null;
        }

    }


    public void padclick(int number) {
        if (pad[number] == null) {
            return;
        }
        pad[number].setThreadrun(false);
    }


    public EventHandler<MouseEvent> rightclick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            for (int i = 0; i < ANZAHL; i++) {
                if (event.getSource().equals(button[i])) {

                    padclick(i);

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
                        padIndex = i + 1;
                        return;
                    }

                }
            }
        }
    };
    public EventHandler<ActionEvent> contextMenu_addEffectClicked = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            effectView = new EffectView(view.getPadController());
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

                                Keyinput keyinput = new Keyinput();
                                pad[i].setShortcut(keyinput.getdefaultkeyCode(i));

                                System.out.println(path);
                                return;
                            }
                        }

                    }
                }
            }
        }
    };

    public AudioOutput getGlobalOut() {
        return this.globalOut;
    }


    public int getClickedPadIndex() {
        return this.padIndex;
    }
}
