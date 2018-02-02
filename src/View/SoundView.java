package View;

import Controller.PadController;
import Controller.SoundController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class SoundView   extends Pane {

    private SoundController soundController;
    private VBox soundpane;
    private ObservableList<String> soundObList;
    private ListView soundlistview;
    private Label audioFX;
    private RadioButton fxOn, fxOff;
    private Tooltip listViewTip;



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
        soundlistview.addEventHandler(KeyEvent.KEY_RELEASED,soundController.keyinput);
        soundlistview.addEventHandler(MouseEvent.MOUSE_CLICKED, soundController.mouseEventEventHandler);
        fxOn = new RadioButton("An");
        fxOff = new RadioButton("Aus");
        fxOff.setSelected(true);

        ToggleGroup activeGroup =new ToggleGroup();
        activeGroup.getToggles().addAll(fxOn,fxOff);
        audioFX = new Label("Metronom");

        soundpane = new VBox(10);
        this.getChildren().add(soundpane);
        listViewTip = new Tooltip("Mit Linksklick Sounds auf die Pads ziehen.\n Mit Rechtsklick Datei anzeigen lassen. ");
        soundlistview.setTooltip(listViewTip);
        soundpane.getChildren().add(soundlistview);
        soundpane.getChildren().add(audioFX);
        soundpane.getChildren().addAll(fxOn,fxOff);

        listViewTip.getStyleClass().add("tooltip");
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
