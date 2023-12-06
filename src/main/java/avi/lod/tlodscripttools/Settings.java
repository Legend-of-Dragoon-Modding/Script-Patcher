package avi.lod.tlodscripttools;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class Settings implements Initializable {



    @FXML
    TextField scFilesLoc;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        scFilesLoc.setText(Preferences.prefs.get("sc_files_folder"));
    }
    public void saveChanges(){
        Preferences.prefs.put("sc_files_folder",scFilesLoc.getText());
        Preferences.savePrefs();
    }
    public void pickFilesFolder(ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Severed Chains Files Folder");
        File selectedFile = directoryChooser.showDialog(((Node) event.getSource()).getScene().getWindow());
        if(selectedFile == null){return;}
        String path = selectedFile.getPath();
        scFilesLoc.setText(path);
    }
}
