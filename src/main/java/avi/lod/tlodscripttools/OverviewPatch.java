package avi.lod.tlodscripttools;

import avi.lod.tlodscripttools.Patching.Patch;
import avi.lod.tlodscripttools.Patching.Patcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.concurrent.Executors;

public class OverviewPatch {

    private Patch chosenPatch;

    @FXML
    private MenuItem openPatchMenuItem;

    @FXML
    private MenuItem closePatchMenuItem;

    @FXML
    private AnchorPane overviewMenu;

    public void pickPatch(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a patch file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json File","*.json"));
        File selectedFile = fileChooser.showOpenDialog((overviewMenu.getScene().getWindow()));

        if(selectedFile != null){
            Patcher patcher = new Patcher();
            this.chosenPatch = patcher.loadPatch(selectedFile.getPath());
            openPatchMenuItem.setDisable(true);
            closePatchMenuItem.setDisable(false);
        }
    }
    public void closePatch(ActionEvent event){
        chosenPatch = null;
        openPatchMenuItem.setDisable(false);
        closePatchMenuItem.setDisable(true);
    }
    void displayPatchInfo(){

    }
}
