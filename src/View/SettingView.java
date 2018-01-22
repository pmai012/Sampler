package View;

import Controller.SettingController;
import Model.Pad;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import com.sun.javafx.scene.control.skin.ContextMenuSkin;
import javafx.stage.Stage;

/**
 * Created by User on 21.12.2017.
 */
public class SettingView extends Pane {

    private MenuBar samplermenu;
    private Menu menuFile;
    private Menu menuEdit;
    private Menu menuView;
    private Menu menuHelp;
    private MenuItem itemOpen;
    private MenuItem itemSave;
    private MenuItem itemLocation;
    private Pad[] pads;

    private SettingController settingController;

    public SettingView(Stage stage, Pad[] pads){
        this.pads = pads;
        samplermenu = new MenuBar();
        menuFile = new Menu("Datei");
        menuEdit = new Menu("Bearbeiten");
        menuView = new Menu("Ansicht");
        menuHelp = new Menu("Hilfe");
        itemOpen = new MenuItem("Öffnen");
        itemSave = new MenuItem("Projekt speichern unter");
        itemLocation = new MenuItem(("Speicherordner festlegen"));

        menuFile.getItems().addAll(itemOpen, itemSave, itemLocation);
        samplermenu.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
        samplermenu.prefWidthProperty().bind(this.widthProperty());
        this.getChildren().add(samplermenu);



        samplermenu.getStyleClass().add("menuBar");
        menuFile.getStyleClass().addAll("context-menu");
        itemOpen.getStyleClass().add("menuItem");
        itemSave.getStyleClass().add("menuItem");
        itemLocation.getStyleClass().add("menuItem");

        settingController =new SettingController(stage,pads);
        itemLocation.setOnAction(settingController.openSaveLocation);
        itemSave.setOnAction(settingController.save);
        itemOpen.setOnAction(settingController.open);

    }
}
