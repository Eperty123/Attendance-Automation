package GUI;

import BE.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    @FXML
    private BorderPane parentBorderPane;
    private FlowPane flowPane = new FlowPane();
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student s = new Student("debuus");
        parentBorderPane.setCenter(flowPane);
        s.makePersonPane();
        flowPane.getChildren().add(s.getPersonPane());
        Button b = new Button();
        b.setOnAction(v->{Student.setFitSize((int)Math.min(parentBorderPane.getCenter().getScene().getWidth()*((double)5/6),parentBorderPane.getCenter().getScene().getHeight()*((double) 5/6)));
        s.setName("d");
        s.makePersonPane();
        });
        Student.getFitSize().addListener(v->s.makePersonPane());

        flowPane.getChildren().add(b);
    }
}
