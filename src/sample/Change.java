package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;

public class Change {

    @FXML public javafx.scene.control.TextField t1;
    @FXML public javafx.scene.control.TextField t2;
    @FXML public javafx.scene.control.TextField t3;
    @FXML public javafx.scene.control.TextField t4;
    @FXML public javafx.scene.control.TextField t5;
    @FXML public javafx.scene.control.TextField t6;
    @FXML public javafx.scene.control.TextField t7;
    @FXML public javafx.scene.control.TextField t8;
    @FXML public javafx.scene.control.TextField t9;
    @FXML public javafx.scene.control.TextField t10;





       public void initialize() {
           add();
    }
    Controller controller = new Controller();
    public void add(){

        t1.setText(controller.tim.get(0));
        t2.setText(controller.tim.get(1));
        t3.setText(controller.tim.get(2));
        t4.setText(controller.tim.get(3));
        t5.setText(controller.interval);
        t6.setText(controller.tim.get(4));
        t7.setText(controller.tim.get(5));
        t8.setText(controller.tim.get(6));
        t9.setText(controller.tim.get(7));
        t10.setText(controller.gatha);
    }


    public void save(ActionEvent actionEvent) {
        controller.tim.add(0,t1.getText());
        controller.tim.add(1,t2.getText());
        controller.tim.add(2,t3.getText());
        controller.tim.add(3,t4.getText());
        controller.tim.add(4,t6.getText());
        controller.tim.add(5,t7.getText());
        controller.tim.add(6,t8.getText());
        controller.tim.add(7,t9.getText());

        controller.interval = t5.getText();
        controller.gatha = t10.getText();

    }

}
