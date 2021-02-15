package BE;

import javafx.scene.layout.BorderPane;

/**
 * The class responsible for defining Students.
 * Use GUIHelper to create GUI stuff from the Student class.
 */
public class Student {
    protected long id;
    protected String firstName;
    protected String lastName;
    protected String picture;

    protected Absence mostAbsenceDay;
    protected Absence totalAbsence;
    protected BorderPane studentPane;

    /**
     * Get the id of the student.
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Set the id of the student.
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the first name of the student.
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the student's first name.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the student's last name.
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the student's last name.
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the file path of the student's picture.
     * @return
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Set the student's picture file path.
     * @param picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Get the student's most absent day.
     * @return
     */
    public Absence getMostAbsenceDay() {
        return mostAbsenceDay;
    }

    /**
     * Set the student's most absent day.
     * @param mostAbsenceDay
     */
    public void setMostAbsenceDay(Absence mostAbsenceDay) {
        this.mostAbsenceDay = mostAbsenceDay;
    }

    /**
     * Get the student's total absence.
     * @return
     */
    public Absence getTotalAbsence() {
        return totalAbsence;
    }

    /**
     * Set the student's total absence.
     * @param totalAbsence
     */
    public void setTotalAbsence(Absence totalAbsence) {
        this.totalAbsence = totalAbsence;
    }

    /**
     * Get the student's assigned BorderPane.
     * @return
     */
    public BorderPane getStudentPane() {
        return studentPane;
    }

    /**
     * Set the student's BorderPane.
     * @param studentPane
     */
    public void setStudentPane(BorderPane studentPane) {
        this.studentPane = studentPane;
    }
}
