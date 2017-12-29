package View;/**
 * Created by User on 21.12.2017.
 */

import Model.Pad;
import Model.Record;
import Model.Track;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFrame extends Application {
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


        primaryStage.show();
    }
}
