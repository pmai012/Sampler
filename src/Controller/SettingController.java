package Controller;

import View.MainFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

/**
 * Created by User on 21.12.2017.
 */
public class SettingController {




    public SettingController(){

    }


    public EventHandler<ActionEvent> openSaveLocation = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("blub");
            /*
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Speicherort wählen");
            filechooser.showOpenDialog(primaryStage);
            */
        }
    };
}



