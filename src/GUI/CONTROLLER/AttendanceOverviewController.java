package GUI.CONTROLLER;

import BE.INTERFACE.ISessionManager;
import BE.Person;
import BE.Student;
import BE.Teacher;
import BE.UTILITY.*;
import GUI.Main;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

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
    @FXML
    private TabPane tabPane;

    /**
     * Makes a new Random instance
     * (using seed 1337 for testing)
     */
    Random random = new Random(1337);

    /**
     * The session manager responsible for everything. Must get from the main singleton.
     */
    protected ISessionManager sessionManager = SessionManager.getInstance();

    /**
     * The main controller. It must be the singleton instance or shit goes down!
     */
    private Main main = sessionManager.getMainController();

    /**
     * The current logged in teacher.
     */
    protected Teacher currentTeacher;

    @FXML
    private ContextMenu contextMenuPerson = new ContextMenu();

    /**
     * The person list.
     */
    protected ObservableList<Person> personList = FXCollections.observableArrayList(Arrays.asList());

    /**
     * The teacher list.
     */
    protected ObservableList<Teacher> teacherList = FXCollections.observableArrayList(Arrays.asList(
            new Teacher(69, "Charlie", "Puth", "/GUI/Pictures/charlieputh.png", "charlie", "puth"))
    );

    /**
     * The student list.
     */
    protected ObservableList<Student> studentList = FXCollections.observableArrayList(Arrays.asList(
            new Student(0, "Shawn", "Mendes", "/GUI/Pictures/shawnmendes.png"),
            new Student(1, "Justin", "Bieber", "/GUI/Pictures/justinbieber.png"),
            new Student(2, "Adam", "Levine", "/GUI/Pictures/adamlevine.png")));

    @FXML
    public FlowPane studentListFlowPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        applySessionSettings();
    }

    /**
     * Listens after changes in the students and changes the view accordingly.
     */
    private void CRUDListeners() {
        //Loops through students
        personList.forEach(person ->
                //adds change listener
                person.firstNameProperty().addListener((observable, t1, t2) -> {
                    //Runs this code when the observable value changes
                    int index = studentListFlowPane.getChildren().indexOf(person.getPersonPane());
                    studentListFlowPane.getChildren().remove(person.getPersonPane());
                    Text studentName = new Text(String.format("%s \n%s", t2, person.getLastName()));
                    studentName.setFont(FONT);
                    BorderPane.setAlignment(studentName, Pos.TOP_CENTER);
                    person.getPersonPane().setTop(studentName);
                    studentListFlowPane.getChildren().add(Math.max(index, 0), person.getPersonPane());
                }));

        //Loops through students
        personList.forEach(person ->
                //adds change listener
                person.lastNameProperty().addListener((observable, t1, t2) -> {
                    //Runs this code when the observable value changes
                    int index = studentListFlowPane.getChildren().indexOf(person.getPersonPane());
                    studentListFlowPane.getChildren().remove(person.getPersonPane());
                    Text studentName = new Text(String.format("%s \n%s", person.getFirstName(), t2));
                    studentName.setFont(FONT);
                    BorderPane.setAlignment(studentName, Pos.TOP_CENTER);
                    person.getPersonPane().setTop(studentName);
                    studentListFlowPane.getChildren().add(Math.max(index, 0), person.getPersonPane());
                }));

        //Loops through students
        personList.forEach(person ->
                //adds change listener
                person.pictureProperty().addListener((observable, t1, t2) -> {
                    //Runs this code when the observable value changes
                    int index = studentListFlowPane.getChildren().indexOf(person.getPersonPane());
                    studentListFlowPane.getChildren().remove(person.getPersonPane());
                    ImageView picture = new ImageView(person.getPicture());
                    picture.setPreserveRatio(true);
                    picture.setFitWidth(WIDTH);
                    picture.setFitHeight(HEIGHT);
                    BorderPane.setAlignment(picture, Pos.CENTER);
                    person.getPersonPane().setCenter(picture);
                    studentListFlowPane.getChildren().add(Math.max(index, 0), person.getPersonPane());
                }));
        //Loops through students
        personList.forEach(person -> person.idProperty().addListener((observable, t1, t2) -> {
            int index = studentListFlowPane.getChildren().indexOf(person.getPersonPane());
            studentListFlowPane.getChildren().remove(person.getPersonPane());
            person.getPersonPane().setAccessibleText(String.format("%d", t2));
            person.getPersonPane().getChildren().forEach(c -> c.setAccessibleText(String.format("%d", t2)));
            studentListFlowPane.getChildren().add(Math.max(index, 0), person.getPersonPane());
        }));

        //listens for changes in the studentlist and removes/adds the studentpanes accordingly
        personList.addListener((ListChangeListener<? super Person>) listChangeListener -> {
            listChangeListener.next();
            listChangeListener.getRemoved().forEach(person ->
                    studentListFlowPane.getChildren().remove(person.getPersonPane()));
        });
        personList.addListener((ListChangeListener<? super Person>) listChangeListener -> {
            listChangeListener.next();
            listChangeListener.getAddedSubList().forEach(person -> {
                studentListFlowPane.getChildren().add(person.getPersonPane());
            });
        });
    }

    /**
     * Initialize the context menu for handling persons.
     */
    private void initContextMenuPerson() {
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItemBit("Attend", v -> attendDay()).getMenuItem()
                , new SeparatorMenuItem()
                , new MenuItemBit("New Person", v -> addStudent()).getMenuItem()
                , new MenuItemBit("Edit Person", v -> editPerson()).getMenuItem()
                , new MenuItemBit("Delete Person", v -> deletePerson()).getMenuItem()
                , new SeparatorMenuItem()
                , new MenuItemBit("Show student's absence", v -> {
                    showChart(PieChartUtility.getStudentAbsencePieChart(sessionManager.getSelectedStudent()), sessionManager.getSelectedStudent().getFullName());
                }).getMenuItem()
                , new MenuItemBit("Show individual absence chart (Daily basis)", v -> {
                    var tmp = getTopFiveMostAbsent();
                    showChart(BarChartUtility.getTotalIndividualAbsenceBarChart(tmp), "Daily basis");
                }).getMenuItem()
                , new MenuItemBit("Show individual absence chart (Person Basis)", v -> {
                    var tmp = getTopFiveMostAbsent();
                    showChart(BarChartUtility.getTotalIndividualAttendanceBarChartDaily(tmp), "Person basis");
                }).getMenuItem());

        menuItems.forEach(e -> contextMenuPerson.getItems().add(e));
    }

    /**
     * Gets a list of the 5 students that have most absence
     *
     * @return a list of students
     */
    private List<Student> getTopFiveMostAbsent() {
        List<Student> tmp = new ArrayList<Student>();
        List<Student> tmpStudents = new ArrayList<>(studentList);
        tmpStudents.sort(Comparator.comparingInt(Student::getTotalAbsence));
        for (int i = 0; i < Math.min(5, tmpStudents.size()); i++)
            tmp.add(tmpStudents.get(i));
        return tmp;
    }

    /**
     * Opens a window with the node
     *
     * @param chart the chart if that's what you want to show
     */
    private void showChart(Node chart, String tabName) {
        Tab tab = new Tab(tabName);
        tab.contentProperty().set(new BorderPane(chart));
        tabPane.getTabs().add(tab);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    /**
     * Delete the person.
     */
    private void deletePerson() {
        Student s = sessionManager.getSelectedStudent();
        if (s != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(String.format("You are deleting %s, please confirm this action.", s.getFullName()));
            alert.showAndWait();
            if(alert.getResult().equals(ButtonType.OK)){
                personList.remove(s);
                }
        }

    }

    /**
     * Edit the person.
     */
    private void editPerson() {
        try {
            main.changeStage("/GUI/FXML/EditOrNewPerson.fxml", "Edit Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Register the selected student as present.
     */
    private void attendDay() {
        Student s = sessionManager.getSelectedStudent();
        if (s != null) {
            s.getAttendanceUtil().attend();
            GUIHelper.changeSignifierColor(s, "Green");
        }
    }

    /**
     * Show the add student form.
     */
    private void addStudent() {
        try {
            main.changeStage("/GUI/FXML/EditOrNewPerson.fxml", "Add Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the person list.
     *
     * @return
     */
    public ObservableList<Person> getPersonList() {
        return personList;
    }

    /**
     * Apply and load cached settings (variables) from the session manager.
     */
    private void applySessionSettings() {
        // Clear the StudentPane for a clean start.
        clearStudentPane();

        // If no teachers are present in the session manager, add the mock data.
        if (!sessionManager.hasTeachers())
            sessionManager.setTeachers(teacherList);
        else {
            if (sessionManager.isTeacherLoggedIn()) {
                // Get the session manager's current active teacher.
                setCurrentTeacher(sessionManager.getLoggedInTeacher());
            }
        }

        // If no students are present in the session manager, create and add them.
        if (!sessionManager.hasStudents())
            sessionManager.setStudentList(createStudents());
        else {
            studentList.clear();
            studentList.addAll(sessionManager.getStudentList());
        }

        // Otherwise use the ones from the session manager.

        // If a teacher is logged in, sort the students by who's the most absence percentage.
        if (sessionManager.isTeacherLoggedIn()) {
            studentList.sort(Comparator.comparing(o -> o.getAttendanceUtil().getAbsencePercentage(), Comparator.reverseOrder()));
        }

        // List the students.
        for (int i = 0; i < studentList.size(); i++) {
            var student = studentList.get(i);
            student.setPersonPane(addToStudentListFlowPane(student));
        }

        // If the session manager doesn't have an instance of AttendanceOverviewController,
        // assign it to this very instance.
        if (sessionManager.getAttendanceOVerviewController() == null)
            sessionManager.setAttendanceOverviewController(this);

        personList.addAll(studentList);
        personList.addAll(teacherList);
        selectStudent();
        //studentListFlowPane.getChildren().add(teacherList.get(0).getPersonPane());
        CRUDListeners();
        initContextMenuPerson();

        System.out.println(String.format("Students count: %d", sessionManager.getStudentList().size()));
    }

    /**
     * Empty the StudentFlowPane for existing students.
     */
    private void clearStudentPane() {
        // Clear the student FlowPane for a clean start.
        studentListFlowPane.getChildren().clear();
    }

    /**
     * Create students mock data.
     *
     * @return The created students.
     */
    public List<Student> createStudents() {
        studentList.forEach(s -> {
            for (int i = 0; i < 100; i++)
                s.getAttendanceUtil().attend(LocalDateTime.now().minusDays(random.nextInt(1000)));
        });
        return studentList;
    }

    /**
     * Add a Student to the Student FlowPane.
     *
     * @param person The person to add.
     * @return The created BorderPane with the student in it.
     */
    private BorderPane addToStudentListFlowPane(Person person) {
        if (person != null) {
            BorderPane pane = person.getPersonPane();
            if (person.getPersonPane() == null)
                GUIHelper.createPersonBorderPane(person);

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

                    // Assign the session manager's selected student to the selected one though the node.
                    sessionManager.getStudentList().forEach((student) -> {

                        // When the student id matches the accessible text (id), assign.
                        if (Long.toString(student.getId()).equals(selectedNode.getAccessibleText()) ||
                                Long.toString(student.getId()).equals(selectedNode.getParent().getAccessibleText())) {

                            // Attend student if not a teacher.
                            if (!sessionManager.isTeacherLoggedIn())
                                student.getAttendanceUtil().attend();

                            sessionManager.setSelectedStudent(student);
                            //System.out.println(String.format("Assigned selected student: %s", student.getId()));
                        }
                    });

                    // On mouse left click.
                    if (e.getButton() == MouseButton.PRIMARY) {
                        try {
                            if (sessionManager.getSelectedStudent() != null && e.getClickCount() > 1) {
                                // Assign the configuration's selected student to the selected one through the node.
                                sessionManager.getStudentList().forEach((student) -> {

                                    // When the student id matches the accessible text (id), assign.
                                    if (Long.toString(student.getId()).equals(selectedNode.getAccessibleText()) ||
                                            Long.toString(student.getId()).equals(selectedNode.getParent().getAccessibleText())) {
                                        attendDay();
                                        sessionManager.setSelectedStudent(student);
                                        //System.out.println(String.format("Assigned selected student: %s", student.getId()));
                                    }
                                });

                                if (sessionManager.getSelectedStudent() != null) {
                                    System.out.println(String.format("Selected student id: %s", sessionManager.getSelectedStudent().getId()));
                                    var dashboardController = (StudentDashboardController) sessionManager.getMainController().changeStage("FXML/StudentDashboard.fxml", "Student Dashboard");
                                    dashboardController.updateDashboard();
                                }

                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }

                    if (e.getButton() == MouseButton.SECONDARY && currentTeacher != null) {
                        contextMenuPerson.show(studentListFlowPane, e.getScreenX(), e.getScreenY());
                    }
                } else studentNode.setStyle(DEFAULT_STYLE);
            });
        });
    }

    /**
     * Handle the on click event on the Teacher Login.
     */
    public void handleTeacherLogin() {
        try {
            main.changeStage("/GUI/FXML/TeacherLogin.fxml", "Teacher Login");

            // Update the session manager's student list
            // to ensure everything's up to date on load.
            sessionManager.setStudentList(studentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the current logged in teacher.
     *
     * @return
     */
    public Teacher getCurrentTeacher() {
        return currentTeacher;
    }

    /**
     * Set the current logged in teacher.
     *
     * @param currentTeacher The teacher to assign as logged in.
     */
    public void setCurrentTeacher(Teacher currentTeacher) {
        this.currentTeacher = currentTeacher;
    }
}
