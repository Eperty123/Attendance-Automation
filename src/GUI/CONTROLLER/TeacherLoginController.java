package GUI.CONTROLLER;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherLoginController {
    public void handleCancelLogin(ActionEvent event) throws IOException {
        Parent attendanceOverviewParent = FXMLLoader.load(getClass().getResource("/GUI/FXML/AttendanceOverview.fxml"));
        Scene attendanceOverviewScene = new Scene(attendanceOverviewParent);
        Stage teacherLogin = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //teacherLogin.hide();
        teacherLogin.setScene(attendanceOverviewScene);
        teacherLogin.show();
    }
}
