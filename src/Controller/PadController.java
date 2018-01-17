package Controller;

import Model.Pad;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Created by User on 21.12.2017.
 */
public class PadController {
    private final int ANZAHL = 16;
    Pad pad[] = new Pad[ANZAHL];
    Button button[] = new Button[ANZAHL];

    public PadController(Button[] pads){
    pad = new Pad[ANZAHL];

    for (int i = 0; i< ANZAHL; i++){
    button[i] = pads[i];
        }
    }




    public EventHandler<ActionEvent> play = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {

            for (int i = 0; i< ANZAHL; i++){
                if (event.getSource().equals(button[i])){

                    pad[i].playSound();


                    return;
                }
            }
        }




    };


}
