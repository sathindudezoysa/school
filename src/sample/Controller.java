package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;


public class Controller {
    @FXML public javafx.scene.control.Label Lclock;


    public void initialize() {
        clock();

    }
    public static ArrayList<String> tim = new ArrayList<String>();

    public void clock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            String time = currentTime.getHour() + ":" + currentTime.getMinute()+":"+currentTime.getSecond();
            Lclock.setText(time);

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }


    public void check() {
        LocalTime currentTime1 = LocalTime.now();

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> run()),new KeyFrame(Duration.minutes(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.setDelay(Duration.seconds(60 - currentTime1.getSecond()));
        clock.play();

    }
    public void run()  {
        LocalTime currentTime = LocalTime.now();
        String time = currentTime.getHour() + ":" + currentTime.getMinute();
        for (String i : tim) {
            if (time.equals(i) ) {
                System.out.println("done");
                Play play = new Play("BELL.WAV");
                new Thread(play).start();
                break;
            }
        }
        if (time.equals(interval)){
            System.out.println("interval");
            Play play = new Play("damso.mp3");
            new Thread(play).start();

        }else if (time.equals(gatha)){
            System.out.println("gatha");
            Play play= new Play("final.wav");
            new Thread(play).start();
        }

    }

    public static String interval = "10:50";
    public static String gatha = "13:30";
    public void times() {
        tim.add("8:30");
        tim.add("9:10");
        tim.add("9:50");
        tim.add("10:30");
        tim.add("11:30");
        tim.add("12:10");
        tim.add("12:50");
        tim.add("13:29");

    }



    public void about(ActionEvent actionEvent) throws IOException {
        About about = new About();
        FXMLLoader loader = new FXMLLoader(getClass().<About>getResource("about.fxml"));
        loader.setController(about);
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.showAndWait();




    }

    public void ctime(ActionEvent actionEvent) throws IOException {
        Change tclass = new Change();
        FXMLLoader loader = new FXMLLoader(getClass().<Change>getResource("ChTime.fxml"));
        loader.setController(tclass);
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.showAndWait();


    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }
}

