package View;

import Controller.PadController;
import Controller.SoundController;
import Model.Pad;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.Observable;
import java.util.Observer;

public class PadView extends Pane {

    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private PadController padController;
    private SoundController soundController;
    private BorderPane rootPV;
    private TilePane padBox;
    private Observer observer;
    private ContextMenu contextMenu;
    private MenuItem addEffect;
    private MenuItem editEffect;
    private MenuItem deleteEffect;
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
        editEffect.setDisable(true);
        deleteEffect.setDisable(true);
        contextMenu.getItems().addAll(addEffect, editEffect, deleteEffect);
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
            pad.addEventHandler(MouseEvent.MOUSE_PRESSED, padController.pressed);
            pad.addEventHandler(MouseEvent.MOUSE_RELEASED, padController.rightclick);
            pad.setOnDragOver(padController.acceptdrag);
            pad.setOnDragDropped(padController.getData);
        }
        addEffect.setOnAction(padController.contextMenu_addEffectClicked);
        deleteEffect.setOnAction(padController.contextMenu_deleteEffectClicked);
        editEffect.setOnAction(padController.contextMenu_editEffectClicked);
    }




    public Pad[] getPads(){
        return padController.getPad();
    }

    public PadController getPadController(){
        return padController;
    }

    public TilePane getPadBox() {
        return padBox;
    }

    public void update(Object arg) {

        System.out.println("update ");
        String command = (String) arg;


        if(command.equals("pad") ){
            int[] notnull = padController.whoisnotnull();
            for (int i = 0; i < notnull.length; i++) {

                    if(notnull[i] < 4){
                        pads[notnull[i]].getStyleClass().add("padGUsed");
                    } else if (notnull[i] < 8) {
                        pads[notnull[i]].getStyleClass().add("padBUsed");
                    } else if (notnull[i] < 12) {
                        pads[notnull[i]].getStyleClass().add("padPUsed");
                    } else if (notnull[i] < 16) {
                        pads[notnull[i]].getStyleClass().add("padRUsed");
                    }
                if(getPads()[notnull[i]].hasEffects()){
                        addEffect.setDisable(true);
                        deleteEffect.setDisable(false);
                        editEffect.setDisable(false);
                }
                else {
                    addEffect.setDisable(false);
                    deleteEffect.setDisable(true);
                    editEffect.setDisable(true);
                }
            }
        }

    }

    public void cleanVisual(int i) {
        pads[i].getStyleClass().add("pad");

        if (i < 4) {
            pads[i].getStyleClass().add("padG");
            pads[i].getStyleClass().remove("padGUsed");
        }else if (i < 8) {
            pads[i].getStyleClass().add("padB");
            pads[i].getStyleClass().remove("padBUsed");
        }else if (i < 12) {
            pads[i].getStyleClass().add("padP");
            pads[i].getStyleClass().remove("padPUsed");
        } else if (i < 16) {
            pads[i].getStyleClass().add("padR");
            pads[i].getStyleClass().remove("padRUsed");
        }
    }
}