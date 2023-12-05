package avi.lod.tlodscripttools;

import avi.lod.tlodscripttools.Patching.Patch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PatchSettings implements Initializable{
    @FXML
    private TextField patchName;
    @FXML
    private TextField patchPrefix;
    @FXML
    private TextArea patchDescription;

    public void savePatchSettings(){
        CreatePatch.chosenPatch.patchName = this.patchName.getText();
        CreatePatch.chosenPatch.patchDescription = this.patchDescription.getText();
        CreatePatch.chosenPatch.patchPrefix = this.patchPrefix.getText();
    }
    public void initialize(URL location, ResourceBundle resources){
        loadPatchSettings();
    }
    public void loadPatchSettings(){
        this.patchName.setText(CreatePatch.chosenPatch.patchName);
        this.patchDescription.setText(CreatePatch.chosenPatch.patchDescription);
        this.patchPrefix.setText(CreatePatch.chosenPatch.patchPrefix);
    }
}
