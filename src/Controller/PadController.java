package Controller;

import Model.Pad;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

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


    public EventHandler<ActionEvent> play = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {

            for (int i = 0; i < ANZAHL; i++) {
                if (event.getSource().equals(button[i])) {
                    System.out.println(i);
                    //  pad[i].playSound();


                    return;
                }
            }
        }


    };

    public EventHandler<DragEvent> acceptdrag = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    };

    public EventHandler<DragEvent> getData = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {

            for (int i = 0; i < ANZAHL; i++) {
                if (event.getSource().equals(button[i])) {
                    Dragboard dragboard = event.getDragboard();

                    if (dragboard.hasFiles()) {

                        List<File> list = dragboard.getFiles();
                        String path = list.get(0).toPath().toString();


                        if ( path.endsWith(".mp3") ||path.endsWith(".wav")) {
                            pad[i] = new Pad(path);
                            System.out.println(path);
                        }
                    }
                }
            }
        }
    };

}
