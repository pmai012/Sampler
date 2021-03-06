package Controller;

import Model.BeatsperMinute;
import Model.Pad;
import View.EffectView;
import View.PadView;
import ddf.minim.AudioOutput;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.File;
import java.util.List;
import java.util.Observer;
import java.util.Optional;


public class PadController {
    private final int ANZAHL = 16;
    private SimpleMinim minim = new SimpleMinim(true);
    AudioOutput globalOut;
    private Pad pad[] = new Pad[ANZAHL];
    private Button button[] = new Button[ANZAHL];
    private Observer observer;
    private PadView view;
    private EffectView effectView;
    private int padIndex;
    private BeatsperMinute beat;

    public PadController(Button[] pads, Observer observer, PadView view) {
        pad = new Pad[ANZAHL];
        this.view = view;
        globalOut = minim.getLineOut();
        this.observer = observer;
        beat = new BeatsperMinute(100, globalOut);

        for (int i = 0; i < ANZAHL; i++) {
            button[i] = pads[i];

        }
    }

    public Button[] getButton() {
        return button;
    }

    public void startbeat() {
        beat.play();
    }

    public void stopbeat() {
        beat.stop();
    }

    public void setbeat(int beatsperminute) {
        beat.setBpm(beatsperminute);
    }

    public int getbeat(){return beat.getBpm();}
    /**
     * Gibt Pad an der Position des übergebenen Index - 1 zurück.
     * - 1, weil der Index für die Padbeschriftung von 1 - 16 gewählt wurde.
     *
     * @param index
     * @return
     */
    public Pad getPadAtIndex(int index) {
        return pad[index - 1];
    }

    public Pad[] getPad() {
        return pad;
    }

    public void setPad(Pad[] pad) {
        this.pad = pad;
    }

