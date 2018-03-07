package Controller;

import Model.Effects.*;
import Model.Pad;
import View.EffectView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by Pascal on 30.01.2018.
 */
public class EffectController {

    private PadController padController;
    private EffectView view;
    private Pad clickedPad;

    public EffectController(PadController ref, EffectView view) {
        this.padController = ref;
        this.view = view;
    }

    public EventHandler<MouseEvent> submit = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            switch(view.getActiveView().getId())
            {
                case("Gain"):
                    GainEffect gainEffect = new GainEffect(view.getGainView().getGainValue());
                    clickedPad =padController.getPadAtIndex(padController.getClickedPadIndex());
                    clickedPad.deleteEffect();
                    clickedPad.addEffect(gainEffect);
                    break;
                case("Delay"):
                    DelayEffect delayEffect = new DelayEffect((float)view.getDelayView().getDelayValue(), (float)view.getDelayView().getDecayValue());
                    clickedPad = padController.getPadAtIndex(padController.getClickedPadIndex());
                    clickedPad.deleteEffect();
                    clickedPad.addEffect(delayEffect);
                    break;
                case("Flanger"):
                    FlangerEffect flanger = new FlangerEffect((float)view.getFlangerView().getDelayValue(),(float)view.getFlangerView().getFlangerRate(), (float)view.getFlangerView().getDepth(),
                            (float)view.getFlangerView().getFeedback(),(float)view.getFlangerView().getWetValue(),(float)view.getFlangerView().getDryValue());
                    clickedPad = padController.getPadAtIndex(padController.getClickedPadIndex());
                    clickedPad.deleteEffect();
                    clickedPad.addEffect(flanger);
                    break;
                case("BitCrush"):
                    BitCrushEffect bitcrusher = new BitCrushEffect((float)view.getBitCrushView().getBitRes(),(float)view.getBitCrushView().getBitRate());
                    clickedPad = padController.getPadAtIndex(padController.getClickedPadIndex());
                    clickedPad.deleteEffect();
                    clickedPad.addEffect(bitcrusher);
                    break;
                case("NotchFilter"):
                    NotchFilterEffect notchfilter = new NotchFilterEffect((float)view.getNotchFilterView().getCutoff(),(float)view.getNotchFilterView().getBandwidth(),(float)view.getNotchFilterView().getSampleRate());
                    clickedPad = padController.getPadAtIndex(padController.getClickedPadIndex());
                    clickedPad.deleteEffect();
                    clickedPad.addEffect(notchfilter);
                    break;
            }
            view.close();
        }
    };
}
