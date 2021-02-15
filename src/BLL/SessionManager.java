package BLL;

import BE.Session;
import BE.Student;
import GUI.Main;

import java.util.List;

public class SessionManager {
    protected Session session;
    protected Main mainController;

    protected static SessionManager instance;

    private void initialize() {
        session = new Session();
    }

    public SessionManager() {
        initialize();
    }

    /**
     * Get the assigned main controller instance.
     * @return The current main controller instance.
     */
    public Main getMainController() {
        return mainController;
    }

    /**
     * Set the main controller instance.
     * @param mainController The main controller to use.
     */
    public void setMainController(Main mainController) {
        this.mainController = mainController;
    }

    /**
     * Does a student exist in the configuration?
     * @return
     */
    public boolean hasStudents() {
        return session.hasStudents();
    }

    /**
     * Get the Session instance.
     * @return
     */
    public Session getSession() {
        return session;
    }

    /**
     * Get the current Session's students.
     * @return
     */
    public List<Student> getStudentList() {
        return session.getStudentList();
    }

    /**
     * Set the Session's student list array.
     * @param studentList
     */
    public void setStudentList(List<Student> studentList) {
        session.setStudentList(studentList);
    }

    /**
     * Get the selected student from the student overview.
     * @return The selected student.
     */
    public Student getSelectedStudent() {
        return session.getSelectedStudent();
    }

    /**
     * Set the selected student.
     * @param student The selected student to use.
     */
    public void setSelectedStudent(Student student) {
        if (student != null) {
            session.setSelectedStudent(student);
        }
    }

    /**
     * Get or create a singleton instance of InstanceManager.
     * @return A singleton instance of InstanceManager.
     */
    public static SessionManager getInstance() {
        if (instance == null) instance = new SessionManager();
        return instance;
    }
}
