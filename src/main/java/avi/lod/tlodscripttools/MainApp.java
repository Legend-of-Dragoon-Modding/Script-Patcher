package avi.lod.tlodscripttools;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Preferences.loadPrefs();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("view-main.fxml"));
        stage.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add("css/gen.css");
        stage.getIcons().add(new Image(getClass().getResource("/img/myconido-drew.png").toExternalForm()));
        stage.setTitle("Script Patcher");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }


}
class MyAppLauncher {public static void main(String[] args) {Application.launch(MainApp.class);}}