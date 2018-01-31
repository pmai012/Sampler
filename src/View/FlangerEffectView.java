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
 * Created by Pascal on 28.01.2018.
 */
public class FlangerEffectView extends Pane {

    private GridPane root;
    private Slider delayLevel;
    private Slider rate;
    private Slider depth;
    private Slider feedback;
    private Slider wet;
    private Slider dry;

    final Label delay = new Label("Delay Level:");
    final Label effectRate = new Label("Flanger Rate:");
    final Label effectDepth = new Label("Depth:");
    final Label effectFeedback = new Label("Feedback:");
    final Label wetRate = new Label("Wet Rate:");
    final Label dryRate = new Label("Dry Rate:");

    private Label delayRate;
    private Label rateValue;
    private Label depthValue;
    private Label feedbackValue;
    private Label wetValue;
    private Label dryValue;

    //delay,rate,depth,feedback,wet,dry

    public FlangerEffectView(double delayValue, double rateValue, double depthValue, double feedbackValue, double wetValue, double dryValue){
        this.delayLevel = new Slider(0,1,delayValue);
        this.rate = new Slider(0,1,rateValue);
        this.depth = new Slider(0,1,depthValue);
        this.feedback = new Slider(0,1,feedbackValue);
        this.wet = new Slider(0,1,wetValue);
        this.dry = new Slider(0,1,dryValue);
        this.delayRate = new Label (Double.toString(this.delayLevel.getValue()));
        this.rateValue = new Label (Double.toString(this.rate.getValue()));
        this.depthValue = new Label (Double.toString(this.depth.getValue()));
        this.feedbackValue = new Label (Double.toString(this.feedback.getValue()));
        this.wetValue = new Label (Double.toString(this.wet.getValue()));
        this.dryValue = new Label (Double.toString(this.dry.getValue()));
        init();
    }

    public FlangerEffectView() {
       delayLevel = new Slider(0,1,0);
       rate = new Slider(0,1,0);
       depth = new Slider(0,1,0);
       feedback = new Slider(0,1,0);
       wet = new Slider(0,1,0);
       dry = new Slider(0,1,0);
        delayRate = new Label (Double.toString(delayLevel.getValue()));
        rateValue = new Label (Double.toString(rate.getValue()));
        depthValue = new Label (Double.toString(depth.getValue()));
        feedbackValue = new Label (Double.toString(feedback.getValue()));
        wetValue = new Label (Double.toString(wet.getValue()));
        dryValue = new Label (Double.toString(dry.getValue()));
       init();
    }
    private void init(){
        setId("Flanger");
        root = new GridPane();
        rate.setShowTickLabels(true);
        rate.setShowTickMarks(true);
        rate.setMajorTickUnit(0.5);
        rate.setMinorTickCount(5);
        rate.setBlockIncrement(0.1);
        delayLevel.setShowTickLabels(true);
        delayLevel.setShowTickMarks(true);
        delayLevel.setMajorTickUnit(0.5);
        delayLevel.setMinorTickCount(5);
        delayLevel.setBlockIncrement(0.1);
        depth.setShowTickLabels(true);
        depth.setShowTickMarks(true);
        depth.setMajorTickUnit(0.5);
        depth.setMinorTickCount(5);
        depth.setBlockIncrement(0.1);
        feedback.setShowTickLabels(true);
        feedback.setShowTickMarks(true);
        feedback.setMajorTickUnit(0.5);
        feedback.setMinorTickCount(5);
        feedback.setBlockIncrement(0.1);
        wet.setShowTickLabels(true);
        wet.setShowTickMarks(true);
        wet.setMajorTickUnit(0.5);
        wet.setMinorTickCount(5);
        wet.setBlockIncrement(0.1);
        dry.setShowTickLabels(true);
        dry.setShowTickMarks(true);
        dry.setMajorTickUnit(0.5);
        dry.setMinorTickCount(5);
        dry.setBlockIncrement(0.1);

        effectRate.setTextFill(Color.WHITESMOKE);
        delayRate.setTextFill(Color.WHITESMOKE);
        delay.setTextFill(Color.WHITESMOKE);
        effectFeedback.setTextFill(Color.WHITESMOKE);
        feedbackValue.setTextFill(Color.WHITESMOKE);
        depthValue.setTextFill(Color.WHITESMOKE);
        rateValue.setTextFill(Color.WHITESMOKE);
        effectDepth.setTextFill(Color.WHITESMOKE);
        wetValue.setTextFill(Color.WHITESMOKE);
        wetRate.setTextFill(Color.WHITESMOKE);
        dryRate.setTextFill(Color.WHITESMOKE);
        dryValue.setTextFill(Color.WHITESMOKE);

        root.setPadding(new Insets(10, 10, 10, 0));
        root.setVgap(20);
        root.setHgap(40);

        this.getChildren().add(root);
        GridPane.setConstraints(delay,0,1);
        root.getChildren().add(delay);

        GridPane.setConstraints(delayLevel,1,1);
        root.getChildren().add(delayLevel);

        GridPane.setConstraints(delayRate,2,1);
        root.getChildren().add(delayRate);

        GridPane.setConstraints(effectRate,0,2);
        root.getChildren().add(effectRate);

        GridPane.setConstraints(rate,1,2);
        root.getChildren().add(rate);

        GridPane.setConstraints(rateValue,2,2);
        root.getChildren().add(rateValue);

        GridPane.setConstraints(effectFeedback,0,3);
        root.getChildren().add(effectFeedback);

        GridPane.setConstraints(feedback,1,3);
        root.getChildren().add(feedback);

        GridPane.setConstraints(feedbackValue,2,3);
        root.getChildren().add(feedbackValue);

        GridPane.setConstraints(effectDepth,0,4);
        root.getChildren().add(effectDepth);

        GridPane.setConstraints(depth,1,4);
        root.getChildren().add(depth);

        GridPane.setConstraints(depthValue,2,4);
        root.getChildren().add(depthValue);

        GridPane.setConstraints(wetRate,0,5);
        root.getChildren().add(wetRate);

        GridPane.setConstraints(wet,1,5);
        root.getChildren().add(wet);

        GridPane.setConstraints(wetValue,2,5);
        root.getChildren().add(wetValue);

        GridPane.setConstraints(dryRate,0,6);
        root.getChildren().add(dryRate);

        GridPane.setConstraints(dry,1,6);
        root.getChildren().add(dry);

        GridPane.setConstraints(dryValue,2,6);
        root.getChildren().add(dryValue);


        rate.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                rateValue.setText(String.format("%.2f", new_val));
            }
        });
        feedback.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                feedbackValue.setText(String.format("%.2f", new_val));
            }
        });
        depth.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                depthValue.setText(String.format("%.2f", new_val));
            }
        });
        delayLevel.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                delayRate.setText(String.format("%.2f", new_val));
            }
        });
        wet.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                wetValue.setText(String.format("%.2f", new_val));
            }
        });
        dry.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                dryValue.setText(String.format("%.2f", new_val));
            }
        });
    }
    public double getFlangerRate(){ return rate.getValue();}
    public double getFeedback(){ return feedback.getValue();}
    public double getDepth(){ return depth.getValue();}
    public double getWetValue(){ return wet.getValue();}
    public double getDryValue(){ return dry.getValue();}
    public double getDelayValue(){ return delayLevel.getValue();}
}
