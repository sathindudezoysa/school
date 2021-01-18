package sample;


import javafx.concurrent.Task;
import javafx.scene.media.AudioClip;

import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;

public class Play extends Task {
    String name = "";

    public Play(String s) {
        name = s;
    }
    @Override
    protected Object call() throws Exception {
        AudioClip audio = new AudioClip(getClass().getResource(name).toExternalForm());
        audio.setVolume(100);
        audio.play();
        return null;
    }
}
