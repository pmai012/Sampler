package View;/**
 * Created by User on 21.12.2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFrame extends Application {

    final int WIDTH = 1280;
    final int HEIGHT = 720;

    private BorderPane root;
    private PadView padView;
    private RecordView recordView;
    private SettingView settingView;
    private SoundView soundView;
    private VBox configBox;

    public MainFrame(){
        root = new BorderPane();
        configBox = new VBox(10);
        padView = new PadView();
        recordView = new RecordView();
        soundView = new SoundView();
        settingView = new SettingView();
    }


    @Override
    public void start(Stage primaryStage) {

        configBox.getChildren().add(recordView);
        configBox.getChildren().add(soundView);
        configBox.getChildren().add(settingView);


        VBox test = new VBox(10);

        test.setStyle("-fx-background-color: " + "#610002");

        root.setRight(configBox);
        root.setLeft(padView);


        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add("CSS/SamplerGUI.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sampler");
        primaryStage.getIcons().add(new Image("Picture/LogoSampler.png"));
        primaryStage.setMinHeight(640);
        primaryStage.setMinWidth(1200);

        root.getStyleClass().addAll("root");
        configBox.getStyleClass().addAll("configBox");

        primaryStage.show();
    }
}
