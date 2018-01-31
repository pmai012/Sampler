package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Created by Pascal on 29.01.2018.
 */
public class NotchFilterEffectView extends Pane {
    private GridPane root;

    private Slider bandWidth = new Slider(0,44100,0);
    private Slider cutoff = new Slider(0,44100,0);
    private Slider sampleRate = new Slider(0,44100,0);

    final Label lbl_Bandwidth = new Label("Bandwidth [Hz]:");
    final Label lbl_Cutoff = new Label("Cutoff:");
    final Label lbl_SampleRate= new Label("Sample Rate:");

    final Label lbl_BandwidthValue = new Label (Double.toString(bandWidth.getValue()));
    final Label lbl_CutoffValue = new Label (Double.toString(cutoff.getValue()));
    final Label lbl_SampleRateValue = new Label (Double.toString(sampleRate.getValue()));

    public NotchFilterEffectView(){
        setId("NotchFilter");
        root = new GridPane();

        lbl_Bandwidth.setTextFill(Color.WHITESMOKE);
        lbl_Cutoff.setTextFill(Color.WHITESMOKE);
        lbl_SampleRate.setTextFill(Color.WHITESMOKE);
        lbl_BandwidthValue.setTextFill(Color.WHITESMOKE);
        lbl_CutoffValue.setTextFill(Color.WHITESMOKE);
        lbl_SampleRateValue.setTextFill(Color.WHITESMOKE);

        root.setPadding(new Insets(10, 5, 10, 0));
        root.setVgap(50);
        root.setHgap(30);

        this.getChildren().add(root);
        GridPane.setConstraints(lbl_Bandwidth,0,1);
        root.getChildren().add(lbl_Bandwidth);

        GridPane.setConstraints(bandWidth,1,1);
        root.getChildren().add(bandWidth);

        GridPane.setConstraints(lbl_BandwidthValue,2,1);
        root.getChildren().add(lbl_BandwidthValue);

        GridPane.setConstraints(lbl_Cutoff,0,2);
        root.getChildren().add(lbl_Cutoff);

        GridPane.setConstraints(cutoff,1,2);
        root.getChildren().add(cutoff);

        GridPane.setConstraints(lbl_CutoffValue,2,2);
        root.getChildren().add(lbl_CutoffValue);

        GridPane.setConstraints(lbl_SampleRate,0,3);
        root.getChildren().add(lbl_SampleRate);

        GridPane.setConstraints(sampleRate,1,3);
        root.getChildren().add(sampleRate);

        GridPane.setConstraints(lbl_SampleRateValue,2,3);
        root.getChildren().add(lbl_SampleRateValue);


        bandWidth.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                lbl_BandwidthValue.setText(String.format("%.2f", new_val));
            }
        });
        cutoff.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                lbl_CutoffValue.setText(String.format("%.2f", new_val));
            }
        });
        sampleRate.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                lbl_SampleRateValue.setText(String.format("%.2f", new_val));
            }
        });
    }
    public double getBandwidth(){ return bandWidth.getValue();}
    public double getCutoff(){ return cutoff.getValue();}
    public double getSampleRate(){ return sampleRate.getValue();}
}
