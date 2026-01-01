package com.example.abs_version_2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {

    public static Task<String> clock = null;

    public Clock(){
    }

    public  static Task<String> stratClock(){
        if(clock == null){
            clock = new Task<String>() {
                @Override
                protected String call() throws Exception {

                    Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                        updateMessage(time);
                    }), new KeyFrame(Duration.seconds(1)));

                    clock.setCycleCount(Animation.INDEFINITE);
                    clock.play();

                    return null;
                }
            };
        }
        return clock;
    }






}
