package Controller;

import ddf.minim.AudioOutput;

import java.awt.*;

/**
 * Created by User on 21.12.2017.
 */
public class RecordController {
    private Button record, stop;

    public RecordController(Button record, Button stop)
    {
        this.record = record;
        this.stop = stop;
    }
}
