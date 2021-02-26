package BE.INTERFACE;

import BE.Student;
import BE.Teacher;
import BE.UTILITY.Session;
import GUI.CONTROLLER.AttendanceOverviewController;
import GUI.Main;
import javafx.stage.Stage;

import java.util.List;

public interface ISessionManager {

    ISessionManager getSessionManager();

    void setSessionManager(ISessionManager sessionManager);

    /**
     * Get the assigned main controller instance.
     *
     * @return The current main controller instance.
     */
    Main getMainController();

    /**
     * Set the main controller instance.
     *
     * @param mainController The main controller to use.
     */
    void setMainController(Main mainController);

    /**
     * Get the assigned AttendanceOverviewController.
     *
     * @return
     */
    AttendanceOverviewController getAttendanceOVerviewController();

    /**
     * Assign the AttendanceOverviewController.
     *
     * @param attendanceOverviewController The AttendanceOVerviewController to use.
     */
    void setAttendanceOverviewController(AttendanceOverviewController attendanceOverviewController);

    /**
     * Does a student exist?
     *
     * @return
     */
    boolean hasStudents();

    /**
     * Get the Session instance.
     *
     * @return
     */
    Session getSession();

    /**
     * Get the current Session's students.
     *
     * @return
     */
    List<Student> getStudentList();

    /**
     * Set the Session's student list array.
     *
     * @param studentList
     */
    void setStudentList(List<Student> studentList);

    /**
     * Get the selected student from the student overview.
     *
     * @return The selected student.
     */
    Student getSelectedStudent();

    /**
     * Set the selected student.
     *
     * @param student The selected student to use.
     */
    void setSelectedStudent(Student student);

    /**
     * Does a teacher exist?
     *
     * @return
     */
    boolean hasTeachers();

    /**
     * Get the Session's teachers.
     *
     * @return
     */
    List<Teacher> getTeachers();

    /**
     * Set Session's teacher list array.
     */
    void setTeachers(List<Teacher> teachers);

    /**
     * Is a teacher logged in?
     */
    boolean isTeacherLoggedIn();

    /**
     * Get the current logged in teacher.
     *
     * @return
     */
    Teacher getLoggedInTeacher();

    /**
     * Assign the current logged in teacher.
     *
     * @param teacher The teacher to set as logged in.
     */
    void setLoggedInTeacher(Teacher teacher);

    /**
     * Get the current active stage.
     * @return The active stage.
     */
    Stage getActiveStage();
}
