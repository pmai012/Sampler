package Controller;

import Model.Effects.BitCrushEffect;
import Model.Effects.DelayEffect;
import Model.Effects.FlangerEffect;
import Model.Effects.NotchFilterEffect;
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
                case("Delay"):
                    DelayEffect delayEffect = new DelayEffect((float)view.returnDelayView().getDelayValue(), (float)view.returnDelayView().getDecayValue());
                    clickedPad = padController.getPadAtIndex(padController.getClickedPadIndex());
                    clickedPad.deleteEffect();
                    clickedPad.addEffect(delayEffect);
                    break;
                case("Flanger"):
                    FlangerEffect flanger = new FlangerEffect((float)view.returnFlangerView().getDelayValue(),(float)view.returnFlangerView().getFlangerRate(), (float)view.returnFlangerView().getDepth(),
                            (float)view.returnFlangerView().getFeedback(),(float)view.returnFlangerView().getWetValue(),(float)view.returnFlangerView().getDryValue());
                    clickedPad = padController.getPadAtIndex(padController.getClickedPadIndex());
                    clickedPad.deleteEffect();
                    clickedPad.addEffect(flanger);
                    break;
                case("BitCrush"):
                    BitCrushEffect bitcrusher = new BitCrushEffect((float)view.returnBitCrushView().getBitRes(),(float)view.returnBitCrushView().getBitRate());
                    clickedPad = padController.getPadAtIndex(padController.getClickedPadIndex());
                    clickedPad.deleteEffect();
                    clickedPad.addEffect(bitcrusher);
                    break;
                case("NotchFilter"):
                    NotchFilterEffect notchfilter = new NotchFilterEffect((float)view.returnNotchFilterView().getCutoff(),(float)view.returnNotchFilterView().getBandwidth(),(float)view.returnNotchFilterView().getSampleRate());
                    clickedPad = padController.getPadAtIndex(padController.getClickedPadIndex());
                    clickedPad.deleteEffect();
                    clickedPad.addEffect(notchfilter);
                    break;
            }
            view.close();
        }
    };
}
