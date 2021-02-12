package GUI.CONTROLLER;

import BE.Student;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceOverviewController implements Initializable {
    @FXML
    private FlowPane flowPane;
    private List<Student> studentList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        flowPane.getChildren().clear();
        studentList = new ArrayList<>(Arrays.asList(new Student("Dennis"), new Student("Ted")));
        studentList.forEach(student -> {
            BorderPane bp = new BorderPane();
            bp.setPadding(new Insets(5, 5, 5, 5));
            bp.setCenter(student.makePersonPane());
            flowPane.getChildren().add(bp);
        });
        studentList.forEach(student -> student.nameProperty().addListener((Observable, t1, t2) -> student.setName(t2)));
        Student.width.addListener((Observable, t1, t2) ->
                studentList.forEach(Student::updatePersonPane));
        Student.height.addListener((Observable, t1, t2) ->
                studentList.forEach(Student::updatePersonPane));
        Slider b = new Slider();
        b.valueChangingProperty().addListener(v -> {Student.height.set(250*b.getValue());
        Student.width.set(200*b.getValue());
        studentList.forEach(Student::updatePersonPane);
        });
        flowPane.getChildren().add(b);
    }
}
