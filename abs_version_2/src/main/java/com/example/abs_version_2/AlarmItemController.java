package com.example.abs_version_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class AlarmItemController {
    @FXML private Label timeLabel;
    @FXML private Label nameLabel;
    @FXML private ToggleButton toggleButton;
    @FXML private Button deleteButton;
    @FXML private Label bellFile;
//    @FXML private HBox alarmItemContainer;

    private Alarm alarm;
    private AlarmController alarmController;

    public void setAlarm(Alarm alarm, AlarmController alarmController) {
        this.alarm = alarm;
        this.alarmController = alarmController;
        updateUI();
    }

    private void updateUI() {
        timeLabel.setText(alarm.getFormattedTime());
        nameLabel.setText(alarm.getName());
        bellFile.setText(alarm.getSound());

        // Set toggle state
        toggleButton.setSelected(alarm.isActive());
        toggleButton.setText(alarm.isActive() ? "ON" : "OFF");

        // Update toggle button style based on state
        updateToggleStyle();
    }


    @FXML
    private void toggleAlarm() {
        boolean isActive = toggleButton.isSelected();
        alarm.setActive(isActive);
        toggleButton.setText(isActive ? "ON" : "OFF");
        updateToggleStyle();

        // Update in database
        alarmController.updateAlarmStatus(alarm.getId(), isActive);
    }

    private void updateToggleStyle() {
        if (toggleButton.isSelected()) {
            toggleButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        } else {
            toggleButton.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white;");
        }
    }

    @FXML
    private void deleteAlarm() {
        // Delete from database
        alarmController.deleteAlarmFromDatabase(alarm.getId());
    }
}