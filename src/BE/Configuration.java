package BE;

import java.util.List;

/**
 * The class responsible for saving the student data temporarily.
 */
public class Configuration {
    private static Configuration instance;
    private List<Student> studentList;
    private Student selectedStudent;

    public static Configuration getInstance() {
        if (instance == null) instance = new Configuration();
        return instance;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public boolean hasStudents() {
        return studentList != null && studentList.size() > 0;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }
}
