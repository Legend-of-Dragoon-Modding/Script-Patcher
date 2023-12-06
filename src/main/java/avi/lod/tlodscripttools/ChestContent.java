package avi.lod.tlodscripttools;

import avi.lod.tlodscripttools.Patching.ChestContentChange;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChestContent {

    @FXML
    TextField chestScriptLocation;

    @FXML
    TextField inventoryFullField;

    @FXML
    TextField itemIdField;

    @FXML
    TextField changeNameField;

    CreatePatch createPatch;
    Stage self;

    public void setStage(Stage self, CreatePatch createPatch){
        this.createPatch = createPatch;
        this.self = self;
    }
    public void saveChange(){
        this.createPatch.chosenPatch.changes.add(new ChestContentChange(chestScriptLocation.getText(),Integer.valueOf(itemIdField.getText()),inventoryFullField.getText(), changeNameField.getText()));
        this.createPatch.setChangeListItems();
        self.close();
    }
}
