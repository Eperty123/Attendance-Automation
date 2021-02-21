package GUI.CONTROLLER;

import BE.INTERFACE.ISessionManager;
import BE.Teacher;
import GUI.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TeacherLoginController {
    @FXML
    public PasswordField passwordField;

    @FXML
    public TextField usernameField;

    protected Main main = Main.getInstance();
    protected ISessionManager sessionManager = main.getSessionManager();

    /**
     * Handle the event for canceling log in.
     */
    public void handleCancelLogin() {
        try {
            main.changeStage("/GUI/FXML/AttendanceOverview.fxml", "Attendance OVerview");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle the event for logging in.
     */
    public void handleLogin() {
        try {

            var foundTeacher = checkLogin();
            if (foundTeacher != null) {
                sessionManager.setLoggedInTeacher(foundTeacher);
                main.changeStage("/GUI/FXML/AttendanceOverview.fxml", String.format("Attendance Overview (Teacher: %s)", foundTeacher.getFullName()));
            } else {
                sessionManager.setLoggedInTeacher(null);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check the username and password for the teacher.
     *
     * @return
     */
    private Teacher checkLogin() {
        var teachers = sessionManager.getTeachers();
        for (int i = 0; i < teachers.size(); i++) {
            var teacher = teachers.get(i);
            boolean gotCredentials = teacher.getLogin().getUsername().equals(usernameField.getText()) && teacher.getLogin().getPassword().equals(passwordField.getText());
            if (gotCredentials) return teacher;
        }
        return null;
    }
}