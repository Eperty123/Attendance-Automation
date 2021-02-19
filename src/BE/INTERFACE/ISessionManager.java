package BE.INTERFACE;

import BE.Utils.Session;
import BE.Student;
import GUI.Main;

import java.util.List;

public interface ISessionManager {
    public ISessionManager getSessionManager();

    public void setSessionManager(ISessionManager sessionManager);

    /**
     * Get the assigned main controller instance.
     *
     * @return The current main controller instance.
     */
    public Main getMainController();

    /**
     * Set the main controller instance.
     *
     * @param mainController The main controller to use.
     */
    public void setMainController(Main mainController);

    /**
     * Does a student exist in the configuration?
     *
     * @return
     */
    public boolean hasStudents();

    /**
     * Get the Session instance.
     *
     * @return
     */
    public Session getSession();

    /**
     * Get the current Session's students.
     *
     * @return
     */
    public List<Student> getStudentList();

    /**
     * Set the Session's student list array.
     *
     * @param studentList
     */
    public void setStudentList(List<Student> studentList);

    /**
     * Get the selected student from the student overview.
     *
     * @return The selected student.
     */
    public Student getSelectedStudent();

    /**
     * Set the selected student.
     *
     * @param student The selected student to use.
     */
    public void setSelectedStudent(Student student);
}
