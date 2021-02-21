package GUI.CONTROLLER;

import BE.INTERFACE.ISessionManager;
import BE.Student;
import BE.Utils.SessionManager;
import GUI.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StudentDashboardController implements Initializable {

    @FXML
    public Button myAttendanceStatsBtn;
    @FXML
    public Button backToAttendanceOverviewBtn;
    @FXML
    public Text welcomeLbl;
    @FXML
    public Text lastAttendanceLbl;

    /**
     * The ISessionManager for handling session data.
     */
    protected ISessionManager sessionManager = SessionManager.getInstance();

    private Main main = sessionManager.getMainController();

    /**
     * The AbsenceOverViewController.
     */
    protected AbsenceOverviewController absenceOverviewController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDashboard();
    }

    /**
     * Update the student dashboard ui.
     */
    public void updateDashboard() {
        if (sessionManager != null) {
            if (sessionManager.hasStudents()) {
                Platform.runLater(() -> welcomeLbl.setText(String.format("Welcome, %s", getSelectedStudent().getFirstName())));
                var lastAttendance = getSelectedStudent().getAttendanceUtil().getLastAttendance();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm:ss");
                String formatDateTime = lastAttendance.format(format);
                Platform.runLater(() -> lastAttendanceLbl.setText(String.format("Last Attendance: %s", formatDateTime)));
                System.out.println(String.format("Selected student: %s (id: %d)", getSelectedStudent().getFullName(), getSelectedStudent().getId()));
            } else System.out.println("No students found!");
        }

    }

    /**
     * The event handler for going back to the student overview.
     *
     * @throws Exception
     */
    public void backToAttendanceOverview() throws Exception {
        try {
            var properTitle = sessionManager.isTeacherLoggedIn() ? String.format("Attendance Overview (Teacher: %s)", sessionManager.getLoggedInTeacher().getFullName()) : "Attendance Overview";
            main.changeStage("FXML/AttendanceOverview.fxml", properTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The event handler for showing attendance stats.
     */
    public void showAttendanceStats() {
        try {
            var properTitle = sessionManager.isTeacherLoggedIn() ? String.format("%s's Attendance Stats", getSelectedStudent().getFullName()) : "My Attendance Stats";
            absenceOverviewController = main.changeStageInNewWindow("FXML/AbsenceOverview.fxml", properTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Get the selected student.
     *
     * @return
     */
    public Student getSelectedStudent() {
        return sessionManager.getSelectedStudent();
    }
}
