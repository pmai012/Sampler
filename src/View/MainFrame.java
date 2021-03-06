package View;

import Controller.Keyinput;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends Application implements Observer {

    private final int WIDTH = 1280;
    private final int HEIGHT = 690;
    private BorderPane root;
    private PadView padView;
    private RecordView recordView;
    private SettingView settingView;
    private SoundView soundView;
    private VBox configBox;
    private StackPane padviewroot;


    public MainFrame(){

        root = new BorderPane();
        configBox = new VBox(40);
        padView = new PadView(this);
        padviewroot = new StackPane();
        recordView = new RecordView(padView.getPadController(),this);
        soundView = new SoundView(padView.getPadController());


    }

    public void init() {

    }

    public void stop() {
        recordView.getRecordController().deleteRecordfile();
        System.exit(1);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

         settingView = new SettingView(primaryStage, padView.getPads(), this, recordView.getRecordController());
        recordView.getRecordController().refsettingController(settingView.getSettingController());
        configBox.getChildren().add(recordView);
        configBox.getChildren().add(soundView);
        VBox test = new VBox(10);
        root.setTop(settingView);
        root.setRight(configBox);
        root.setLeft(padviewroot);
        padviewroot.getChildren().add(padView);
        root.setOnDragOver(settingView.getSettingController().acceptdrag);
        root.setOnDragDropped(settingView.getSettingController().getData);


        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add("CSS/SamplerGUI.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sampler");
        primaryStage.getIcons().add(new Image("Picture/LogoSampler.png"));
        primaryStage.setMinHeight(710);
        primaryStage.setMinWidth(960);
        primaryStage.setMaxHeight(760);
        primaryStage.setMaxWidth(1400);

        root.getStyleClass().addAll("mainFrame");
        configBox.getStyleClass().addAll("configBox");

        Keyinput keyinput = new Keyinput(padView.getPadController(), settingView.getSettingController(), recordView.getRecordController());
        root.setOnKeyPressed(keyinput);
        root.setOnKeyReleased(keyinput);

        primaryStage.show();
    }

    @Override
    public void update(Observable o, Object arg) {

        if(arg.equals("padsladen") ){
        padView.getPadController().setPad(settingView.getSettingController().getpads());
         arg ="pad";
        }

        padView.update(arg);
        recordView.update(arg);
    }



}
