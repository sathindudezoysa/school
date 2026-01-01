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

import java.util.List;

public class AlarmController {

    @FXML
    private VBox alarmListContainer;
    @FXML private Button addAlarmButton;

    private DatabaseHelper dbHelper;


    @FXML
    public void initialize() {
        dbHelper = DatabaseHelper.getInstance();
        loadAlarmsFromDatabase();
    }



    private void loadAlarmsFromDatabase() {
        alarmListContainer.getChildren().clear();
        List<Alarm> alarms = dbHelper.getAllAlarms();

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



    public void addAlarmToDatabase(Alarm alarm) {
        dbHelper.addAlarm(alarm);
        loadAlarmsFromDatabase();
    }

    public void deleteAlarmFromDatabase(String alarmId) {
        dbHelper.deleteAlarm(alarmId);
        loadAlarmsFromDatabase();
    }

    public void updateAlarmStatus(String alarmId, boolean isActive) {
        dbHelper.updateAlarmStatus(alarmId, isActive);
    }


//    public void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }

}
