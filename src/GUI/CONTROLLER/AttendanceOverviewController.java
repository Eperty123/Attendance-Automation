package GUI.CONTROLLER;

import BE.GUIHelper;
import BE.INTERFACE.ISessionManager;
import BE.SessionManager;
import BE.Student;
import GUI.Main;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class AttendanceOverviewController implements Initializable {

    /**
     * The width of the student picture.
     */
    public static final int WIDTH = 150;

    /**
     * The height of the student picture.
     */
    public static final int HEIGHT = 250;

    /**
     * The font to use for the student name.
     */
    public static final Font FONT = new Font("System Bold", 24);

    /**
     * The default style for each student BorderPane.
     */
    public static final String DEFAULT_STYLE = "-fx-background-color: lightgrey; -fx-background-radius: 10px";

    /**
     * The select style for when clicking on a student BorderPane.
     */
    public static final String SELECTED_STYLE = "-fx-background-radius: 15;-fx-background-color: lightblue;-fx-border-style: solid;-fx-border-color: grey;-fx-border-radius: 15;";

    /**
     * Makes a new Random instance
     * (using seed 1337 for testing)
     */
    Random random = new Random(1337);
    /**
     * The ISessionManager for handling session data.
     */
    protected ISessionManager sessionManager;

    /**
     * initializes the student list
     */
    protected ObservableList<Student> studentList = FXCollections.observableArrayList(Arrays.asList(
            new Student("Shawn", "Mendes", "/GUI/Pictures/shawnmendes.png"),
            new Student("Justin", "Bieber", "/GUI/Pictures/justinbieber.png"),
            new Student("Adam", "Lavine", "/GUI/Pictures/adamlavine.png")
            )
    );

    @FXML
    public FlowPane studentListFlowPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeSessionManager();
        CRUDListeners();
    }

    /**
     * Listens after changes in the students and changes the view accordingly.
     */
    private void CRUDListeners() {
        //Loops through students
        studentList.forEach(s ->
                //adds change listener
                s.firstNameProperty().addListener((observable, t1, t2) -> {
                    //Runs this code when the observable value changes
                    int index = studentListFlowPane.getChildren().indexOf(s.getStudentPane());
                    studentListFlowPane.getChildren().remove(s.getStudentPane());
                    Text studentName = new Text(String.format("%s \n%s", t2, s.getLastName()));
                    studentName.setFont(FONT);
                    BorderPane.setAlignment(studentName, Pos.TOP_CENTER);
                    s.getStudentPane().setTop(studentName);
                    studentListFlowPane.getChildren().add(index, s.getStudentPane());
                }));

        //Loops through students
        studentList.forEach(s ->
                //adds change listener
                s.lastNameProperty().addListener((observable, t1, t2) -> {
                    //Runs this code when the observable value changes
                    int index = studentListFlowPane.getChildren().indexOf(s.getStudentPane());
                    studentListFlowPane.getChildren().remove(s.getStudentPane());
                    Text studentName = new Text(String.format("%s \n%s", s.getFirstName(), t2));
                    studentName.setFont(FONT);
                    BorderPane.setAlignment(studentName, Pos.TOP_CENTER);
                    s.getStudentPane().setTop(studentName);
                    studentListFlowPane.getChildren().add(index, s.getStudentPane());
                }));

        //Loops through students
        studentList.forEach(s ->
                //adds change listener
                s.pictureProperty().addListener((observable, t1, t2) -> {
                    //Runs this code when the observable value changes
                    int index = studentListFlowPane.getChildren().indexOf(s.getStudentPane());
                    studentListFlowPane.getChildren().remove(s.getStudentPane());
                    ImageView picture = new ImageView(s.getPicture());
                    picture.setPreserveRatio(true);
                    picture.setFitWidth(WIDTH);
                    picture.setFitHeight(HEIGHT);
                    BorderPane.setAlignment(picture, Pos.CENTER);
                    s.getStudentPane().setCenter(picture);
                    studentListFlowPane.getChildren().add(index, s.getStudentPane());
                }));


        //listens for changes in the studentlist and removes/adds the studentpanes accordingly
        studentList.addListener((ListChangeListener<? super Student>) listChangeListener -> {
            listChangeListener.next();
            listChangeListener.getRemoved().forEach(s ->
                    studentListFlowPane.getChildren().remove(s.getStudentPane()));
        });
        studentList.addListener((ListChangeListener<? super Student>) listChangeListener -> {
            listChangeListener.next();
            listChangeListener.getAddedSubList().forEach(s -> {
                studentListFlowPane.getChildren().add(s.getStudentPane());
            });
        });
    }

    /**
     * Initialize the ISessionManager.
     */
    private void initializeSessionManager() {
        if (sessionManager == null) {
            sessionManager = new SessionManager();
            sessionManager.setMainController(Main.getInstance());
        } else sessionManager = SessionManager.getInstance();

        if (!sessionManager.hasStudents())
            sessionManager.setStudentList(createStudents());

        selectStudent();
        System.out.println(String.format("Students count: %d", sessionManager.getStudentList().size()));
    }

    /**
     * Create students mock data.
     *
     * @return The created students.
     */
    public List<Student> createStudents() {
        studentList.forEach(s -> {
            s.setId(studentList.indexOf(s) + 1);
            for (int i = 0; i < 100; i++)
                s.attend(LocalDateTime.now().minusDays(random.nextInt(1000)));
            s.setStudentPane(addToStudentListFlowPane(s));
            s.setFirstName(s.getFirstName());
        });
        return studentList;
    }

    /**
     * Add a Student to the Student FlowPane.
     *
     * @param student The student to add.
     * @return The created BorderPane with the student in it.
     */
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

    /**
     * Initialize the event handler for when selecting a student on the student overview.
     * Must only be called once! Put it in the initialize method.
     */
    private void selectStudent() {
        studentListFlowPane.setOnMouseClicked(e -> {
            studentListFlowPane.getChildren().forEach(studentNode -> {
                        var selectedNode = e.getPickResult().getIntersectedNode();

                        if (selectedNode != null && studentNode.getAccessibleText() != null && studentNode.getAccessibleText().equals(selectedNode.getAccessibleText())
                                ^ studentNode.getAccessibleText().equals(selectedNode.getParent().getAccessibleText())) {
                            studentNode.setStyle(SELECTED_STYLE);

                            // On mouse left click.
                            if (e.getButton() == MouseButton.PRIMARY) {
                                try {
                                    // Assign the configuration's selected student to the selected one though the node.
                                    sessionManager.getStudentList().forEach((student) -> {

                                        // When the student id matches the accessible text (id), assign.
                                        if (Long.toString(student.getId()).equals(selectedNode.getAccessibleText())) {
                                            // Make the student attend on click.
                                            student.attend();
                                            sessionManager.setSelectedStudent(student);
                                            //System.out.println(String.format("Assigned selected student: %s", student.getId()));
                                        }
                                    });

                                    if (sessionManager.getSelectedStudent() != null) {
                                        System.out.println(String.format("Selected student id: %s", sessionManager.getSelectedStudent().getId()));
                                        var dashboardController = (StudentDashboardController) Main.getInstance().changeStage("FXML/StudentDashboard.fxml", "Student Dashboard");
                                        dashboardController.setSessionManager(sessionManager);
                                        dashboardController.updateDashboard();

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
