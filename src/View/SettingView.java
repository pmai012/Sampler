package View;

import Controller.RecordController;
import Controller.SettingController;
import Model.Pad;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Observer;


public class SettingView extends Pane {

    private MenuBar samplermenu;
    private Menu menuFile;
    private Menu menuEdit;
    private Menu menuView;
    private Menu menuHelp;
    private Menu itemNew;
    private MenuItem itemNewNew;
    private MenuItem itemNewPreset;
    private MenuItem itemOpen;
    private MenuItem itemSave;
    private MenuItem itemSourceLocation;
    private MenuItem itemManual;
    private Pad[] pads;
    private Observer refOb;

    private SettingController settingController;

    public SettingView(Stage stage, Pad[] pads, Observer observer, RecordController recordController){
        refOb = observer;
        this.pads = pads;
        samplermenu = new MenuBar();
        menuFile = new Menu("Datei");
        menuEdit = new Menu("Bearbeiten");
        menuView = new Menu("Ansicht");
        menuHelp = new Menu("Hilfe");
        itemNew = new Menu(" Neues Projekt");
        itemNewNew = new MenuItem("Neu...");
        itemNewPreset = new MenuItem("Default Vorlage");
        itemOpen = new MenuItem("Projekt Öffnen");
        itemSave = new MenuItem("Projekt speichern unter");
        itemSourceLocation = new MenuItem("Quellordner festlegen");
        itemManual = new MenuItem("Anleitung");

        itemNew.getItems().addAll(itemNewNew, itemNewPreset);
        menuFile.getItems().addAll(itemNew, itemOpen, itemSave,  itemSourceLocation);
        menuHelp.getItems().add(itemManual);
        samplermenu.getMenus().addAll(menuFile, menuHelp);
        samplermenu.prefWidthProperty().bind(this.widthProperty());
        this.getChildren().add(samplermenu);

        samplermenu.getStyleClass().add("menuBar");
        menuFile.getStyleClass().addAll("context-menu");
        itemNew.getStyleClass().addAll("context-menu", "menuItem");
        itemNewNew.getStyleClass().add("menuItem");
        itemNewPreset.getStyleClass().add("menuItem");
        itemOpen.getStyleClass().add("menuItem");
        itemSave.getStyleClass().add("menuItem");
        itemSourceLocation.getStyleClass().add("menuItem");
        itemManual.getStyleClass().add("menuItem");

        settingController = new SettingController(stage,pads, refOb,recordController);
        itemNewNew.setOnAction(settingController.cleanEvent);
        itemSave.setOnAction(settingController.save);
        itemOpen.setOnAction(settingController.openEvent);
        itemSourceLocation.setOnAction(settingController.openSourceLocation);
        itemManual.setOnAction(settingController.openManualView);
        settingController.addObserver(refOb);
    }

    public SettingController getSettingController(){
        return  settingController;
    }
}
