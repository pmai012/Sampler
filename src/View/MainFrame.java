package View;/**
 * Created by User on 21.12.2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFrame extends Application {

    final int WIDTH = 1280;
    final int HEIGHT = 720;

    private BorderPane root;
    private PadView padView;
    private RecordView recordView;
    private SettingView settingView;
    private SoundView soundView;

    public MainFrame(){
        root = new BorderPane();
        padView = new PadView();
        recordView = new RecordView();
        settingView = new SettingView();
        soundView = new SoundView();
    }


    @Override
    public void start(Stage primaryStage) {
        root.setTop(padView);
        root.setRight(settingView);
        root.setLeft(recordView);
        root.setBottom(soundView);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add("CSS/MainFrameGUI.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sampler");
        primaryStage.setMinHeight(640);
        primaryStage.setMinWidth(1200);

        root.getStylesheets().addAll("root");


        primaryStage.show();
    }
}
