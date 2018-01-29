package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Created by Pascal on 28.01.2018.
 */
public class DelayEffectView extends Pane {

    private Slider decayLevel = new Slider(0,1,0);
    private Slider delayLevel = new Slider(0,1,0);
    private GridPane root;
    final Label decay = new Label("Decay Level:");
    final Label delay = new Label("Delay Level:");
    final Label decayRate = new Label (Double.toString(decayLevel.getValue()));
    final Label delayRate = new Label (Double.toString(delayLevel.getValue()));

    public DelayEffectView(){
        setId("Delay");
        root = new GridPane();
        decayLevel.setShowTickLabels(true);
        decayLevel.setShowTickMarks(true);
        decayLevel.setMajorTickUnit(0.5);
        decayLevel.setMinorTickCount(5);
        decayLevel.setBlockIncrement(0.1);
        delayLevel.setShowTickLabels(true);
        delayLevel.setShowTickMarks(true);
        delayLevel.setMajorTickUnit(0.5);
        delayLevel.setMinorTickCount(5);
        delayLevel.setBlockIncrement(0.1);

        decayRate.setTextFill(Color.WHITESMOKE);
        delayRate.setTextFill(Color.WHITESMOKE);
        decay.setTextFill(Color.WHITESMOKE);
        delay.setTextFill(Color.WHITESMOKE);

        root.setPadding(new Insets(10, 10, 10, 0));
        root.setVgap(50);
        root.setHgap(40);

        this.getChildren().add(root);
        GridPane.setConstraints(decay,0,1);
        root.getChildren().add(decay);

        GridPane.setConstraints(decayLevel,1,1);
        root.getChildren().add(decayLevel);

        GridPane.setConstraints(decayRate,2,1);
        root.getChildren().add(decayRate);

        GridPane.setConstraints(delay,0,2);
        root.getChildren().add(delay);

        GridPane.setConstraints(delayLevel,1,2);
        root.getChildren().add(delayLevel);

        GridPane.setConstraints(delayRate,2,2);
        root.getChildren().add(delayRate);


        decayLevel.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                decayRate.setText(String.format("%.2f", new_val));
            }
        });
        delayLevel.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                delayRate.setText(String.format("%.2f", new_val));
            }
        });
    }
    public double getDecayValue(){ return decayLevel.getValue();}
    public double getDelayValue(){ return delayLevel.getValue();}
}
