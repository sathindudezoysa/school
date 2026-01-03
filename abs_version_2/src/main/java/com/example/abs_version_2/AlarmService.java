package com.example.abs_version_2;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AlarmService {

    private AlarmQueue alarmQueue;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private StringProperty nextBell = new SimpleStringProperty(this, "nextBell", "");


    public AlarmService(){
        alarmQueue = AlarmQueue.getInstance();

    }
    public void startChecking() {

        scheduler.scheduleAtFixedRate(() -> {
            synchronized (alarmQueue) {
                if (alarmQueue.isEmpty()) {
                    Platform.runLater(()->{
                        this.nextBell.set("");
                    });
                    return;
                }
                Alarm nextAlarm = alarmQueue.peek();

                Platform.runLater(()->{
                    this.nextBell.set(nextAlarm.getTime().toString());
                });


                if (!nextAlarm.isActive()){
                    alarmQueue.poll();
                }else if (nextAlarm.getTime().toString().equals(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")))) {
                    System.out.println("Bell ");
                    playAlarm(nextAlarm);

                    alarmQueue.poll();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void playAlarm(Alarm alarm){
        Play play = new Play(alarm.getSound());
        play.start();
    }


    public StringProperty nextBellProperty(){
        return nextBell;
    }

    public void shutdown() {
        scheduler.shutdownNow();
    }

}
