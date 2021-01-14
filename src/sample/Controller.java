package sample;

import com.sun.javafx.tk.Toolkit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;

import java.io.File;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;


public class Controller {
    @FXML private javafx.scene.control.Label Lclock;
    @FXML private TextArea log;


    public void initialize() {
        times();
        time1();
        checktime();
        //Play();
    }

    public static ArrayList<String> tim = new ArrayList<String>();

    public void time1() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            String time = currentTime.getHour() + ":" + currentTime.getMinute()+":"+ currentTime.getSecond();
            Lclock.setText(time);

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }
    LocalTime currentTime1 = LocalTime.now();
    public void checktime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime current = LocalTime.now();
            String time = current.getHour() + ":" + current.getMinute();
            for (String i: tim ){
                    if (time.equals(i)){
                        sound("BELL.WAV");
                        appendText(time);
                    }
                }

        }),
                new KeyFrame(Duration.minutes(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.setDelay(Duration.seconds(60 - currentTime1.getSecond()));
        clock.play();
    }
    public void Play (){
        Timeline play1 = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            String time = currentTime.getHour() + ":" + currentTime.getMinute()+":"+ currentTime.getSecond();
            System.out.println(time);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

        }),
                new KeyFrame(Duration.seconds(1))
        );
        play1.setCycleCount(Animation.INDEFINITE);
        play1.play();

    }

    public void sound(String name){
        Media sound = new Media(new File(name).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        Duration x = mediaPlayer.getTotalDuration();
        mediaPlayer.play();
    }

    public void times() {
        tim.add("8:30");
        tim.add("9:10");
        tim.add("9:50");
        tim.add("10:30");
        tim.add("10:50");
        tim.add("11:30");
        tim.add("12:10");
        tim.add("12:50");
        tim.add("13:29");

    }
    public void appendText(String time) {
        log.appendText("\n [ Bell ringed at ]  -->   "+time);
    }

    public void about(ActionEvent actionEvent) throws IOException {
        /*
        About about = new About();
        FXMLLoader loader = new FXMLLoader(getClass().<About>getResource("About.fxml"));
        loader.setController(about);
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.showAndWait();
        */



    }

    public void ctime(ActionEvent actionEvent) throws IOException {
        /*
        ChTime chTime = new ChTime();
        FXMLLoader loader = new FXMLLoader(getClass().<ChTime>getResource("ChTime.fxml"));
        loader.setController(chTime);
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.showAndWait();

         */
    }
}

