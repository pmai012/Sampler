package View;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;

/**
 * Created by User on 21.12.2017.
 */
public class SettingView extends Pane {

    private MenuBar samplermenu;
    private Menu menuFile;
    private Menu menuEdit;
    private Menu menuView;
    private Menu menuHelp;


    public SettingView(){
        samplermenu = new MenuBar();
        menuFile = new Menu("File");
        menuEdit = new Menu("Edit");
        menuView = new Menu("View");
        menuHelp = new Menu("Help");
        samplermenu.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
        samplermenu.prefWidthProperty().bind(this.widthProperty());
        this.getChildren().add(samplermenu);
        samplermenu.getStyleClass().add("menuBar");
        menuFile.getStyleClass().addAll("menuTitle","label");

    }
}
