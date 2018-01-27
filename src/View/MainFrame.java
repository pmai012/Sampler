package View;/**
 * Created by User on 21.12.2017.
 */

import Controller.Keyinput;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class MainFrame extends Application implements Observer {

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
        configBox = new VBox(40);
        padView = new PadView(this);
        recordView = new RecordView(padView.getPadController());
        soundView = new SoundView();


    }

    public void init() {

    }

    public void stop() {
        System.exit(1);
    }

    @Override
    public void start(Stage primaryStage) {
        settingView = new SettingView(primaryStage, padView.getPads(), this);
        //configBox.setMaxHeight(800);
        //configBox.setMaxWidth(600);
        configBox.getChildren().add(recordView);
        configBox.getChildren().add(soundView);
        //configBox.getChildren().add(settingView);


        VBox test = new VBox(10);

        //test.setStyle("-fx-background-color: " + "#610002");

        root.setTop(settingView);
        root.setRight(configBox);
        root.setLeft(padView);


        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add("CSS/SamplerGUI.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sampler");
        primaryStage.getIcons().add(new Image("Picture/LogoSampler.png"));
        primaryStage.setMinHeight(640);
        primaryStage.setMinWidth(1200);

        root.getStyleClass().addAll("mainFrame");
        configBox.getStyleClass().addAll("configBox");


        Keyinput keyinput = new Keyinput(padView.getPadController(), settingView.getSettingController(), recordView.getRecordController());
        root.setOnKeyPressed(keyinput);


        primaryStage.show();
    }

    @Override
    public void update(Observable o, Object arg) {

        if(arg.equals("padsladen") ){
        padView.getPadController().setPad(settingView.getSettingController().getpads());
        arg ="pad";
        }
        padView.update(arg);
    }
}
