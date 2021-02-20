package GUI.CONTROLLER;

import BE.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class EditOrNewPersonController {
    @FXML
    private TextField idField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField pictureUrlField;
    private Student student;

    public AttendanceOverviewController attendanceOverviewController;

    public void setAttendanceOverviewController(AttendanceOverviewController attendanceOverviewController) {
        this.attendanceOverviewController = attendanceOverviewController;
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public TextField getPictureUrlField() {
        return pictureUrlField;
    }

    public void setStudent(Student student) {
        this.student = student;
        getIdField().setText(String.valueOf(student.getId()));
        getFirstNameField().setText(student.getFirstName());
        getLastNameField().setText(student.getLastName());
        getPictureUrlField().setText(student.getPicture().getUrl());
    }

    public Student getStudent() {
        return this.student;
    }

    public void confirm() {
        if (!idField.getText().isEmpty() && !firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && !pictureUrlField.getText().isEmpty()) {
            if (student == null) {
                attendanceOverviewController.getPersonList().add(new Student(Integer.parseInt(idField.getText()), firstNameField.getText(), lastNameField.getText(), pictureUrlField.getText()));
            } else {
                if (this.student.getId() != Long.parseLong(idField.getText()))
                    this.student.setId(Long.parseLong(idField.getText()));
                if (!this.student.getFirstName().equals(firstNameField.getText()))
                    this.student.setFirstName(firstNameField.getText());
                if (!this.student.getLastName().equals(lastNameField.getText()))
                    this.student.setLastName(lastNameField.getText());
                if (!this.student.getPicture().getUrl().equals(pictureUrlField.getText()))
                    this.student.setPicture(pictureUrlField.getText());
            }
            attendanceOverviewController.getMain().getActiveStage().setScene(attendanceOverviewController.getOldScene());
        }
    }

    public void cancel() {
        attendanceOverviewController.getMain().getActiveStage().setScene(attendanceOverviewController.getOldScene());
    }

    public void pickUrl() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./src/gui/pictures"));
        File file = fileChooser.showOpenDialog(attendanceOverviewController.getMain().getActiveStage());
        try {
            pictureUrlField.setText("file:/" + file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
