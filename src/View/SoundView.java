package View;

import Controller.PadController;
import Controller.SoundController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class SoundView   extends Pane {

    private SoundController soundController;
    private VBox soundpane;
    private ObservableList<String> soundObList;
    private ListView soundlistview;
    private Label audioFX;
    private RadioButton on, off;



    public SoundView(PadController padController){


        soundController = new SoundController(System.getProperty("user.home" )
                .concat("//Music").concat("//" + "SamplerSoundfiles"));
        soundlistview = new ListView();
        soundObList = FXCollections.observableArrayList();
        soundController.createSounddir(soundObList);

        for (String name: soundController.getDirList()) {
            soundObList.add(name);
        }
        soundlistview.setItems(soundObList);

        soundlistview.setOnDragDetected(soundController.take);
        soundlistview.setOnDragOver(soundController.acceptdrag);
        soundlistview.setOnDragDropped(soundController.getData);
        on = new RadioButton("An");
        off = new RadioButton("Aus");
        off.setSelected(true);

        ToggleGroup activeGroup =new ToggleGroup();
        activeGroup.getToggles().addAll(on, off);
        audioFX = new Label("Metronom");

        soundpane = new VBox(10);
        this.getChildren().add(soundpane);

        soundpane.getChildren().add(soundlistview);
        soundpane.getChildren().add(audioFX);
        soundpane.getChildren().addAll(on, off);

        audioFX.getStyleClass().addAll("title", "radioButton");
        off.getStyleClass().addAll("title", "radioButton");
        on.getStyleClass().addAll("title", "radioButton");

        off.setOnAction(padController.metronomaus);
        on.setOnAction(padController.metronoman);
    }

    public SoundController getSoundController() {
        return soundController;
    }

}
