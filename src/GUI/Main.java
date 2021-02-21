package GUI;

import BE.INTERFACE.ISessionManager;
import BE.Utils.SessionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage activeStage;
    private Stage primaryStage;
    private static Main instance;

    /**
     * Grab a singleton instance to make sure it gets instantiated for child controllers.
     */
    private ISessionManager sessionManager = SessionManager.getInstance();

    /**
     * Get the singleton instance of Main.
     *
     * @return
     */
    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }

    private void initialize() {
        instance = this;
        sessionManager.setMainController(getInstance());
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/AttendanceOverview.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Attendance Overview");
        primaryStage.setScene(new Scene(root));

        // Assign the stages now that they've been initialized.
        this.primaryStage = primaryStage;
        activeStage = primaryStage;

        // And show the AttendanceOVerview.
        primaryStage.show();
        System.out.println(String.format("Initial scene: %s", primaryStage.getScene()));
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

            getActiveStage().setScene(scene);
            getActiveStage().setTitle(title);
            getActiveStage().centerOnScreen();
            //System.out.println(String.format("Changing stage to: %s", getActiveStage()));
        }
        return loader.getController();
    }

    /**
     * Change the current stage to the specified fxml.
     *
     * @param scene The scene to load.
     * @param fxml  The fxml path of the scene.
     * @param title The title for the scene.
     * @return Returns the stage's controller.
     * @throws Exception Any exceptions.
     */
    public Stage changeScene(Scene scene, String fxml, String title) throws IOException {
        if (scene != null) {
            Parent pane = FXMLLoader.load(getClass().getResource(fxml));
            getActiveStage().getScene().setRoot(pane);
            //getActiveStage().setScene(scene);
            getActiveStage().setTitle(title);
            getActiveStage().centerOnScreen();
            return getActiveStage();
        }
        return null;
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

        // Make the new stage wait for closure before being able to return to the main window.
        // Reference: https://www.codota.com/code/java/methods/javafx.stage.Stage/showAndWait.
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        return loader.getController();
    }

    /**
     * Get the session manager.
     *
     * @return The instanced session manager.
     */
    public ISessionManager getSessionManager() {
        return sessionManager;
    }

}
