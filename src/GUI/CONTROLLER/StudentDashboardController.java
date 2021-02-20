package GUI.CONTROLLER;

import BE.INTERFACE.ISessionManager;
import BE.Student;
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

    private Main main = Main.getInstance();
    /**
     * The ISessionManager for handling session data.
     */
    protected ISessionManager sessionManager = Main.getInstance().getSessionManager();

    /**
     * The AbsenceOverViewController.
     */
    protected AbsenceOverviewController absenceOverviewController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sessionManager = Main.getInstance().getSessionManager();
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
            main.changeStage("FXML/AttendanceOverview.fxml", "Attendance Overview");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The event handler for showing attendance stats.
     */
    public void showAttendanceStats() {
        try {
            absenceOverviewController = main.changeStageInNewWindow("FXML/AbsenceOverview.fxml", "My Attendance Stats");
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
