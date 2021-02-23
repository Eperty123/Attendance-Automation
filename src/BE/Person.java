package BE;

import BE.Utils.AttendanceUtility;
import BE.Utils.GUIHelper;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import java.io.File;

public class Person {
    protected LongProperty id = new SimpleLongProperty(0);
    protected StringProperty firstName = new SimpleStringProperty("");
    protected StringProperty lastName = new SimpleStringProperty("");
    protected ObjectProperty<Image> picture = new SimpleObjectProperty<Image>(new Image("/GUI/Pictures/noIMG.png"));
    protected BorderPane personPane;

    public Person() {
    }

    public Person(int id, String firstName) {
        this.id.setValue(id);
        this.firstName.set(firstName);
        GUIHelper.createPersonBorderPane(this);
    }

    public Person(int id, String firstName, String lastName) {
        this.id.setValue(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        GUIHelper.createPersonBorderPane(this);
    }

    public Person(int id, String firstName, String lastName, String pictureUrl) {
        this.id.setValue(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.picture.setValue(new Image(pictureUrl));
        GUIHelper.createPersonBorderPane(this);
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
        File file = new File(picture);
        if (file.exists()) {
            this.picture.set(new Image(picture));
        }
    }

    /**
     * Get the student's assigned BorderPane.
     *
     * @return
     */
    public BorderPane getPersonPane() {
        return this.personPane;
    }

    /**
     * Set the student's BorderPane.
     *
     * @param studentPane
     */
    public void setPersonPane(BorderPane studentPane) {
        this.personPane = studentPane;
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
}
