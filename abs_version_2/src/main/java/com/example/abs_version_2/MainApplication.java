package com.example.abs_version_2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Dharmasoka College");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}