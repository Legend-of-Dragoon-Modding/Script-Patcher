package avi.lod.tlodscripttools;

import avi.lod.tlodscripttools.Patching.DiffChange;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class DiffChangeController {

    @FXML
    TextField ogScriptField;

    @FXML
    TextField updatedScriptField;

    @FXML
    TextField changeNameField;

    private CreatePatch createPatch;
    private Stage self;

    public void setStage(Stage self, CreatePatch createPatch){
        this.self = self;
        this.createPatch = createPatch;
    }
    public void pickUpdatedScriptPath(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setTitle("Pick updated script text file");
        File file =fileChooser.showOpenDialog(self);
        if(file != null){
            this.updatedScriptField.setText(file.getPath());
        }
    }

    public void saveChange(){
        if(ogScriptField.getText().isEmpty() || updatedScriptField.getText().isEmpty()) return;
        try{
            this.createPatch.chosenPatch.changes.add(new DiffChange(ogScriptField.getText(),changeNameField.getText(),updatedScriptField.getText()));
            this.createPatch.setChangeListItems();
        }catch (IOException err){
            err.printStackTrace();
        }
        self.close();
    }
}
