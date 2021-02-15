package BE;

import java.util.List;

/**
 * The class responsible for saving the student data temporarily.
 */
public class Session {
    private static Session instance;
    private List<Student> studentList;
    private Student selectedStudent;

    public static Session getInstance() {
        if (instance == null) instance = new Session();
        return instance;
    }

    /**
     * Get the student list array.
     * @return
     */
    public List<Student> getStudentList() {
        return studentList;
    }

    /**
     * Set the student list array.
     * @param studentList
     */
    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    /**
     * Does a student currently exist?
     * @return Returns true if yes otherwise false.
     */
    public boolean hasStudents() {
        return studentList != null && studentList.size() > 0;
    }

    /**
     * Get the selected student.
     * @return
     */
    public Student getSelectedStudent() {
        return selectedStudent;
    }

    /**
     * Set the selected student.
     * @param selectedStudent The selected student to use.
     */
    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }
}
