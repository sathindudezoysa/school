package com.example.abs_version_2;

import java.time.LocalTime;
import java.util.List;
import java.util.PriorityQueue;

public class AlarmQueue {


    private static AlarmQueue instance;

    private final PriorityQueue<Alarm> alarmQueue = new PriorityQueue<>();

    private DatabaseHelper databaseHelper;
    public AlarmQueue(){
        databaseHelper = DatabaseHelper.getInstance();
        loadAlarms();
    }

    public synchronized void loadAlarms(){
        List<Alarm> alarms = databaseHelper.getAllAlarms();
        if(alarmQueue.isEmpty()){
            alarmQueue.addAll(alarms);
        }else{
            alarmQueue.clear();
            alarmQueue.addAll(alarms);
        }
        removePassedAlarms();
    }

    private void removePassedAlarms() {
        LocalTime currentTime = LocalTime.now();

        while (!alarmQueue.isEmpty()) {
            Alarm earliest = alarmQueue.peek();
            if (earliest.getTime().isBefore(currentTime)) {
                alarmQueue.poll();  // safe, because no iteration
            } else {
                break;
            }
        }
    }

    public static synchronized AlarmQueue getInstance(){
        if (instance == null){
            instance = new AlarmQueue();
        }
        return instance;
    }

    public synchronized PriorityQueue<Alarm> getQueue(){
        return alarmQueue;
    }

    public synchronized void poll(){
        alarmQueue.poll();
    }

    public void deleteItemById(String id){
        alarmQueue.removeIf(i -> i.getId().equals(id));
    }

    public synchronized void addItem(Alarm e){
        alarmQueue.add(e);
        removePassedAlarms();
    }

    public synchronized boolean isEmpty(){
        if(alarmQueue.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public synchronized Alarm peek(){
        return alarmQueue.peek();
    }


}
