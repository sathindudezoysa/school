package com.example.abs_version_2;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label clock_view;

    Task<String> clock = Clock.stratClock();

    private AlarmService alarmService;


    @FXML
    public void initialize(){
        wall_clock();
        alarmService = new AlarmService();
    }

    /**
     * Wall Clock Logic
     */
    public void wall_clock(){
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

    }


    @FXML
    protected void close() {
        System.exit(0);
    }



}
