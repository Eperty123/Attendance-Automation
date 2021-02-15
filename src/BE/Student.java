package BE;

import javafx.scene.layout.BorderPane;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The class responsible for defining Students.
 * Use GUIHelper to create GUI stuff from the Student class.
 */
public class Student {
    protected long id;
    protected String firstName = "";
    protected String lastName = "";
    protected String picture;
    protected List<LocalDateTime> daysAttended = new ArrayList<>();
    protected Absence mostAbsenceDay;
    protected Absence totalAbsence;
    protected BorderPane studentPane;

    public Student() {
    }

    public Student(String firstName) {
        this.firstName = firstName;
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Get the id of the student.
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Set the id of the student.
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * Get the first name of the student.
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the student's first name.
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the student's last name.
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the student's last name.
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the file path of the student's picture.
     *
     * @return
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Set the student's picture file path.
     *
     * @param picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Get the student's most absent day.
     *
     * @return
     */
    public Absence getMostAbsenceDay() {
        return mostAbsenceDay;
    }

    /**
     * Set the student's most absent day.
     *
     * @param mostAbsenceDay
     */
    public void setMostAbsenceDay(Absence mostAbsenceDay) {
        this.mostAbsenceDay = mostAbsenceDay;
    }

    /**
     * Get the student's total absence.
     *
     * @return
     */
    public Absence getTotalAbsence() {
        return totalAbsence;
    }

    /**
     * Set the student's total absence.
     *
     * @param totalAbsence
     */
    public void setTotalAbsence(Absence totalAbsence) {
        this.totalAbsence = totalAbsence;
    }

    /**
     * Get the student's assigned BorderPane.
     *
     * @return
     */
    public BorderPane getStudentPane() {
        return studentPane;
    }

    /**
     * Set the student's BorderPane.
     *
     * @param studentPane
     */
    public void setStudentPane(BorderPane studentPane) {
        this.studentPane = studentPane;
    }

    /**
     * Gets the days attended.
     *
     * @return the days attended
     */
    public List<LocalDateTime> getDaysAttended() {
        return daysAttended;
    }

    /**
     * Attends current date
     */
    public void attend() {
        daysAttended.add(LocalDateTime.now());
    }

    /**
     * Attends a specific date
     *
     * @param localDateTime the date you want to add
     */
    public void attend(LocalDateTime localDateTime) {
        daysAttended.add(localDateTime);
    }

    public int[] getWeekDaysAttended() {
        int[] dayFreq = new int[5];
        this.getDaysAttended().forEach(d -> {
            if (d.getDayOfWeek().getValue() < 6)
                dayFreq[d.getDayOfWeek().getValue() - 1] += 1;
        });
        return dayFreq;
    }
}
