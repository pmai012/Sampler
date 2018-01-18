package Controller;

import Model.Pad;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.*;

import java.io.File;
import java.util.List;

/**
 * Created by User on 21.12.2017.
 */
public class PadController {
    private final int ANZAHL = 16;
    Pad pad[] = new Pad[ANZAHL];
    Button button[] = new Button[ANZAHL];



    public PadController(Button[] pads) {
        pad = new Pad[ANZAHL];

        for (int i = 0; i < ANZAHL; i++) {
            button[i] = pads[i];
        }
    }




    public EventHandler<MouseEvent> pressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            for (int i = 0; i < ANZAHL; i++) {
                if (event.getSource().equals(button[i])) {

                    if (pad[i] != null) {
                        pad[i].setThreadrun(true);

                            pad[i].threadstarten();
                            pad[i].playSound();


                    }
                }
            }
        }
    };




    public EventHandler<MouseEvent> rightclick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            for (int i = 0; i < ANZAHL; i++) {
                if (event.getSource().equals(button[i])) {
                    pad[i].setThreadrun(false);

                    //LINKSKLICK
                    if (event.getButton() == MouseButton.PRIMARY) {
                        System.out.println(i + " linksclick");
                        if (pad[i] != null) {
                            System.out.println(pad[i].getPresstime());
                            if (pad[i].getPresstime() > 400) {

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
                                pad[i] = new Pad(path);
                                System.out.println(path);
                                return;
                            }
                        }

                    }
                }
            }
        }
    };

}
