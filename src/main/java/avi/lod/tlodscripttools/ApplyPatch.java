package avi.lod.tlodscripttools;

import avi.lod.tlodscripttools.Patching.Patch;
import avi.lod.tlodscripttools.Patching.Patcher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;

public class ApplyPatch {

    @FXML
    Label statusLabel;

    @FXML
    Button applyPatchButton;

    public void pickPatch(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a patch file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json File","*.json"));
        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if(selectedFile != null){
            applyPatchButton.setDisable(true);
            statusLabel.setText("Applying Patch");
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    applyPatch(selectedFile);
                }
            });
        }
    }
    private void applyPatch(File p){
        Patcher patcher = new Patcher();
        Patch patch = patcher.loadPatch(p.getPath());
        try{
            patcher.applyPatch(patch);
        }catch (IOException e){
            e.printStackTrace();
        }
        Platform.runLater(()->{
            statusLabel.setText("Patching ended");
            applyPatchButton.setDisable(false);
        });
    }
}
