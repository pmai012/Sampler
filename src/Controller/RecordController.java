package Controller;


import javafx.scene.control.Button;

/**
 * Created by User on 21.12.2017.
 */
public class RecordController {
    private Button record, stop;
    private PadController padController;




    public RecordController(Button record, Button stop, PadController ref)
    {
        padController = ref;
        this.record = record;
        this.stop = stop;
    }
}
