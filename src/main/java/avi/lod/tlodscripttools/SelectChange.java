package avi.lod.tlodscripttools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectChange implements Initializable {

    @FXML
    ListView changeListView;


    @FXML
    Button addChangeButton;
    private Stage self;
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
        changeListView.setOnMouseClicked(event ->{
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() > 1){
                addChange();
            }
        });
        self.setOnCloseRequest(windowEvent -> {
            changeListView.getSelectionModel().selectedItemProperty().removeListener(changeTypeListener);
            changeListView.setOnMouseClicked(null);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> items = FXCollections.observableArrayList("Chest Content Change","Difference Change");
        changeListView.setItems(items);
    }
    public void setStage(Stage self, CreatePatch c) {
        this.self = self;
        this.createPatchController = c;

    }
    public void addChange(){
        switch (changeListView.getSelectionModel().getSelectedItem().toString()){
            case "Chest Content Change":
                openChestChangeWindow();
                break;
            case "Difference Change":
                openDiffChangeWindow();
                break;
        }
        self.close();
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
            chestChangeWindow.setScene(new Scene(root, 200, 300));
            chestChangeWindow.show();
        }catch (IOException err){
            err.printStackTrace();
        }
    }
    private void openDiffChangeWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("diff-change-controller.fxml"));
            Parent root = loader.load();
            Stage diffChangeWindow = new Stage();
            DiffChangeController controller = (DiffChangeController) loader.getController();
            controller.setStage(diffChangeWindow,createPatchController);
            diffChangeWindow.getIcons().add(new Image(getClass().getResource("/img/myconido-drew.png").toExternalForm()));
            diffChangeWindow.setTitle("Difference Change");
            diffChangeWindow.setScene(new Scene(root, 256, 210));
            diffChangeWindow.show();
        }catch(IOException err){
            err.printStackTrace();
        }
    }
}