    public EventHandler<ActionEvent> metronoman = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            startbeat();
        }

    };
    public EventHandler<ActionEvent> metronomaus = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            stopbeat();
        }

    };
    public EventHandler<MouseEvent> pressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            for (int i = 0; i < ANZAHL; i++) {
                if (event.getSource().equals(button[i]) && event.getButton().equals(MouseButton.PRIMARY)) {

                    startpressing(i);
                }
            }
        }
    };

    public String beattoString() {
        String beats = Integer.toString(beat.getBpm());
        return beats + " BPM";
    }

    public void setMetronom(int bpm) {
        beat.setBpm(bpm);

    }

    public void startpressing(int number) {
        if (pad[number] != null) {

            if (pad[number].isPlaying()) {
                pad[number].stop();
                pad[number].setThreadrun(true);
                pad[number].threadstarten();
                pad[number].playSound();
                return;
            }
            pad[number].setThreadrun(true);
            pad[number].threadstarten();
            pad[number].playSound();
        }
    }

    public void stopPlay(int number) {

        if (pad[number] != null) {
            if (!pad[number].isPlaying()) {
                return;
            } else {
                pad[number].stop();
            }
        }
    }

    public void padclick(int number) {
        if (pad[number] == null) {
            return;
        }
        pad[number].setThreadrun(false);
    }

    public EventHandler<MouseEvent> rightclick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            for (int i = 0; i < ANZAHL; i++) {
                if (event.getSource().equals(button[i])) {
                    //LINKSKLICK
                    if (event.getButton().equals(MouseButton.PRIMARY)) {
                        padclick(i);
                        System.out.println(i + " linksclick");
                        if (pad[i] != null) {

                            if (pad[i].getPresstime() > 500) {

                                pad[i].stop();
                            }
                        }
                        return;
                    } else                //RECHTSCLICK
                        if (event.getButton().equals(MouseButton.SECONDARY)) {
                            System.out.println(i + " rechtsclick");
                            padIndex = i + 1;
                            return;
                        }
                    return;
                }
            }
        }
    };
    public EventHandler<ActionEvent> contextMenu_addEffectClicked = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            effectView = new EffectView(view.getPadController());
        }
    };
    public EventHandler<ActionEvent> contextMenu_deleteEffectClicked = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            getPadAtIndex(getClickedPadIndex()).deleteEffect();
            getPadAtIndex(getClickedPadIndex()).stop();
        }
    };

    public EventHandler<ActionEvent> contextMenu_editEffectClicked = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            effectView = new EffectView(view.getPadController(), getPadAtIndex(getClickedPadIndex()).getEffect());
            getPadAtIndex(getClickedPadIndex()).stop();
        }
    };
    public EventHandler<ActionEvent> contextMenu_deleteSoundClicked = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            System.out.println(getClickedPadIndex());
            getPadAtIndex(getClickedPadIndex()).stop();
            deletePad(getClickedPadIndex() - 1);

            return;
        }
    };

    public EventHandler<ActionEvent> contextMenu_setstartpoint = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            setstarttime(getClickedPadIndex() - 1);
        }
    };


    public void deletePad(int number) {
        pad[number] = null;
        Pad dummy = new Pad(observer, "pad");
    }

    /**
     * Acceptdrag allows to drop something
     */
    public EventHandler<DragEvent> acceptdrag = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    };
    /**
     * getData get the Data. it has to be mp3 or wav. If you put more files it looks for the first wav or mp3
     */
    public EventHandler<DragEvent> getData = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {

            for (int i = 0; i < ANZAHL; i++) {
                if (event.getSource().equals(button[i])) {
                    Dragboard dragboard = event.getDragboard();

                    if (dragboard.hasFiles()) {

                        List<File> list = dragboard.getFiles();

                        for (int f = 0; f < list.size(); f++) {
                            String path = list.get(f).toPath().toString();

                            if (path.endsWith(".mp3") || path.endsWith(".wav")) {
                                if (pad[i] != null) {
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Bereits verwendet");
                                    alert.setHeaderText("Dieses Pad ist bereits belegt ");
                                    alert.setContentText("Wollen Sie dieses Pad überschreiben?");

                                    Optional<ButtonType> result = alert.showAndWait();
                                    if (result.get() != ButtonType.OK) {
                                        return;
                                    }
                                }
                                pad[i] = new Pad(path, observer, globalOut);
                                pad[i].sendupdate();

                                Keyinput keyinput = new Keyinput();
                                pad[i].setShortcut(keyinput.getdefaultkeyCode(i));

                                while (pad[i] != null && i < 16) {
                                    i++;
                                }
                                if (i > 15) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    };

    public AudioOutput getGlobalOut() {
        return this.globalOut;
    }

    public int getClickedPadIndex() {
        return this.padIndex;
    }


    public void setstarttime(int i) {

        Dialog<Long> dialog = new Dialog<>();
        dialog.setTitle("Geben Sie einen Startpunkt ein");
        dialog.setHeaderText("Geben Sie einen Startpunkt an");
        dialog.setResizable(false);
        Label label1 = new Label("Startpunkt: ");
        Label zeit = new Label(String.valueOf(pad[i].getStartpoint()/1000) + " Sekunden");
        long laenge = (pad[i].getLenght());

        Slider slider = new Slider(0, laenge / 1000, 1);
        slider.setValue((pad[i].getStartpoint() / 1000));
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.valueProperty().addListener(new ChangeListener<Number>() {


            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               zeit.setText(String.valueOf(newValue.intValue()) + " Sekunden");

            }
        });
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(slider, 2, 1);
        grid.add(zeit, 1, 2);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("setzen", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.setResultConverter(new Callback<ButtonType, Long>() {
            @Override
            public Long call(ButtonType b) {
                if (b == buttonTypeOk) {


                    return Long.valueOf((int) (slider.getValue()*1000));
                }

                return new Long(pad[i].getStartpoint() / 1000);
            }
        });
        Optional<Long> result = dialog.showAndWait();
        pad[i].setStartpoint(result.get().intValue());

    }
}