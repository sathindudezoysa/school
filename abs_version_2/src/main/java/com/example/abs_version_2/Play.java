package com.example.abs_version_2;


import javafx.concurrent.Task;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.net.URL;

public class Play extends Thread{

    private AudioClip clip;
    public Play(String sound_file) {

        System.out.println(sound_file);
        try {

            File file = new File(sound_file);

            if (file == null) {
                throw new RuntimeException("File" + sound_file + " not found in resources.");
            }

            clip = new AudioClip(file.toURI().toString());

        }catch (Exception e){
            new ErrorDialog().showErrorDialog("Error", e.getMessage());
        }

        // Create the AudioClip
    }
    public void run(){
        if (clip != null){
            clip.setVolume(1.0);
            clip.play();
        }
    }

    public void stopPlaying(){
        if (clip != null){
            clip.stop();
        }
    }


}
