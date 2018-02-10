package Sound;

import java.io.File;

/**
 * Die besten Java Klassen sind die leeren java Klassen, denn da gibt es so zu 99 % keine Fehlermeldungen!
 * Diese Klasse wird als ortung genutzt. Von dieser Klasse aus wird ein Startpunkt für die Sounddateis gegeben.
 */
public class Soundadmin {

    public File[] getfiles(){

       return new File(this.getClass().getResource("").getPath()).listFiles();
    }
}
