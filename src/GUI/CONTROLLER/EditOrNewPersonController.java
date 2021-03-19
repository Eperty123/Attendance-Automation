package GUI.CONTROLLER;

import BE.Student;
import BE.Utils.SessionManager;
import GUI.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    private Main main = SessionManager.getInstance().getMainController();

    public AttendanceOverviewController attendanceOverviewController = main.getSessionManager().getAttendanceOVerviewController();

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

    public boolean checkIdIsNumber(String id){
        try {
            Long.parseLong(id);
            return true;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Invalid id. Please use a valid number as your id.");
            alert.show();
            return false;
        }
    }

    /**
     * Checks that the input is valid, and if it is valid it makes the changes or creates the person
     * Shows a warning if something is invalid.
     */
    public void confirm() {
        if (!idField.getText().isEmpty() && !firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty()) {
            if(!checkIdIsNumber(idField.getText()))
                return;
            if (student == null) {
                attendanceOverviewController.getStudentList().add(new Student(Integer.parseInt(idField.getText()), firstNameField.getText(), lastNameField.getText(), pictureUrlField.getText()));
            } else {
                if (this.student.getId() != Long.parseLong(idField.getText()))
                    this.student.setId(Long.parseLong(idField.getText()));
                if (!this.student.getFirstName().equals(firstNameField.getText()))
                    this.student.setFirstName(firstNameField.getText());
                if(!this.student.getLastName().equals(lastNameField.getText()))
                    this.student.setLastName(lastNameField.getText());
                if(!this.student.getPicture().getUrl().equals(pictureUrlField.getText()))
                    this.student.setPicture(pictureUrlField.getText());
            }
            try {
                main.changeStage("FXML/AttendanceOverview.fxml", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("A field is empty. PLease fill it out.");
            alert.show();
        }
    }

    public void cancel() {
        try {
            main.changeStage("FXML/AttendanceOverview.fxml", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pickUrl() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./src/gui/pictures"));
        File file = fileChooser.showOpenDialog(main.getActiveStage());
        try {
            pictureUrlField.setText("file:/" + file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
