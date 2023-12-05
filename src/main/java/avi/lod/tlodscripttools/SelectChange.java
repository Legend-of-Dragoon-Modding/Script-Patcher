package avi.lod.tlodscripttools;

import avi.lod.tlodscripttools.Patching.ChestContentChange;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectChange implements Initializable {

    @FXML
    ListView changeListView;


    @FXML
    Button addChangeButton;
    private Stage stage;
    private CreatePatch createPatchController;

    public void setEventListeners(){
        javafx.beans.value.ChangeListener<String> changeTypeListener = (observableValue, oldValue, newValue) -> {
            if(newValue != null){
                addChangeButton.setDisable(false);
            }else{
                addChangeButton.setDisable(true);
            }
        };
        changeListView.getSelectionModel().selectedItemProperty().addListener(changeTypeListener);
        stage.setOnCloseRequest(windowEvent -> {
            changeListView.getSelectionModel().selectedItemProperty().removeListener(changeTypeListener);
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
                openChestChangeWindow();
                break;
            case "Difference Change":
                break;
        }
        stage.close();
    }
    private void openChestChangeWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chest-content.fxml"));
            Parent root = loader.load();
            Stage chestChangeWindow = new Stage();
            ChestContent controller = (ChestContent)loader.getController();
            controller.setStage(chestChangeWindow, this.createPatchController);
            //controller.setEventListeners();
            chestChangeWindow.getIcons().add(new Image(getClass().getResource("/img/myconido-drew.png").toExternalForm()));
            chestChangeWindow.setTitle("Chest Content Change");
            chestChangeWindow.setScene(new Scene(root, 200, 280));
            chestChangeWindow.show();
        }catch (IOException err){
            err.printStackTrace();
        }
    }
}
