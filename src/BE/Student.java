package BE;

import BE.UTILITY.AttendanceUtility;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import java.io.File;

/**
 * The class responsible for defining Students.
 * Use GUIHelper to create GUI stuff from the Student class.
 */
public class Student extends Person{
    protected BorderPane personPane;
    protected AttendanceUtility attendanceUtility = new AttendanceUtility();

    public Student() {
        super();
    }

    public Student(int id, String firstName) {
        super(id,firstName);
    }

    public Student(int id, String firstName, String lastName) {
        super(id,firstName,lastName);
    }

    public Student(int id, String firstName, String lastName, String pictureUrl) {
        super(id,firstName,lastName,pictureUrl);
    }

    /**
     * Get the id of the student.
     *
     * @return
     */
    @Override
    public long getId() {
        return super.id.get();
    }


    /**
     * Gets the id property
     *
     * @return the id property
     */
    @Override
    public LongProperty idProperty() {
        return super.id;
    }

    /**
     * Set the id of the student.
     *
     * @param id
     */
    @Override
    public void setId(long id) {
        super.id.setValue(id);
    }

    @Override
    public String getFullName() {
        return String.format("%s %s", super.getFirstName(), super.getLastName());
    }

    /**
     * Get the first name of the student.
     *
     * @return
     */
    @Override
    public String getFirstName() {
        return super.firstName.get();
    }

    /**
     * Set the student's first name.
     *
     * @param firstName
     */
    @Override
    public void setFirstName(String firstName) {
        super.firstName.set(firstName);
    }

    /**
     * Get the student's last name.
     *
     * @return
     */
    @Override
    public String getLastName() {
        return super.lastName.get();
    }

    /**
     * Set the student's last name.
     *
     * @param lastName
     */
    @Override
    public void setLastName(String lastName) {
        super.lastName.set(lastName);
    }

    /**
     * Get the file path of the student's picture.
     *
     * @return
     */
    @Override
    public Image getPicture() {
        return super.picture.get();
    }

    /**
     * Set the student's picture file path.
     *
     * @param picture
     */
    @Override
    public void setPicture(String picture) {
        if(!picture.isEmpty()){
        File file = new File(picture);
        if (file.exists()) {
            super.picture.set(new Image(picture));
        }
        }
    }

    /**
     * Get the student's assigned BorderPane.
     *
     * @return
     */
    public BorderPane getPersonPane() {
        return super.personPane;
    }

    /**
     * Set the student's BorderPane.
     *
     * @param personPane
     */
    public void setPersonPane(BorderPane personPane) {
        super.personPane = personPane;
    }

    /**
     * Gets the first name property
     *
     * @return firstNAmeProperty
     */
    @Override
    public StringProperty firstNameProperty() {
        return super.firstName;
    }

    /**
     * Gets the last name property
     *
     * @return lastNameProperty
     */
    @Override
    public StringProperty lastNameProperty() {
        return super.lastName;
    }

    /**
     * Gets the picture property
     *
     * @return pictureProperty
     */
    @Override
    public ObjectProperty<Image> pictureProperty() {
        return super.picture;
    }

    public AttendanceUtility getAttendanceUtil() {
        return attendanceUtility;
    }

    /**
     * Gets the total absence from the attendance util
     * @return the total absence
     */
    public int getTotalAbsence(){
        return attendanceUtility.getTotalAbsence();
    }
}
