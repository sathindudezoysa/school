package com.example.abs_version_2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AlarmService {

    private final PriorityQueue<Alarm> alarmQueue = new PriorityQueue<>();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


    public AlarmService(){
        Alarm alarm = new Alarm("1 st", LocalTime.parse("14:51"), "/home/sathindu/Documents/Projects/school/abs_version_2/src/main/resources/com/example/abs_version_2/sounds/BELL.WAV", true);
        Alarm alarm2 = new Alarm("1 st", LocalTime.parse("15:00"), "/home/sathindu/Documents/Projects/school/abs_version_2/src/main/resources/com/example/abs_version_2/sounds/BELL.WAV", true);

        alarmQueue.add(alarm);
        alarmQueue.add(alarm2);
        startChecking();
    }
    private void startChecking() {

        removePassedAlarms();

        scheduler.scheduleAtFixedRate(() -> {
            synchronized (alarmQueue) {
                if (alarmQueue.isEmpty()) return;

                Alarm nextAlarm = alarmQueue.peek();
                if (nextAlarm.getTime().toString().equals(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")))) {

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

    private void removePassedAlarms(){
        LocalTime currentTime  = LocalTime.now();
        for (Alarm i : alarmQueue){
            if(i.getTime().isBefore(currentTime)){
                alarmQueue.poll();
            }else{
                break;
            }
        }
    }

    public void shutdown() {
        scheduler.shutdownNow();
    }

}
