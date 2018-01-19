package View;

import Controller.PadController;
import Model.Pad;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.Observable;
import java.util.Observer;


/**
 * Created by User on 21.12.2017.
 */
public class PadView extends Pane implements Observer {

    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private PadController padController;
    private BorderPane rootPV;
    private TilePane padBox;

    private Button[] pads;
    private Button pad1, pad2, pad3, pad4, pad5, pad6, pad7, pad8,
            pad9, pad10, pad11, pad12, pad13, pad14, pad15, pad16;


    protected PadView(){

        rootPV = new BorderPane();
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
                pad9, pad10, pad11,pad12, pad13, pad14, pad15, pad16};

        rootPV.setCenter(padBox);

        for (Button pad: pads) {
            padBox.getChildren().add(pad);
        }
        //padBox.getChildren().addAll(pad1, pad2, pad3, pad4, pad5, pad6, pad7, pad8,
         //       pad9, pad10, pad11,pad12, pad13, pad14, pad15, pad16);
        this.getChildren().add(padBox);

        padBox.getStyleClass().addAll("padBox");

        for (Button pad: pads) {
            pad.getStyleClass().add("pad");
        }


        for(int i = 0; i < pads.length; i++) {

            if (i < 4) {
                pads[i].getStyleClass().add("padG");
            }else if (i < 8) {
                pads[i].getStyleClass().add("padB");
            }else if (i < 12) {
                pads[i].getStyleClass().add("padP");
            } else if (i < 16) {
                pads[i].getStyleClass().add("padR");
            }
        }
        /*
        pad1.getStyleClass().addAll("pad");
        pad2.getStyleClass().addAll("pad");
        pad3.getStyleClass().addAll("pad");
        pad4.getStyleClass().addAll("pad");
        pad5.getStyleClass().addAll("pad");
        pad6.getStyleClass().addAll("pad");
        pad7.getStyleClass().addAll("pad");
        pad8.getStyleClass().addAll("pad");
        pad9.getStyleClass().addAll("pad");
        pad10.getStyleClass().addAll("pad");
        pad11.getStyleClass().addAll("pad");
        pad12.getStyleClass().addAll("pad");
        pad13.getStyleClass().addAll("pad");
        pad14.getStyleClass().addAll("pad");
        pad15.getStyleClass().addAll("pad");
        pad16.getStyleClass().addAll("pad");
        */

        padController = new PadController(pads, this);



        for (Button pad:pads) {
            pad.addEventHandler(MouseEvent.MOUSE_RELEASED,padController.rightclick);

            pad.addEventHandler(MouseEvent.MOUSE_PRESSED,padController.pressed);


            pad.setOnDragOver(padController.acceptdrag);
            pad.setOnDragDropped(padController.getData);


        }
        /*
        pad1.setOnAction(padController.play);
        pad2.setOnAction(padController.play);
        pad3.setOnAction(padController.play);
        pad4.setOnAction(padController.play);
        pad5.setOnAction(padController.play);
        pad6.setOnAction(padController.play);
        pad7.setOnAction(padController.play);
        pad8.setOnAction(padController.play);
        pad9.setOnAction(padController.play);
        pad10.setOnAction(padController.play);
        pad11.setOnAction(padController.play);
        pad12.setOnAction(padController.play);
        pad13.setOnAction(padController.play);
        pad14.setOnAction(padController.play);
        pad15.setOnAction(padController.play);
        pad16.setOnAction(padController.play);
        */

    }


    @Override
    public void update(Observable o, Object arg) {

        System.out.println("update");
        String command = (String) arg;

        if(command.equals("pad") ){

            for (int i = 0; i < padController.whoisnotnull().length; i++) {

               {
                    if(i < 4){
                        pads[i].getStyleClass().setAll("padGUsed");
                    } else if (i < 8) {
                        pads[i].getStyleClass().setAll("padBUsed");
                    } else if (i < 12) {
                        pads[i].getStyleClass().setAll("padPUsed");
                    } else if (i < 16) {
                        pads[i].getStyleClass().setAll("padRUsed");
                    }

                }

            }
        }



    }
}
