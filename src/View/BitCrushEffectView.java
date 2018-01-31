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
public class BitCrushEffectView extends Pane {

    private GridPane root;
    private Slider bitRes;
    private Slider bitRate;

    final Label lbl_BitRes = new Label("Bit Resolution:");
    final Label lbl_BitRate= new Label("Bit Rate:");

    private Label lbl_BitResValue;
    private Label lbl_BitRateValue;

    //delay,rate,depth,feedback,wet,dry
    public BitCrushEffectView(double bitres, double bitrate){
        bitRes = new Slider(0,16, bitres);
        bitRate = new Slider(0,44100, bitrate);
        lbl_BitResValue = new Label (Double.toString(bitRes.getValue()));
        lbl_BitRateValue = new Label (Double.toString(bitRate.getValue()));
        init();
    }

    public BitCrushEffectView() {

        bitRes = new Slider(0,16,0);
        bitRate = new Slider(0,44100,44100);
        lbl_BitResValue = new Label (Double.toString(bitRes.getValue()));
        lbl_BitRateValue = new Label (Double.toString(bitRate.getValue()));
        init();
    }
    private void init(){
        setId("BitCrush");
        root = new GridPane();
        bitRes.setShowTickLabels(true);

        lbl_BitRes.setTextFill(Color.WHITESMOKE);
        lbl_BitRate.setTextFill(Color.WHITESMOKE);
        lbl_BitResValue.setTextFill(Color.WHITESMOKE);
        lbl_BitRateValue.setTextFill(Color.WHITESMOKE);

        root.setPadding(new Insets(10, 10, 10, 0));
        root.setVgap(50);
        root.setHgap(30);

        this.getChildren().add(root);
        GridPane.setConstraints(lbl_BitRes,0,1);
        root.getChildren().add(lbl_BitRes);

        GridPane.setConstraints(bitRes,1,1);
        root.getChildren().add(bitRes);

        GridPane.setConstraints(lbl_BitResValue,2,1);
        root.getChildren().add(lbl_BitResValue);

        GridPane.setConstraints(lbl_BitRate,0,2);
        root.getChildren().add(lbl_BitRate);

        GridPane.setConstraints(bitRate,1,2);
        root.getChildren().add(bitRate);

        GridPane.setConstraints(lbl_BitRateValue,2,2);
        root.getChildren().add(lbl_BitRateValue);

        bitRes.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                lbl_BitResValue.setText(String.format("%.2f", new_val));
            }
        });
        bitRate.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                lbl_BitRateValue.setText(String.format("%.2f", new_val));
            }
        });

    }
    public double getBitRes(){ return bitRes.getValue();}
    public double getBitRate(){ return bitRate.getValue();}

}
