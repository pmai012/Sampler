package Model;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;

/**
 * Created by User on 21.12.2017.
 */
public class Pad {
private Mp3File file ;
private long startpoint;
private long endpoint;
    public void play() {
        
    }

    public void delete() {
    }


    public void loadSound(String path) {
        try {
            file = new Mp3File(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
    }

    public void setStartpoint(long time) {
       startpoint = time;
    }

    public void setEndpoint(long time) {
        endpoint = time;
    }
}
