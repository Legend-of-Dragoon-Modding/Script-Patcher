package avi.lod.tlodscripttools;

import avi.lod.tlodscripttools.Patching.ChestContentChange;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectChange implements Initializable {

    @FXML
    ListView changeListView;

    @FXML
    TextField changeLocationField;

    @FXML
    Button addChangeButton;
    private Stage stage;
    private CreatePatch createPatchController;

    public void setEventListeners(){
        javafx.beans.value.ChangeListener<String> changeLocationListener = (observableValue, oldValue, newValue) -> {
            if(!newValue.equals("") && changeListView.getSelectionModel().getSelectedIndex() != -1){
                addChangeButton.setDisable(false);
            }else{
                addChangeButton.setDisable(true);
            }
        };
        javafx.beans.value.ChangeListener<String> changeTypeListener = (observableValue, oldValue, newValue) -> {
            if(newValue != null && !changeLocationField.getText().equals("")){
                addChangeButton.setDisable(false);
            }else{
                addChangeButton.setDisable(true);
            }
        };
        changeListView.getSelectionModel().selectedItemProperty().addListener(changeTypeListener);
        changeLocationField.textProperty().addListener(changeLocationListener);
        stage.setOnCloseRequest(windowEvent -> {
            changeListView.getSelectionModel().selectedItemProperty().removeListener(changeTypeListener);
            changeLocationField.textProperty().removeListener(changeLocationListener);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> items = FXCollections.observableArrayList("Chest Content Change","Difference Change");
        changeListView.setItems(items);
    }
    public void setStage(Stage stage, CreatePatch c) {
        this.stage = stage;
        this.createPatchController = c;

    }
    public void addChange(){
        switch (changeListView.getSelectionModel().getSelectedItem().toString()){
            case "Chest Content Change":
                createPatchController.chosenPatch.changes.add(new ChestContentChange("SECT/DRGN21.BIN/36/9",2));
                createPatchController.setChangeListItems();
                stage.close();
                break;
            case "Difference Change":
                break;
        }
    }
}
