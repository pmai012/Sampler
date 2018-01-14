package View;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.layout.*;


/**
 * Created by User on 21.12.2017.
 */
public class PadView extends Pane {

    final int WIDTH = 250;
    final int HEIGHT = 500;

    private BorderPane root;
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
        root = new BorderPane();
        padBox = new FlowPane(Orientation.HORIZONTAL);
        padBox.setHgap(10);
        padBox.setVgap(10);
        padBox.setMaxHeight(HEIGHT);
        padBox.setMaxWidth(WIDTH);
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
        pad1 = new Button("test");
        pad2 = new Button("test");
        pad3 = new Button("test");
        pad4 = new Button("test");
        pad5 = new Button("test");
        pad6 = new Button("test");
        pad7 = new Button("test");
        pad8 = new Button("test");
        pad9 = new Button("test");
        pad10 = new Button("test");
        pad11 = new Button("test");
        pad12 = new Button("test");
        pad13 = new Button("test");
        pad14 = new Button("test");
        pad15 = new Button("test");
        pad16 = new Button("test");

        root.setCenter(padBox);
        padBox.getChildren().addAll(pad1, pad2, pad3, pad4, pad5, pad6, pad7, pad8,
                pad9, pad10, pad11,pad12, pad13, pad14, pad15, pad16);
        this.getChildren().add(padBox);


    }
}
