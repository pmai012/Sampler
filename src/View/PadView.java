package View;

import Controller.Keyinput;
import Controller.PadController;
import Model.Pad;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.Observer;

public class PadView extends Pane {

    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private PadController padController;
    private BorderPane rootPV;
    private TilePane padBox;
    private Observer observer;
    private ContextMenu contextMenu;
    private MenuItem addEffect;
    private MenuItem editEffect;
    private MenuItem padproperty;
    private MenuItem deleteEffect;
    private MenuItem deleteSound;
    private Tooltip padTip;
    private Button[] pads;
    private Button pad1, pad2, pad3, pad4, pad5, pad6, pad7, pad8,
            pad9, pad10, pad11, pad12, pad13, pad14, pad15, pad16;


    public PadView(Observer obs) {
        observer = obs;
        rootPV = new BorderPane();
        contextMenu = new ContextMenu();
        addEffect = new MenuItem("Effekt hinzufügen");
        editEffect = new MenuItem("Effekt bearbeiten");
        deleteEffect = new MenuItem("Effekt löschen");
        deleteSound = new MenuItem("Sound löschen");
        padproperty = new MenuItem("Eigenschaften");
        editEffect.setDisable(true);
        addEffect.setDisable(true);
        deleteEffect.setDisable(true);
        deleteSound.setDisable(true);
        contextMenu.getItems().addAll(addEffect, editEffect, deleteEffect, deleteSound, padproperty);
        padBox = new TilePane(Orientation.HORIZONTAL);
        padBox.setHgap(10);
        padBox.setVgap(10);
        padBox.setMaxHeight(HEIGHT);
        padBox.setMaxWidth(WIDTH);
        // padBox.prefWidthProperty().bind(this.widthProperty());
        // padBox.prefHeightProperty().bind(this.heightProperty());
        pad1 = new Button();
        pad2 = new Button();
        pad3 = new Button();
        pad4 = new Button();
        pad5 = new Button();
        pad6 = new Button();
        pad7 = new Button();
        pad8 = new Button();
        pad9 = new Button();
        pad10 = new Button();
        pad11 = new Button();
        pad12 = new Button();
        pad13 = new Button();
        pad14 = new Button();
        pad15 = new Button();
        pad16 = new Button();
        pads = new Button[]{pad1, pad2, pad3, pad4, pad5, pad6, pad7, pad8,
                pad9, pad10, pad11, pad12, pad13, pad14, pad15, pad16};
        rootPV.setCenter(padBox);
        padTip = new Tooltip("Linksklick zum abspielen\nRechtsklick für Effekte");
        padTip.getStyleClass().add("tooltip");
        Keyinput input = new Keyinput();
        for (Button pad : pads) {
            pad.setTooltip(padTip);
        }

        for (Button pad : pads) {
            padBox.getChildren().add(pad);
            pad.setContextMenu(contextMenu);
        }

        this.getChildren().add(padBox);
        padBox.getStyleClass().addAll("padBox");
        contextMenu.getStyleClass().addAll("menuItem", "context-menu");

        for (Button pad : pads) {
            pad.getStyleClass().add("pad");
        }

        for (int i = 0; i < pads.length; i++) {

            if (i < 4) {
                pads[i].getStyleClass().add("padG");
            } else if (i < 8) {
                pads[i].getStyleClass().add("padB");
            } else if (i < 12) {
                pads[i].getStyleClass().add("padP");
            } else if (i < 16) {
                pads[i].getStyleClass().add("padR");
            }
        }

        padController = new PadController(pads, observer, this);

        for (Button pad : pads) {
            pad.setOnDragOver(padController.acceptdrag);
            pad.setOnDragDropped(padController.getData);
            pad.addEventHandler(MouseEvent.MOUSE_PRESSED, padController.pressed);
            pad.addEventHandler(MouseEvent.MOUSE_RELEASED, padController.rightclick);
        }
        addEffect.setOnAction(padController.contextMenu_addEffectClicked);
        deleteEffect.setOnAction(padController.contextMenu_deleteEffectClicked);
        editEffect.setOnAction(padController.contextMenu_editEffectClicked);
        deleteSound.setOnAction(padController.contextMenu_deleteSoundClicked);
        padproperty.setOnAction(padController.contextMenu_properties);
    }

    public Pad[] getPads() {
        return padController.getPad();
    }

    public PadController getPadController() {
        return padController;
    }

    /**
     * Updated die aktuellen Farben der Pads
     *
     * @param arg meist "pad" als Argument
     */
    public void update(Object arg) {


        String command = (String) arg;

        if (command.equals("pad")) {
            Pad[] pad = getPads();


            //Fehler beim laden

            for (int i = 0; i < 16; i++) {

                if (pad[i] == null) {

                    //es werden nuller erkennt Probleme bei dem removen und adden
                    //Das erste mal funktioniert es aber danach nicht
                    // Grund unbekannt

                    pads[i].getStyleClass().add("pad");


                    if (i < 4) {
                        pads[i].getStyleClass().remove("padGUsed");
                        pads[i].getStyleClass().add("padG");

                    } else if (i < 8) {
                        pads[i].getStyleClass().remove("padBUsed");
                        pads[i].getStyleClass().add("padB");
                    } else if (i < 12) {

                        pads[i].getStyleClass().remove("padPUsed");
                        pads[i].getStyleClass().add("padP");
                    } else if (i < 16) {
                        pads[i].getStyleClass().remove("padRUsed");
                        pads[i].getStyleClass().add("padR");
                    }
                } else {

                    deleteSound.setDisable(false);
                    if (i < 4) {
                        pads[i].getStyleClass().remove("padGUsed");
                        pads[i].getStyleClass().add("padGUsed");
                    } else if (i < 8) {
                        pads[i].getStyleClass().remove("padBUsed");
                        pads[i].getStyleClass().add("padBUsed");
                    } else if (i < 12) {
                        pads[i].getStyleClass().remove("padPUsed");
                        pads[i].getStyleClass().add("padPUsed");
                    } else if (i < 16) {
                        pads[i].getStyleClass().remove("padRUsed");
                        pads[i].getStyleClass().add("padRUsed");
                    }
                    if (pad[i].hasEffects()) {
                        pads[padController.getClickedPadIndex()].getContextMenu().getItems().get(0).setDisable(true);
                        pads[padController.getClickedPadIndex()].getContextMenu().getItems().get(1).setDisable(false);
                        pads[padController.getClickedPadIndex()].getContextMenu().getItems().get(2).setDisable(false);
                    } else {
                        pads[padController.getClickedPadIndex()].getContextMenu().getItems().get(0).setDisable(false);
                        pads[padController.getClickedPadIndex()].getContextMenu().getItems().get(1).setDisable(true);
                        pads[padController.getClickedPadIndex()].getContextMenu().getItems().get(2).setDisable(true);
                    }
                }


            }
        }

    }

}