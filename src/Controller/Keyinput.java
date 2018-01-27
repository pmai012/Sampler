package Controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 */
public class Keyinput implements  EventHandler<KeyEvent>{

    PadController padController;
    SettingController settingController;
    RecordController recordController;


    public Keyinput(PadController padController, SettingController settingController, RecordController recordController){
        this.padController = padController;
        this.settingController = settingController;
        this.recordController = recordController;
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


    }
}
