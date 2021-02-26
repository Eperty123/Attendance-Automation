package GUI.CONTROLLER;

import BE.INTERFACE.ISessionManager;
import BE.UTILITY.GUIHelper;
import BE.UTILITY.PieChartUtility;
import BE.UTILITY.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class AbsenceOverviewController implements Initializable {

    @FXML
    public FlowPane absenceFlowPane;

    protected ISessionManager sessionManager = SessionManager.getInstance();

    public static final Font FONT = new Font("System Bold", 24);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateChart();
    }

    /**
     * Update the chart.
     */
    public void updateChart() {

        absenceFlowPane.getChildren().clear();

        var absencePieChart = PieChartUtility.getStudentPieChart(sessionManager.getSelectedStudent(), String.format("%s's Total Attendance", sessionManager.getSelectedStudent().getFullName()));
        var absencePieChartPane = GUIHelper.createPieChartBorderPane(absencePieChart, sessionManager.getSelectedStudent(), FONT);
        absenceFlowPane.getChildren().add(absencePieChartPane);
    }
}
