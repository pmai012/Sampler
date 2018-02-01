package View;

import Controller.PadController;
import Controller.SoundController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;


public class SoundView   extends Pane {

    private SoundController soundController;
    private VBox soundpane;
    private ObservableList<String> soundObList;
    private ListView soundlistview;
    private Label audioFX;
    private RadioButton fxOn, fxOff;

    public SoundView(PadController padController){

        soundController = new SoundController(System.getProperty("user.home")
                .concat("//Music").concat("//" + "SamplerSoundfiles"));
        soundlistview = new ListView();
        soundObList = FXCollections.observableArrayList();


        for (String name: soundController.getDirList()) {
            soundObList.add(name);
        }
        soundlistview.setItems(soundObList);
        soundlistview.setOnDragDetected(soundController.take);
        soundlistview.setOnDragOver(soundController.acceptdrag);
        soundlistview.setOnDragDropped(soundController.getData);
        fxOn = new RadioButton("An");
        fxOff = new RadioButton("Aus");
        fxOff.setSelected(true);

        ToggleGroup activeGroup =new ToggleGroup();
        activeGroup.getToggles().addAll(fxOn,fxOff);
        audioFX = new Label("Metronom");

        soundpane = new VBox(10);
        this.getChildren().add(soundpane);

        soundpane.getChildren().add(soundlistview);
        soundpane.getChildren().add(audioFX);
        soundpane.getChildren().addAll(fxOn,fxOff);

        audioFX.getStyleClass().addAll("title", "radioButton");
        fxOff.getStyleClass().addAll("title", "radioButton");
        fxOn.getStyleClass().addAll("title", "radioButton");

        fxOff.setOnAction(padController.metronomaus);
        fxOn.setOnAction(padController.metronoman);
    }

    public SoundController getSoundController() {
        return soundController;
    }

}
