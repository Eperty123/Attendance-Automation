package GUI.CONTROLLER;

import BLL.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AbsenceOverviewController implements Initializable {

    @FXML
    public FlowPane absenceFlowPane;

    protected SessionManager sessionManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void update() {
        // TODO: Implement charts.
    }
}
