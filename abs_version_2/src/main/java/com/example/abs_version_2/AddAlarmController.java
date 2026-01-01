package com.example.abs_version_2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalTime;

public class AddAlarmController {
    @FXML private TextField alarmNameField;
    @FXML private ChoiceBox<String> hourChoiceBox;
    @FXML private ChoiceBox<String> minuteChoiceBox;
    @FXML private ChoiceBox<String> ampmChoiceBox;
//    @FXML private ChoiceBox<String> soundChoiceBox;

    @FXML private Button cancelButton;
    @FXML private Button saveButton;

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
                name = "Alarm";
            }

            int hour = Integer.parseInt(hourChoiceBox.getValue());
            int minute = Integer.parseInt(minuteChoiceBox.getValue());
            String ampm = ampmChoiceBox.getValue();
//            String sound = soundChoiceBox.getValue();

            // Convert to 24-hour format for LocalTime
            if (ampm.equals("PM") && hour != 12) {
                hour += 12;
            } else if (ampm.equals("AM") && hour == 12) {
                hour = 0;
            }

            LocalTime time = LocalTime.of(hour, minute);

            // Create alarm object (default to active)
            Alarm alarm = new Alarm(name, time, "test", true);

            // Add to database via main controller
            alarmController.addAlarmToDatabase(alarm);

            // Show success message
//            alarmController.showAlert("Success", "Alarm added successfully!");
            System.out.println("Successfull");

            // Close window
            closeWindow();

        } catch (Exception e) {
            e.printStackTrace();
//            alarmController.showAlert("Error", "Failed to add alarm: " + e.getMessage());
            System.out.println(e.getMessage());
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