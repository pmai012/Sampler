package View;

import Controller.PadController;
import Controller.SoundController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class SoundView   extends Pane {

    private SoundController soundController;
    private VBox soundpane;
    private ObservableList<String> soundObList;
    private ListView soundlistview;
    private Label metronom;
    private RadioButton On, Off;
    private Tooltip listviewTip;


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
        On = new RadioButton("An");
        Off = new RadioButton("Aus");
        Off.setSelected(true);

        ToggleGroup activeGroup =new ToggleGroup();
        activeGroup.getToggles().addAll(On, Off);
        metronom = new Label("Metronom");
        listviewTip = new Tooltip("Sounddatei zum rüberziehen");
        soundlistview.setTooltip(listviewTip);
        soundpane = new VBox(10);
        this.getChildren().add(soundpane);

        soundpane.getChildren().add(soundlistview);
        soundpane.getChildren().add(metronom);
        soundpane.getChildren().addAll(On, Off);

        metronom.getStyleClass().addAll("title", "radioButton");
        Off.getStyleClass().addAll("title", "radioButton");
        On.getStyleClass().addAll("title", "radioButton");
        listviewTip.getStyleClass().add("tooltip");

        Off.setOnAction(padController.metronomaus);
        On.setOnAction(padController.metronoman);
    }

    public SoundController getSoundController() {
        return soundController;
    }

}
