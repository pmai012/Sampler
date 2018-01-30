package View;

import Controller.EffectController;
import Controller.PadController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;


/**
 * Created by Pascal on 27.01.2018.
 */
public class EffectView extends Stage{
    final int WIDTH = 435;
    final int HEIGHT = 673;
    private Stage stage;
    private BorderPane root;
    private Scene scene;
    private HBox effectsBox = new HBox();
    private HBox submitBox = new HBox();
    private HBox sliderBox;
    private Pane activeView;
    private Button submitButton;
    private EffectController effectController;

    private ToggleGroup allEffects = new ToggleGroup();
    private ToggleButton delayEffect = new ToggleButton("Delay");
    private ToggleButton flangerEffect = new ToggleButton("Flanger");
    private ToggleButton bitCrushEffect = new ToggleButton("Bit Crush");
    private ToggleButton notchFilter = new ToggleButton("Notch Filter");

    private DelayEffectView delayEffectView;
    private FlangerEffectView flangerEffectView;
    private BitCrushEffectView bitCrushEffectView;
    private NotchFilterEffectView notchFilterEffectView;

    public EffectView(PadController ref) {
        activeView = new Pane();
        stage = this;
        root = new BorderPane();
        submitButton = new Button("Anwenden");
        submitButton.getStyleClass().add("submitButton");
        effectController = new EffectController(ref, this);

        sliderBox = new HBox();
        sliderBox.setMaxHeight(300);
        sliderBox.setMaxWidth(300);
        sliderBox.setAlignment(Pos.CENTER);
        root.setCenter(sliderBox);
        delayEffect.setToggleGroup(allEffects);
        flangerEffect.setToggleGroup(allEffects);
        bitCrushEffect.setToggleGroup(allEffects);
        notchFilter.setToggleGroup(allEffects);
        delayEffect.getStyleClass().add("toggle-button");
        flangerEffect.getStyleClass().add("toggle-button");
        bitCrushEffect.getStyleClass().add("toggle-button");
        notchFilter.getStyleClass().add("toggle-button");
        effectsBox.getChildren().addAll(delayEffect, flangerEffect, bitCrushEffect, notchFilter);
        effectsBox.getStyleClass().add("effectsBox");
        root.getChildren().add(effectsBox);
        submitBox.getChildren().add(submitButton);
        scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add("CSS/SamplerGUI.css");
        stage.setTitle("Pad " + ref.getClickedPadIndex() + " - Effekt hinzufügen");
        stage.getIcons().add(new Image("Picture/LogoSampler.png"));
        stage.setMinWidth(435);
        stage.setMinHeight(500);
        stage.setMaxWidth(435);
        stage.setMaxHeight(720);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(stage.getX() + stage.getWidth() / 2d);
        stage.setY(stage.getY() + stage.getHeight() / 2d);
        stage.setX(870);
        stage.setY(163);

        stage.show();

        allEffects.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle toggle, Toggle new_toggle) {
                if (new_toggle == null) {
                    if (root.getChildren().contains(submitBox)) {
                        root.getChildren().removeAll(submitBox);
                    }
                    sliderBox.getChildren().clear();
                }
                else{
                    new_toggle.setSelected(true);
                    root.setBottom(submitBox);
                    submitBox.getStyleClass().add("submitBox");
                    sliderBox.getChildren().clear();
                    if (new_toggle.equals(delayEffect)){

                        showDelaySettings();
                    }
                    if (new_toggle.equals(flangerEffect)){

                        showFlangerSettings();
                    }
                    if (new_toggle.equals(bitCrushEffect)){

                        showBitCrushSettings();
                    }
                    if (new_toggle.equals(notchFilter)){

                        showNotchFilterSettings();
                    }
                }
            }
        });
    submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, effectController.submit);
    }
    private void showDelaySettings()
    {
        if (delayEffectView == null){delayEffectView = new DelayEffectView();}

        activeView = delayEffectView;
        sliderBox.getChildren().add(delayEffectView);
    }
    private void showFlangerSettings()
    {
        if (flangerEffectView == null){flangerEffectView = new FlangerEffectView();}


        activeView = flangerEffectView;
        sliderBox.getChildren().add(flangerEffectView);
    }
    private void showBitCrushSettings()
    {
        if (bitCrushEffectView == null){bitCrushEffectView = new BitCrushEffectView();}


        activeView = bitCrushEffectView;
        sliderBox.getChildren().add(bitCrushEffectView);
    }
    private void showNotchFilterSettings()
    {
        if (notchFilterEffectView == null){notchFilterEffectView = new NotchFilterEffectView();}


        activeView = notchFilterEffectView;
        sliderBox.getChildren().add(notchFilterEffectView);
    }
    public Pane getActiveView(){return this.activeView;}

    public DelayEffectView returnDelayView()
    {return this.delayEffectView;}
    public FlangerEffectView returnFlangerView()
    {return this.flangerEffectView;}
    public BitCrushEffectView returnBitCrushView()
    {return this.bitCrushEffectView;}
    public NotchFilterEffectView returnNotchFilterView()
    {return this.notchFilterEffectView;}
}
