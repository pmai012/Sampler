package View;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.layout.*;


/**
 * Created by User on 21.12.2017.
 */
public class PadView extends Pane {

    final int WIDTH = 600;
    final int HEIGHT = 600;

    private BorderPane rootPV;
    private FlowPane padBox;

    private Button pad1, pad2, pad3, pad4, pad5, pad6, pad7, pad8,
            pad9, pad10, pad11, pad12, pad13, pad14, pad15, pad16;

    /*
    Rectangle pad1;
    Rectangle pad2;
    Rectangle pad3;
    Rectangle pad4;
    Rectangle pad5;
    Rectangle pad6;
    Rectangle pad7;
    Rectangle pad8;
    */

    public PadView(){
        rootPV = new BorderPane();
        padBox = new FlowPane(Orientation.HORIZONTAL);
        padBox.setHgap(10);
        padBox.setVgap(10);
        padBox.setMaxHeight(HEIGHT);
        padBox.setMaxWidth(WIDTH);
        this.getStylesheets().add("CSS/SamplerGUI.css");
        /*
        pad1 = new Rectangle(100,100, Paint.valueOf("GREEN"));
        pad2 = new Rectangle(100,100, Paint.valueOf("RED"));
        pad3 = new Rectangle(100,100, Paint.valueOf("BLUE"));
        pad4 = new Rectangle(100,100, Paint.valueOf("YELLOW"));
        pad5 = new Rectangle(100,100, Paint.valueOf("PURPLE"));
        pad6 = new Rectangle(100,100, Paint.valueOf("PINK"));
        pad7 = new Rectangle(100,100, Paint.valueOf("CYAN"));
        pad8 = new Rectangle(100,100, Paint.valueOf("ORANGE"));
        */
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

        padBox.getStyleClass().addAll("padView");

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




    }
}
