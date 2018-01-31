package Controller;

import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Keyinput implements EventHandler<KeyEvent> {

    PadController padController;
    SettingController settingController;
    RecordController recordController;
    KeyCode[] padshortcut;


    public void setShortCut(KeyCode input, int number) {
        padshortcut[number] = input;
    }

    public Keyinput(PadController padController, SettingController settingController, RecordController recordController) {
        padshortcut = new KeyCode[16];
        this.padController = padController;
        this.settingController = settingController;
        this.recordController = recordController;

        //BEispiel
        padshortcut[0] = KeyCode.Z;
    }





    //Tasteneingaben
    @Override
    public void handle(KeyEvent event) {


        if (event.getCode() == KeyCode.S && event.isControlDown()) {
            settingController.save();

        }

        if (event.getCode() == KeyCode.O && event.isControlDown()) {
            settingController.open();
        }

        if (event.getCode() == KeyCode.R && event.isControlDown()) {
            recordController.makerecord();
        }

        for (int i = 0; i < 16; i++) {
            if (padshortcut[i] != null && event.getCode() == padshortcut[i]) {


                padController.startpressing(i);


            }

        }
    }
}
