package com.example.abs_version_2;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Alarm implements Comparable<Alarm>{

    private String id;
    private String name;
    private LocalTime time;
    private String sound;
    private boolean isActive;

    public Alarm(String name, LocalTime time, String sound, boolean isActive) {
        this.id = java.util.UUID.randomUUID().toString();;
        this.name = name;
        this.time = time;
        this.sound = sound;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    @Override
    public int compareTo(Alarm other) {
        return this.time.compareTo(other.time);
    }
}
