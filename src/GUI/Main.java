package GUI;

import GUI.CONTROLLER.AttendanceOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private AttendanceOverviewController viewController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AttendanceOverview.fxml"));
        Parent root = loader.load();
        viewController = loader.getController();
        viewController.createStudents();
        //primaryStage.setTitle("Hello World");
        primaryStage.setTitle("Attendance Overview");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
