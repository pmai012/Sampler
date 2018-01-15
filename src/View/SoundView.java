package View;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

/**
 * Created by User on 21.12.2017.
 */
public class SoundView extends Pane {

    private ListView soundlist;
    private Label test;

    public SoundView(){

        soundlist = new ListView();
        test = new Label("hallo");
        this.getChildren().addAll(soundlist);
    }
}
