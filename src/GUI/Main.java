package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AttendanceOverview.fxml"));
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
     * Replace the current stage with the specified fxml path.
     *
     * @param fxml The fxml name of the replacement stage.
     * @return Returns the replaced stage.
     * @throws Exception Any exceptions.
     */
    public Parent replaceStage(String fxml) throws Exception {
        Parent page = FXMLLoader.load(getClass().getResource(fxml), null, new JavaFXBuilderFactory());
        if (getActiveStage() != null) {
            Scene scene = getActiveStage().getScene();
            if (scene == null) {
                scene = new Scene(page);
                getActiveStage().setScene(scene);
            } else {
                getActiveStage().getScene().setRoot(page);
            }
            getActiveStage().sizeToScene();
        }
        return page;
    }
}
