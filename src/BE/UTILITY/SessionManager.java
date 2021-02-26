package BE.UTILITY;

import BE.INTERFACE.ISessionManager;
import BE.Student;
import BE.Teacher;
import GUI.CONTROLLER.AttendanceOverviewController;
import GUI.Main;
import javafx.stage.Stage;

import java.util.List;

public class SessionManager implements ISessionManager {
    protected Session session;
    protected Main mainController;
    protected AttendanceOverviewController attendanceOverviewController;

    protected static SessionManager instance;

    private void initialize() {
        session = new Session();
    }

    public SessionManager() {
        initialize();
    }

    @Override
    /**
     * Get the assigned main controller instance.
     * @return The current main controller instance.
     */
    public Main getMainController() {
        return mainController;
    }

    @Override
    /**
     * Set the main controller instance.
     * @param mainController The main controller to use.
     */
    public void setMainController(Main mainController) {
        this.mainController = mainController;
    }

    @Override
    /**
     * Does a student exist in the configuration?
     * @return
     */
    public boolean hasStudents() {
        return session.hasStudents();
    }

    @Override
    /**
     * Get the Session instance.
     * @return
     */
    public Session getSession() {
        return session;
    }

    @Override
    /**
     * Get the current Session's students.
     * @return
     */
    public List<Student> getStudentList() {
        return session.getStudentList();
    }

    @Override
    /**
     * Set the Session's student list array.
     * @param studentList
     */
    public void setStudentList(List<Student> studentList) {
        session.setStudentList(studentList);
    }

    @Override
    /**
     * Get the selected student from the student overview.
     * @return The selected student.
     */
    public Student getSelectedStudent() {
        return session.getSelectedStudent();
    }

    @Override
    /**
     * Set the selected student.
     * @param student The selected student to use.
     */
    public void setSelectedStudent(Student student) {
        if (student != null) {
            session.setSelectedStudent(student);
        }
    }

    @Override
    public boolean hasTeachers() {
        return session.hasTeachers();
    }

    @Override
    public List<Teacher> getTeachers() {
        return session.getTeacherList();
    }

    @Override
    public void setTeachers(List<Teacher> teachers) {
        if (teachers != null)
            session.setTeacherList(teachers);
    }

    @Override
    public boolean isTeacherLoggedIn() {
        return getLoggedInTeacher() != null;
    }

    @Override
    public Teacher getLoggedInTeacher() {
        return session.getLoggedInTeacher();
    }

    @Override
    public void setLoggedInTeacher(Teacher teacher) {
        session.setLoggedInTeacher(teacher);
    }

    @Override
    public Stage getActiveStage() {
        return getMainController().getActiveStage();
    }

    /**
     * Get or create a singleton instance of InstanceManager.
     *
     * @return A singleton instance of InstanceManager.
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    @Override
    public ISessionManager getSessionManager() {
        return this;
    }

    @Override
    public void setSessionManager(ISessionManager sessionManager) {

    }

    /**
     * Get the instanced AttendanceOVerviewController.
     *
     * @return
     */
    @Override
    public AttendanceOverviewController getAttendanceOVerviewController() {
        return attendanceOverviewController;
    }


    @Override
    /**
     * Set the AttendanceOVerviewController.
     * @param attendanceOverviewController The AttendanceOVerviewController to use.
     */
    public void setAttendanceOverviewController(AttendanceOverviewController attendanceOverviewController) {
        this.attendanceOverviewController = attendanceOverviewController;
    }
}
