package avi.lod.tlodscripttools;

import avi.lod.tlodscripttools.Patching.Patch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PatchSettings{
    @FXML
    private TextField patchName;
    @FXML
    private TextField patchPrefix;
    @FXML
    private TextArea patchDescription;

    private CreatePatch createPatch;

    private Stage self;

    public void savePatchSettings(){
        createPatch.chosenPatch.patchName = this.patchName.getText();
        createPatch.chosenPatch.patchDescription = this.patchDescription.getText();
        createPatch.chosenPatch.patchPrefix = this.patchPrefix.getText();
        self.close();
    }
    public void setStage(Stage self,CreatePatch c){
        this.createPatch = c;
        this.self = self;
        loadPatchSettings();
    }
    public void loadPatchSettings(){
        this.patchName.setText(createPatch.chosenPatch.patchName);
        this.patchDescription.setText(createPatch.chosenPatch.patchDescription);
        this.patchPrefix.setText(createPatch.chosenPatch.patchPrefix);
    }
}
