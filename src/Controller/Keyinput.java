package Controller;

import Model.Pad;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class Keyinput implements EventHandler<KeyEvent> {

    PadController padController;
    SettingController settingController;
    RecordController recordController;





    public Keyinput(){

    }

    public Keyinput(PadController padController, SettingController settingController, RecordController recordController) {

        this.padController = padController;
        this.settingController = settingController;
        this.recordController = recordController;

        //BEispiel

    }

    //Tasteneingaben
    @Override
    public void handle(KeyEvent event) {
        Pad[] pads = padController.getPad();

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
            if (event.getCode() == pads[i].getShortcut()) {

                padController.startpressing(i);


            }

        }
    }

    public KeyCode getdefaultkeyCode(int padnumber) {
        switch (padnumber){
            case 0:
                return KeyCode.DIGIT1;
            case 1:
                return KeyCode.DIGIT2;
            case 2:
                return KeyCode.DIGIT3;
            case 3:
                return KeyCode.DIGIT4;
            case 4:
                return KeyCode.Q;
            case 5:
                return KeyCode.W;
            case 6:
                return KeyCode.E;
            case 7:
                return KeyCode.R;
            case 8:
                return KeyCode.A;
            case 9:
                return KeyCode.S;
            case 10:
                return KeyCode.D;
            case 11:
                return KeyCode.F;
            case 12:
                return KeyCode.Y;
            case 13:
                return KeyCode.X;
            case 14:
                return KeyCode.C;
            case 15:
                return KeyCode.V;

        }
        return null;
    }
}
