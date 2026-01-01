package com.example.abs_version_2;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ErrorDialog {

    public void showErrorDialog(String title, String message) {
        // Platform.runLater ensures this runs on the UI thread,
        // preventing crashes if you create this object from a background task.
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR); // Sets the icon to a Red X
            alert.setTitle(title);
            alert.setHeaderText(null); // Optional: Removes the header for a cleaner look
            alert.setContentText(message);
            alert.showAndWait(); // Pauses interaction until user clicks OK
        });
    }
}
