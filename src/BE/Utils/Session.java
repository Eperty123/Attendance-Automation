package BE.Utils;

import BE.Student;
import BE.Teacher;

import java.util.List;

/**
 * The class responsible for saving the student data temporarily.
 */
public class Session {
    private List<Student> studentList;
    private Student selectedStudent;
    private Teacher loggedInTeacher;
    private List<Teacher> teacherList;

    /**
     * Get the student list array.
     *
     * @return
     */
    public List<Student> getStudentList() {
        return studentList;
    }

    /**
     * Set the student list array.
     *
     * @param studentList
     */
    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    /**
     * Does a student currently exist?
     *
     * @return Returns true if yes otherwise false.
     */
    public boolean hasStudents() {
        return studentList != null && studentList.size() > 0;
    }

    /**
     * Get the selected student.
     *
     * @return
     */
    public Student getSelectedStudent() {
        return selectedStudent;
    }

    /**
     * Set the selected student.
     *
     * @param selectedStudent The selected student to use.
     */
    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    /**
     * Does a teacher currently exist?
     *
     * @return
     */
    public boolean hasTeachers() {
        return teacherList != null && teacherList.size() > 0;
    }

    /**
     * Get the assigned teacher login.
     *
     * @return
     */
    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    /**
     * Set the teacher login.
     *
     * @param teacherList The teacher login to use.
     */
    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    /**
     * Get the logged in teacher.
     *
     * @return
     */
    public Teacher getLoggedInTeacher() {
        return loggedInTeacher;
    }

    /**
     * Set the current logged in teacher.
     *
     * @param loggedInTeacher
     */
    public void setLoggedInTeacher(Teacher loggedInTeacher) {
        this.loggedInTeacher = loggedInTeacher;
    }
}
