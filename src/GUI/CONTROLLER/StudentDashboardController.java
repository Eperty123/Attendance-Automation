package GUI.CONTROLLER;

import BE.Student;
import BLL.SessionManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentDashboardController implements Initializable {

    @FXML
    public Button myAttendanceStatsBtn;
    @FXML
    public Button backToAttendanceOverviewBtn;
    @FXML
    public Text welcomeLbl;

    protected SessionManager sessionManager;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDashboard();
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void updateDashboard() {
        if (sessionManager != null) {
            if (sessionManager.hasStudents()) {
                Platform.runLater(() -> welcomeLbl.setText(String.format("Welcome, %s", getSelectedStudent().getFirstName())));
                System.out.println(String.format("Selected student: %s (id: %d)", getSelectedStudent().getFullName(), getSelectedStudent().getId()));
            } else System.out.println("No students found!");
        }

    }

    public void backToAttendanceOverview() throws Exception {
        try {
            getSessionManager().getMainController().changeStage("FXML/AttendanceOverview.fxml", "Attendance Overview");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAttendanceStats() {
        try {
            var absenceController = (AbsenceOverviewController)getSessionManager().getMainController().changeStageInNewWindow("FXML/AbsenceOverview.fxml", "My Attendance Stats");
            absenceController.setSessionManager(getSessionManager());
            absenceController.update();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Student getSelectedStudent() {
        return getSessionManager().getSelectedStudent();
    }
}
