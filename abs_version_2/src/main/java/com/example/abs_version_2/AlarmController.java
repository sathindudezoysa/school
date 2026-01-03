package com.example.abs_version_2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

public class AlarmController {

    @FXML
    private VBox alarmListContainer;
    @FXML private Button addAlarmButton;

    private DatabaseHelper dbHelper;

    private AlarmQueue alarmQueue;

    private List<Alarm> alarms;

    @FXML
    public void initialize() {
        dbHelper = DatabaseHelper.getInstance();
        alarmQueue = AlarmQueue.getInstance();
        loadAlarmsFromDatabase();
    }



    private void loadAlarmsFromDatabase() {
        alarmListContainer.getChildren().clear();
        alarms = dbHelper.getAllAlarms();
        Collections.sort(alarms);

        for (Alarm alarm : alarms) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("alarm-item-view.fxml"));
                HBox alarmItem = loader.load();
                AlarmItemController controller = loader.getController();
                controller.setAlarm(alarm, this);
                alarmListContainer.getChildren().add(alarmItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void openAddAlarmWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-item-view.fxml"));
            Parent root = loader.load();
            AddAlarmController controller = loader.getController();
            controller.setAlarmController(this);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Alarm");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Alarm> getAllAlarms(){
        return alarms;
    }

    public void addAlarmToDatabase(Alarm alarm) {
        dbHelper.addAlarm(alarm);
        alarmQueue.addItem(alarm);
        loadAlarmsFromDatabase();
    }

    public void deleteAlarmFromDatabase(String alarmId) {
        dbHelper.deleteAlarm(alarmId);
        alarmQueue.deleteItemById(alarmId);
        loadAlarmsFromDatabase();
    }

    public void updateAlarmStatus(String alarmId, boolean isActive) {
        alarmQueue.changeState(alarmId, isActive);

        dbHelper.updateAlarmStatus(alarmId, isActive);
    }

}
