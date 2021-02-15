package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage activeStage;
    private static Main instance;

    public static Main getInstance() {
        if (instance == null)
            instance = new Main();
        return instance;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        activeStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/AttendanceOverview.fxml"));
        Parent root = loader.load();
        //viewController = loader.getController();

        primaryStage.setTitle("Attendance Overview");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    /**
     * Get the current active stage.
     *
     * @return
     */
    public Stage getActiveStage() {
        return activeStage;
    }


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Change the current stage to the specified fxml.
     *
     * @param fxml The fxml name of the replacement stage.
     * @return Returns the stage's controller.
     * @throws Exception Any exceptions.
     */
    public <T> T changeStage(String fxml, String title) throws Exception {
        var loader = new FXMLLoader(getClass().getResource(fxml));
        Parent page = loader.load();
        if (getActiveStage() != null) {
            Scene scene = getActiveStage().getScene();
            if (scene == null) {
                scene = new Scene(page);
                getActiveStage().setScene(scene);
            } else {
                getActiveStage().getScene().setRoot(page);
            }
            getActiveStage().setTitle(title);
            getActiveStage().centerOnScreen();
        }
        return loader.getController();
    }

    /**
     * Change and open the current stage to the specified fxml in a new window.
     *
     * @param fxml The fxml name of the replacement stage.
     * @return Returns the stage's controller.
     * @throws Exception Any exceptions.
     */
    public <T> T changeStageInNewWindow(String fxml, String title) throws Exception {
        var loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        var stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.showAndWait();
        return loader.getController();
    }
}
