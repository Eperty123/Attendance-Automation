package GUI.CONTROLLER;

import BE.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherLoginController {
    public TextField usernameField;
    public AttendanceOverviewController attendanceOverviewController;

    public void setAttendanceOverviewController(AttendanceOverviewController attendanceOverviewController) {
        this.attendanceOverviewController = attendanceOverviewController;
    }

    public void handleCancelLogin(){
    attendanceOverviewController.getMain().getActiveStage().setScene(attendanceOverviewController.getOldScene());
    }

    public void handleLogin(){
    if(passwordField.getText().equals(Teacher.password)){
        attendanceOverviewController.setIsTeacher(true);
        attendanceOverviewController.getMain().getActiveStage().setScene(attendanceOverviewController.getOldScene());
    }
    else{
        attendanceOverviewController.setIsTeacher(false);
        attendanceOverviewController.getMain().getActiveStage().setScene(attendanceOverviewController.getOldScene());

    }
    }
