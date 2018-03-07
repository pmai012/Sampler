package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GainEffectView extends Pane{

    private GridPane root;
    private Slider gainLevel;
    final private Label gain = new Label("Gain Level:");
    private Label gainRate;

    public GainEffectView(){
        gainLevel = new Slider(-50, 50, 0);
        gainRate = new Label(Double.toString(gainLevel.getValue()));
        init();
    }

    public GainEffectView(double gainValue){
        gainLevel = new Slider(-50, 50, gainValue);
        gainRate = new Label(Double.toString(gainLevel.getValue()));
        init();
    }

    private void init(){
        this.setId("Gain");
        root = new GridPane();


        gainRate.setTextFill(Color.WHITESMOKE);
        gain.setTextFill(Color.WHITESMOKE);

        root.setPadding(new Insets(10, 10, 10, 0));
        root.setVgap(50);
        root.setHgap(40);

        this.getChildren().add(root);
        GridPane.setConstraints(gain, 0, 1);
        root.getChildren().add(gain);
        GridPane.setConstraints(gainLevel, 1, 1);
        root.getChildren().add(gainLevel);
        GridPane.setConstraints(gainRate, 2, 1);
        root.getChildren().add(gainRate);

        gainLevel.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                gainRate.setText(String.format("%.2f", newValue));
            }
        });

    }

    public float getGainValue(){
        return (float) gainLevel.getValue();
    }

}
