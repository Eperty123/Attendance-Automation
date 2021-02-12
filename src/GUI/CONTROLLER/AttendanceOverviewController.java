package GUI.CONTROLLER;

import BE.Student;
import BE.GUIHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceOverviewController {
    public static final int WIDTH = 150;
    public static final int HEIGHT = 250;
    public static final Font FONT = new Font("System Bold", 24);
    @FXML
    private FlowPane flowPane;
    private List<Student> studentList;

    public void createStudents() {
        var studentList = new ArrayList<Student>();
        int amount = 3;
        for (int i = 0; i < amount; i++) {
            var firstName = String.format("%s %d", "Test First Name", i);
            var lastName = String.format("%s %d", "Test Last Name", i);
            var student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setPicture("Data/Pictures/shawn mendes.png");
            var studentPane = GUIHelper.createStudentBorderPane(student, FONT, WIDTH, HEIGHT);
            studentListFlowPane.getChildren().add(studentPane);
        }
    }
}
