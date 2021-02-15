package GUI.CONTROLLER;

import BE.GUIHelper;
import BE.Student;
import BLL.ConfigurationManager;
import GUI.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceOverviewController implements Initializable {
    public static final int WIDTH = 150;
    public static final int HEIGHT = 250;
    public static final Font FONT = new Font("System Bold", 24);
    public static final String DEFAULT_STYLE = "-fx-background-color: lightgrey; -fx-background-radius: 10px";
    public static final String SELECTED_STYLE = "-fx-background-radius: 15;-fx-background-color: lightblue;-fx-border-style: solid;-fx-border-color: grey;-fx-border-radius: 15;";

    protected ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @FXML
    public FlowPane studentListFlowPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurationManager = new ConfigurationManager();

        if (!configurationManager.hasStudents())
            configurationManager.setStudentList(createStudents(3));

        selectStudent();
        System.out.println(String.format("Students count: %d", configurationManager.getStudentList().size()));
    }

    public List<Student> createStudents(int amount) {
        var studentList = new ArrayList<Student>();

        for (int i = 0; i < amount; i++) {
            var firstName = String.format("%s %d", "Test First Name", i);
            var lastName = String.format("%s %d", "Test Last Name", i);
            var student = new Student();

            student.setId(i);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setPicture("Data/Pictures/shawn mendes.png");

            student.setStudentPane(addToStudentListFlowPane(student));
            studentList.add(student);
        }

        return studentList;
    }

    private BorderPane addToStudentListFlowPane(Student student) {
        if (student != null) {
            BorderPane pane = student.getStudentPane();
            if (student.getStudentPane() == null)
                pane = GUIHelper.createStudentBorderPane(student, FONT, WIDTH, HEIGHT, DEFAULT_STYLE);

            studentListFlowPane.getChildren().add(pane);
            return pane;
        }
        return null;
    }

    private void selectStudent() {
        studentListFlowPane.setOnMouseClicked(e -> {
            studentListFlowPane.getChildren().forEach(studentNode -> {
                        var selectedNode = e.getPickResult().getIntersectedNode();

                        if (selectedNode != null && studentNode.getAccessibleText().equals(selectedNode.getAccessibleText())
                                ^ studentNode.getAccessibleText().equals(selectedNode.getParent().getAccessibleText())) {
                            studentNode.setStyle(SELECTED_STYLE);

                            // On mouse left click.
                            if (e.getButton() == MouseButton.PRIMARY) {
                                try {
                                    // Assign the configuration's selected student to the selected one though the node.
                                    configurationManager.getStudentList().forEach((student) -> {

                                        // When the student id matches the accessible text (id), assign.
                                        if (Long.toString(student.getId()).equals(selectedNode.getAccessibleText())) {
                                            configurationManager.setSelectedStudent(student);
                                            //System.out.println(String.format("Assigned selected student: %s", student.getId()));
                                        }
                                    });

                                    if (configurationManager.getSelectedStudent() != null) {
                                        System.out.println(String.format("Selected student id: %s", configurationManager.getSelectedStudent().getId()));
                                        Main.getInstance().replaceStage("StudentDashboard.fxml", "Student Dashboard");
                                    }

                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                        } else studentNode.setStyle(DEFAULT_STYLE);
                    }
            );
        });
    }
}
