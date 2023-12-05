package avi.lod.tlodscripttools;

import avi.lod.tlodscripttools.Patching.Patch;
import avi.lod.tlodscripttools.Patching.Patcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreatePatch implements Initializable{

    public Patch chosenPatch;

    @FXML
    private MenuItem openPatchMenuItem;

    @FXML
    private MenuItem closePatchMenuItem;
    @FXML
    private MenuItem editPatchMenuItem;

    @FXML
    private AnchorPane createPatchMenu;

    @FXML
    private Menu patchMenu;

    @FXML
    private ListView<String> changeListView;

    @FXML
    private AnchorPane sideBarPane;

    @FXML
    private Button removeChangeButton;

    private Stage stage;
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setEventListeners(){
        javafx.beans.value.ChangeListener<Number> selectedIndexListener = (observableValue, oldValue, newValue) ->{
            if (newValue.intValue() != -1) {
                System.out.println("Index clicked: " + newValue);
                removeChangeButton.setDisable(false);
            } else {
                System.out.println("No index selected");
                removeChangeButton.setDisable(true);
            }
        };
        changeListView.getSelectionModel().selectedIndexProperty().addListener(selectedIndexListener);
        stage.setOnCloseRequest(windowEvent -> changeListView.getSelectionModel().selectedIndexProperty().removeListener(selectedIndexListener));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    public void pickPatch(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a patch file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json File","*.json"));
        File selectedFile = fileChooser.showOpenDialog((createPatchMenu.getScene().getWindow()));
        if(selectedFile != null){
            Patcher patcher = new Patcher();
            this.chosenPatch = patcher.loadPatch(selectedFile.getPath());
            setChangeListItems();
            openPatchMenuItem.setDisable(true);
            editPatchMenuItem.setDisable(true);
            patchMenu.setDisable(false);
            sideBarPane.setVisible(true);
        }
    }
    public void setChangeListItems(){
        ObservableList<String> items = FXCollections.observableArrayList();
        for(int i = 0;i < this.chosenPatch.changes.size(); i++){
            items.add(this.chosenPatch.changes.get(i).getClass().getSimpleName());
        }
        changeListView.setItems(items);
    }
    public void createNewPatch(ActionEvent event){
        this.chosenPatch = new Patch();
        openPatchMenuItem.setDisable(true);
        editPatchMenuItem.setDisable(true);
        patchMenu.setDisable(false);

        sideBarPane.setVisible(true);
    }
    public void closePatch(ActionEvent event){
        chosenPatch = null;
        openPatchMenuItem.setDisable(false);
        editPatchMenuItem.setDisable(false);
        sideBarPane.setVisible(false);
        patchMenu.setDisable(true);
    }

    public void removeChange(ActionEvent event){
        int changeIndex = changeListView.getSelectionModel().getSelectedIndex();
        this.chosenPatch.changes.remove(changeIndex);
        setChangeListItems();
    }
    public void openSelectChange(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("select-change.fxml"));
            Parent root = loader.load();
            Stage createPatchWindow = new Stage();
            SelectChange controller = (SelectChange)loader.getController();
            controller.setStage(createPatchWindow, this);
            controller.setEventListeners();
            createPatchWindow.getIcons().add(new Image(getClass().getResource("/img/myconido-drew.png").toExternalForm()));
            createPatchWindow.setTitle("Select Change");
            createPatchWindow.setScene(new Scene(root, 300, 400));
            createPatchWindow.show();
        }catch (IOException err){
            err.printStackTrace();
        }
    }

    public void openPatchSettings(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("patch-settings.fxml"));
            Parent root = loader.load();
            Stage PatchSettingsWindow = new Stage();
            PatchSettings controller = (PatchSettings) loader.getController();
            controller.setStage(PatchSettingsWindow,this);
            PatchSettingsWindow.getIcons().add(new Image(getClass().getResource("/img/myconido-drew.png").toExternalForm()));
            PatchSettingsWindow.setTitle("Patch Settings");
            PatchSettingsWindow.setScene(new Scene(root, 440,280));
            PatchSettingsWindow.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
