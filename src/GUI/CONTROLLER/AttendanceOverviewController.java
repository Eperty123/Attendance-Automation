package GUI.CONTROLLER;

import BE.Student;
import BLL.GUIHelper;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class AttendanceOverviewController {
    @FXML
    public FlowPane studentListFlowPane;

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
            var studentPane = GUIHelper.createStudentBorderPane(student);
            studentListFlowPane.getChildren().add(studentPane);
        }
    }
}
