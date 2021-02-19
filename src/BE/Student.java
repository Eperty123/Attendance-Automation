package BE;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The class responsible for defining Students.
 * Use GUIHelper to create GUI stuff from the Student class.
 */
public class Student {
    protected LongProperty id = new SimpleLongProperty(0);
    protected StringProperty firstName = new SimpleStringProperty("");
    protected StringProperty lastName = new SimpleStringProperty("");
    protected ObjectProperty<Image> picture = new SimpleObjectProperty<Image>(new Image("/GUI/Pictures/noIMG.png"));
    protected BorderPane studentPane;
    protected AttendanceUtil attendanceUtil;

    public Student() {
        attendanceUtil = new AttendanceUtil();
    }

    public Student(int id, String firstName) {
        attendanceUtil = new AttendanceUtil();
        this.id.setValue(id);
        this.firstName.set(firstName);
        GUIHelper.createStudentBorderPane(this);
    }

    public Student(int id, String firstName, String lastName) {
        attendanceUtil = new AttendanceUtil();
        this.id.setValue(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        GUIHelper.createStudentBorderPane(this);
    }

    public Student(int id, String firstName, String lastName, String pictureUrl) {
        attendanceUtil = new AttendanceUtil();
        this.id.setValue(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.picture.setValue(new Image(pictureUrl));
        GUIHelper.createStudentBorderPane(this);
    }

    /**
     * Get the id of the student.
     *
     * @return
     */
    public long getId() {
        return id.get();
    }


    /**
     * Gets the id property
     *
     * @return the id property
     */
    public LongProperty idProperty() {
        return id;
    }

    /**
     * Set the id of the student.
     *
     * @param id
     */
    public void setId(long id) {
        this.id.setValue(id);
    }

    public String getFullName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

    /**
     * Get the first name of the student.
     *
     * @return
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * Set the student's first name.
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * Get the student's last name.
     *
     * @return
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * Set the student's last name.
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * Get the file path of the student's picture.
     *
     * @return
     */
    public Image getPicture() {
        return picture.get();
    }

    /**
     * Set the student's picture file path.
     *
     * @param picture
     */
    public void setPicture(String picture) {
        this.picture.set(new Image(picture));
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
     * Gets the first name property
     *
     * @return firstNAmeProperty
     */
    public StringProperty firstNameProperty() {
        return firstName;
    }

    /**
     * Gets the last name property
     *
     * @return lastNameProperty
     */
    public StringProperty lastNameProperty() {
        return lastName;
    }

    /**
     * Gets the picture property
     *
     * @return pictureProperty
     */
    public ObjectProperty<Image> pictureProperty() {
        return picture;
    }

    public AttendanceUtil getAttendanceUtil() {
        return attendanceUtil;
    }

    /**
     * Gets the total absence from the attendance util
     * @return the total absence
     */
    public int getTotalAbsence(){
        return attendanceUtil.getTotalAbsence();
    }

    /**
     * Has this student already attended and registered for today?
     *
     * @return Returns true if yes otherwise false.
     * @throws ParseException
     */
    public boolean hasAttendedToday() {
        var lastAttence = getLastAttendance();
        var today = LocalDateTime.now();
        return lastAttence.isAfter(today) || lastAttence.equals(today);
    }
}
