package View;

import Controller.PadController;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.layout.*;


/**
 * Created by User on 21.12.2017.
 */
public class PadView extends Pane {

    final int WIDTH = 600;
    final int HEIGHT = 600;
    private PadController padController;
    private BorderPane rootPV;
    private FlowPane padBox;

    private Button pad1, pad2, pad3, pad4, pad5, pad6, pad7, pad8,
            pad9, pad10, pad11, pad12, pad13, pad14, pad15, pad16;


    public PadView(){

        rootPV = new BorderPane();
        padBox = new FlowPane(Orientation.HORIZONTAL);
        padBox.setHgap(10);
        padBox.setVgap(10);
        padBox.setMaxHeight(HEIGHT);
        padBox.setMaxWidth(WIDTH);
        //this.getStylesheets().add("CSS/SamplerGUI.css");

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



        rootPV.setCenter(padBox);
        padBox.getChildren().addAll(pad1, pad2, pad3, pad4, pad5, pad6, pad7, pad8,
                pad9, pad10, pad11,pad12, pad13, pad14, pad15, pad16);
        this.getChildren().add(padBox);

        padBox.getStyleClass().addAll("padBox");

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

        padController = new PadController(new Button[]{pad1,pad2, pad3,pad4,pad5,pad6, pad7,pad8,pad9,pad10,pad11,pad12,pad13,pad14,pad15,pad16});
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

        //Versuch
        pad1.setOnDragOver(padController.acceptdrag);
        pad1.setOnDragDropped(padController.getData);

    }



}
