package com.example.abs_version_2;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Label clock_view;

    @FXML private Label next_bell;

    Task<String> clock = Clock.stratClock();

    private AlarmService alarmService;


    @FXML
    public void initialize(){
        wallClock();
        alarmService = new AlarmService();
        alarmService.startChecking();
        next_bell.textProperty().bind(alarmService.nextBellProperty());
    }

    /**
     * Wall Clock Logic
     */
    private void wallClock(){
        clock_view.textProperty().bind(clock.messageProperty());
        clock.setOnFailed(e -> {
            clock_view.textProperty().unbind();
        });

        Thread clock_tread = new Thread(clock);
        clock_tread.setDaemon(true);
        clock_tread.start();
    }


    @FXML
    protected void change_time(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("change-times-view.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Alarm");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));

            dialogStage.setOnCloseRequest(e ->{
                alarmService.startChecking();
            });

            dialogStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void about() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("about.fxml"));
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setScene(new Scene(root));
        stage.show();


    }


    @FXML
    protected void close() {
        System.exit(0);
    }



}
