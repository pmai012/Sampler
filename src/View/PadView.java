package View;

import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


/**
 * Created by User on 21.12.2017.
 */
public class PadView extends Pane {

    final int WIDTH = 250;
    final int HEIGHT = 500;

    private BorderPane root;
    private VBox configBox;
    Rectangle pad1;
    Rectangle pad2;
    Rectangle pad3;
    Rectangle pad4;
    Rectangle pad5;
    Rectangle pad6;
    Rectangle pad7;
    Rectangle pad8;

    public PadView(){
        root = new BorderPane();
        configBox = new VBox(30);
        configBox.setMaxHeight(HEIGHT);
        configBox.setMaxWidth(WIDTH);
        pad1 = new Rectangle(100,100, Paint.valueOf("GREEN"));
        pad2 = new Rectangle(100,100, Paint.valueOf("RED"));
        pad3 = new Rectangle(100,100, Paint.valueOf("BLUE"));
        pad4 = new Rectangle(100,100, Paint.valueOf("YELLOW"));
        pad5 = new Rectangle(100,100, Paint.valueOf("PURPLE"));
        pad6 = new Rectangle(100,100, Paint.valueOf("PINK"));
        pad7 = new Rectangle(100,100, Paint.valueOf("CYAN"));
        pad8 = new Rectangle(100,100, Paint.valueOf("ORANGE"));


        root.setCenter(configBox);
        configBox.getChildren().addAll(pad1, pad2, pad3, pad4, pad5, pad6, pad7, pad8);
        getChildren().add(configBox);
    }
}
