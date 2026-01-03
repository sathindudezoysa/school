package com.example.abs_version_2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AddAlarmController {
    @FXML private TextField alarmNameField;
    @FXML private ChoiceBox<String> hourChoiceBox;
    @FXML private ChoiceBox<String> minuteChoiceBox;
    @FXML private ChoiceBox<String> ampmChoiceBox;
    @FXML private Label preText;
    @FXML private Label fileLabel;


    @FXML private Button cancelButton;
    @FXML private Button saveButton;
    @FXML private Button testButton;

    private AlarmController alarmController;


    @FXML
    public void initialize() {
        // Setup hour choices
        for (int i = 1; i <= 12; i++) {
            hourChoiceBox.getItems().add(String.valueOf(i));
        }
        hourChoiceBox.setValue("8");

        // Setup minute choices
        for (int i = 0; i < 60; i++) {
            minuteChoiceBox.getItems().add(String.format("%02d", i));
        }
        minuteChoiceBox.setValue("30");

        // Setup AM/PM
        ampmChoiceBox.getItems().addAll("AM", "PM");
        ampmChoiceBox.setValue("AM");


    }

    public void setAlarmController(AlarmController controller) {
        this.alarmController = controller;
    }

    @FXML
    private void saveAlarm() {
        try {
            // Get values from form
            String name = alarmNameField.getText().trim();
            if (name.isEmpty()) {
                name = "Bell";
            }

            String bell_file = fileLabel.getText();
            if(bell_file.isEmpty()){
                new ErrorDialog().showErrorDialog("Error", "Please select the sound file");
                return;
            }


            int hour = Integer.parseInt(hourChoiceBox.getValue());
            int minute = Integer.parseInt(minuteChoiceBox.getValue());
            String ampm = ampmChoiceBox.getValue();

            if (ampm.equals("PM") && hour != 12) {
                hour += 12;
            } else if (ampm.equals("AM") && hour == 12) {
                hour = 0;
            }

            LocalTime time = LocalTime.of(hour, minute);

            List<Alarm> alarmList = alarmController.getAllAlarms();
            for (Alarm a : alarmList){
                if(a.getTime().equals(time)){
                    new ErrorDialog().showErrorDialog("Error", "Bell already Exists");
                    return;
                }
            }


            Alarm alarm = new Alarm(name, time, bell_file, true);

            alarmController.addAlarmToDatabase(alarm);

            System.out.println("Successfull");

            closeWindow();

        } catch (Exception e) {
            e.printStackTrace();
            new ErrorDialog().showErrorDialog("Error", "Can not Save the Bell");
//            alarmController.showAlert("Error", "Failed to add alarm: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    public void onSelectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a File");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files (*.mp3, *.wav)", "*.mp3", "*.wav")
        );
        Stage stage = (Stage) fileLabel.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            preText.setText("Selected File: ");
            fileLabel.setText(file.getAbsolutePath());
            testButton.setDisable(false);
        } else {
            preText.setText("No file selected");
        }
    }

    @FXML
    private void testSound(){
        Play play = new Play(fileLabel.getText());
        if (testButton.getText().equalsIgnoreCase("test")) {
            testButton.setText("stop");
            play.start();
        }else{
            play.stopPlaying();
            testButton.setText("test");
        }

    }


    @FXML
    private void cancelAlarm() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}