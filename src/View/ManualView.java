package View;

import Controller.PadController;
import Controller.SettingController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ManualView extends Stage {
    final int WIDTH = 700;
    final int HEIGHT = 673;
    private Stage stage;
    private BorderPane root;
    private Scene scene;
    private Label headline;
    private HBox headBox;
    private HBox pictureBox;
    private HBox manualBox;
    private HBox togglebox;
    private Button manualPageButton;
    private ToggleGroup pages;
    private ToggleButton page1;
    private ToggleButton page2;
    private ToggleButton page3;
    private ToggleButton page4;
    private SettingController settingController;

    public ManualView(){
        init();
    }

    public ManualView(SettingController settingController){
        this.settingController = settingController;
        init();
    }

    private void init(){
        stage = this;
        root = new BorderPane();
        headline = new Label("Anleitung zum Sampler");
        headBox = new HBox();
        pictureBox = new HBox();
        manualBox = new HBox();
        togglebox = new HBox(10);
        manualPageButton = new Button();
        manualBox.getChildren().add(pictureBox);
        pictureBox.getChildren().add(manualPageButton);
        headBox.getChildren().add(headline);
        pages = new ToggleGroup();
        page1 = new ToggleButton();
        page2 = new ToggleButton();
        page3 = new ToggleButton();
        page4 = new ToggleButton();
        pages.getToggles().addAll(page1, page2, page3, page4);
        page1.setUserData("page1");
        page2.setUserData("page2");
        page3.setUserData("page3");
        page4.setUserData("page4");
        page1.setSelected(true);
        togglebox.getChildren().addAll(page1, page2, page3, page4);
        root.setTop(headBox);
        root.setCenter(pictureBox);
        root.setBottom(togglebox);
        scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add("CSS/SamplerGUI.css");
        stage.setTitle("Anleitung");
        stage.getIcons().add(new Image("Picture/LogoSampler.png"));
        stage.setMinWidth(700);
        stage.setMinHeight(650);
        stage.setMaxWidth(850);
        stage.setMaxHeight(720);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setX(870);
        stage.setY(163);

        headline.getStyleClass().add("headline");
        headBox.setAlignment(Pos.BASELINE_CENTER);
        togglebox.setAlignment(Pos.BASELINE_CENTER);
        pictureBox.setAlignment(Pos.CENTER);
        headBox.getStyleClass().add("headBox");
        pictureBox.getStyleClass().add("PictureBox");
        manualPageButton.getStyleClass().add("manualPage1");
        page1.getStyleClass().add("manualButtonOnPress");
        page2.getStyleClass().add("manualButton");
        page3.getStyleClass().add("manualButton");
        page4.getStyleClass().add("manualButton");
        togglebox.getStyleClass().add("toggleBox");
        pages.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(newValue != null){
                    if(newValue.equals(page1) && newValue.isSelected()) {
                        manualPageButton.getStyleClass().clear();
                        manualPageButton.getStyleClass().add("manualPage1");
                        page1.getStyleClass().clear();
                        page2.getStyleClass().clear();
                        page3.getStyleClass().clear();
                        page4.getStyleClass().clear();
                        page1.getStyleClass().add("manualButtonOnPress");
                        page2.getStyleClass().add("manualButton");
                        page3.getStyleClass().add("manualButton");
                        page4.getStyleClass().add("manualButton");
                        togglebox.getStyleClass().add("togglebox");
                    }
                    else if(newValue.equals(page2) && newValue.isSelected()){
                        manualPageButton.getStyleClass().clear();
                        manualPageButton.getStyleClass().add("manualPage2");
                        page1.getStyleClass().clear();
                        page2.getStyleClass().clear();
                        page3.getStyleClass().clear();
                        page4.getStyleClass().clear();
                        page1.getStyleClass().add("manualButton");
                        page2.getStyleClass().add("manualButtonOnPress");
                        page3.getStyleClass().add("manualButton");
                        page4.getStyleClass().add("manualButton");
                        togglebox.getStyleClass().add("togglebox");
                    }
                    else if(newValue.equals(page3) && newValue.isSelected()){
                        manualPageButton.getStyleClass().clear();
                        manualPageButton.getStyleClass().add("manualPage3");
                        page1.getStyleClass().clear();
                        page2.getStyleClass().clear();
                        page3.getStyleClass().clear();
                        page4.getStyleClass().clear();
                        page1.getStyleClass().add("manualButton");
                        page2.getStyleClass().add("manualButton");
                        page3.getStyleClass().add("manualButtonOnPress");
                        page4.getStyleClass().add("manualButton");
                        togglebox.getStyleClass().add("togglebox");
                    }
                    else if(newValue.equals(page4) && newValue.isSelected()){
                        manualPageButton.getStyleClass().clear();
                        manualPageButton.getStyleClass().add("manualPage4");
                        page1.getStyleClass().clear();
                        page2.getStyleClass().clear();
                        page3.getStyleClass().clear();
                        page4.getStyleClass().clear();
                        page1.getStyleClass().add("manualButton");
                        page2.getStyleClass().add("manualButton");
                        page3.getStyleClass().add("manualButton");
                        page4.getStyleClass().add("manualButtonOnPress");
                        togglebox.getStyleClass().add("togglebox");
                    }
                }
            }
         });
        stage.show();
    }
}
