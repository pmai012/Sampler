package View;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by User on 21.12.2017.
 */
public class SoundView extends Pane {

    private VBox soundpane;
    private ListView soundlist;
    private Label audioFX;
    private RadioButton fxOn, fxOff;

    public SoundView(){


        soundlist = new ListView();
        fxOn = new RadioButton("An");
        fxOff = new RadioButton("Aus");
        fxOff.setSelected(true);

        ToggleGroup activeGroup =new ToggleGroup();
        activeGroup.getToggles().addAll(fxOn,fxOff);
        audioFX = new Label("Audio-FX");

        soundpane = new VBox(10);
        this.getChildren().add(soundpane);

        soundpane.getChildren().addAll(soundlist);
        soundpane.getChildren().add(audioFX);
        soundpane.getChildren().addAll(fxOn,fxOff);

        audioFX.getStyleClass().add("title");
        fxOff.getStyleClass().add("title");
        fxOn.getStyleClass().add("title");
    }
}
