package GUI.CONTROLLER;

import BE.Utils.GUIHelper;
import BE.Utils.PieChartUtils;
import BE.INTERFACE.ISessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class AbsenceOverviewController implements Initializable {

    @FXML
    public FlowPane absenceFlowPane;

    protected ISessionManager sessionManager;

    public static final Font FONT = new Font("System Bold", 24);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Get the ISessionManager instance.
     * @return
     */
    public ISessionManager getSessionManager() {
        return sessionManager;
    }

    /**
     * Set the ISessionManager.
     * @param sessionManager
     */
    public void setSessionManager(ISessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Update the chart.
     */
    public void updateChart() {

        var chartInfo = PieChartUtils.getStudentPieChart(sessionManager.getSelectedStudent(), String.format("%s's Attendance", getSessionManager().getSelectedStudent().getFullName()));
        var chartPane = GUIHelper.createPieChartBorderPane(chartInfo,sessionManager.getSelectedStudent(), FONT);
        absenceFlowPane.getChildren().add(chartPane);
    }
}
