package Controller;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by User on 22.01.2018.
 */
public class SaveOpenDialog {

    public String Savedialog(Stage stage, String title){ //

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Speicherort");
       File file = fileChooser.showSaveDialog(stage);
     System.out.println(file.getAbsolutePath());

        return file.getAbsolutePath();
    }


    public String DirectionDialog(Stage stage, String title){
         DirectoryChooser directoryChooser = new DirectoryChooser();
        File file =
                directoryChooser.showDialog(stage);
       return file.getAbsolutePath();
    }
}
