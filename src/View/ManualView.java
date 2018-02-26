package View;

import Controller.PadController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ManualView extends Stage {
    final int WIDTH = 800;
    final int HEIGHT = 673;
    private Stage stage;
    private BorderPane root;
    private Scene scene;
    private HBox manualBox;
    private PadController padController;

    public ManualView(){
        init();
    }

    public ManualView(PadController padController){
        this.padController = padController;

    }

    private void init(){
        stage = this;
        root = new BorderPane();
        manualBox = new HBox();
        root.setCenter(manualBox);
        scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add("CSS/SamplerGUI.css");
        stage.setTitle("Anleitung");
        stage.getIcons().add(new Image("Picture/LogoSampler.png"));
        stage.setMinWidth(700);
        stage.setMinHeight(500);
        stage.setMaxWidth(850);
        stage.setMaxHeight(720);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setX(870);
        stage.setY(163);
    }
}
