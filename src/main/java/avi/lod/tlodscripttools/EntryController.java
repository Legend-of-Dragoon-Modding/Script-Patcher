package avi.lod.tlodscripttools;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EntryController {
    private final String iconPath = "/img/myconido-drew.png";
    public void openSettingsWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
            Parent root = loader.load();
            Stage settingsWindow = new Stage();
            Settings controller = (Settings) loader.getController();
            controller.setStage(settingsWindow);
            settingsWindow.getIcons().add(new Image(getClass().getResource(iconPath).toExternalForm()));
            settingsWindow.setTitle("Settings");
            settingsWindow.setScene(new Scene(root, 300, 200));
            settingsWindow.show();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void openCreatePatchWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("create-patch.fxml"));
            Parent root = loader.load();
            Stage createPatchWindow = new Stage();
            CreatePatch controller = (CreatePatch)loader.getController();
            controller.setStage(createPatchWindow);
            controller.setEventListeners();
            createPatchWindow.getIcons().add(new Image(getClass().getResource(iconPath).toExternalForm()));
            createPatchWindow.setTitle("Create Patch");
            createPatchWindow.setScene(new Scene(root, 1000,600));
            createPatchWindow.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void openApplyPatchWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("apply-patch.fxml"));
            Parent root = loader.load();
            Stage createPatchWindow = new Stage();
            createPatchWindow.getIcons().add(new Image(getClass().getResource(iconPath).toExternalForm()));
            createPatchWindow.setTitle("Apply Patch");
            createPatchWindow.setScene(new Scene(root, 300, 200));
            createPatchWindow.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void openCreditsWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("credits.fxml"));
            Parent root = loader.load();
            Stage createPatchWindow = new Stage();
            createPatchWindow.getIcons().add(new Image(getClass().getResource(iconPath).toExternalForm()));
            createPatchWindow.setTitle("Credits");
            createPatchWindow.setScene(new Scene(root, 300, 200));
            createPatchWindow.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}