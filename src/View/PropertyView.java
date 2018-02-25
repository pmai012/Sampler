package View;

import Model.Pad;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Created by Pascal on 27.01.2018.
 */
public class PropertyView extends Stage{
    final int WIDTH = 435;
    final int HEIGHT = 60;
    private  Stage stage;
    private Pane root;
    private Scene scene;
    private Slider start;
    private Slider end;
    Pad pad;

    public PropertyView(Pad ref) {

        this.pad = ref;

        init();
    }

        private void init () {

            stage = this;
            root = new Pane();
            scene = new Scene(root, WIDTH, HEIGHT);
            scene.getStylesheets().add("CSS/SamplerGUI.css");

            start = new Slider(0, 0, 1);
            end = new Slider(0, 0, 0);

            root.getChildren().add(start);


            stage.getIcons().add(new Image("Picture/LogoSampler.png"));
            stage.setMinWidth(435);
            stage.setMinHeight(500);
            stage.setMaxWidth(435);
            stage.setMaxHeight(720);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setX(870);
            stage.setY(163);

            stage.show();

        }
}
