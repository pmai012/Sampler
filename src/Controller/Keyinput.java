package Controller;

import Model.Pad;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Created by User on 27.01.2018.
 */
public class Keyinput implements  EventHandler<KeyEvent>{

    PadController padController;


    public Keyinput(PadController padController){
        this.padController = padController;
    }

    //Tasteneingaben
    @Override
    public void handle(KeyEvent event) {

        if (event.getCode() == KeyCode.) {
            System.out.println("Enter Pressed");
        }


    }
}
