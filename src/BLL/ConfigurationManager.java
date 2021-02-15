package BLL;

import BE.Configuration;
import BE.Student;

import java.util.List;

public class ConfigurationManager {
    protected Configuration configuration;
    protected static ConfigurationManager instance;

    private void initialize() {
        configuration = new Configuration();
    }

    public  ConfigurationManager() {
        initialize();
    }

    public boolean hasStudents() {
        return configuration.hasStudents();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public List<Student> getStudentList() {
        return configuration.getStudentList();
    }

    public void setStudentList(List<Student> studentList) {
        configuration.setStudentList(studentList);
    }

    public Student getSelectedStudent() {
        return configuration.getSelectedStudent();
    }

    public void setSelectedStudent(Student student) {
        if (student != null) {
            configuration.setSelectedStudent(student);
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) instance = new ConfigurationManager();
        return instance;
    }
}
