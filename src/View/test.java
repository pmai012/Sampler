package View;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import ddf.minim.AudioOutput;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import ddf.minim.spi.AudioOut;
import ddf.minim.spi.AudioRecordingStream;
import ddf.minim.ugens.Delay;
import ddf.minim.ugens.FilePlayer;
import ddf.minim.ugens.Oscil;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by User on 08.01.2018.
 */
public class test {

    public static void main(String[] args)  {


        Minim minim;
        FilePlayer filePlayer;
        AudioOutput out;

// you can use your own file by putting it in the data directory of this sketch
// and changing the value assigned to fileName here.
        String fileName = "C:\\Users\\User\\Music\\Eminem\\03 - Die Alone [feat. Kobe] [Explicit].mp3";


            // setup the size of the app


            // create our Minim object for loading audio
         //   minim = new Minim();

            // a FilePlayer reads from an AudioRecordingStream, which we
            // can easily get from Minim using loadFileStream
        // filePlayer = new FilePlayer( minim.loadFileStream(fileName) );


    }
    }




