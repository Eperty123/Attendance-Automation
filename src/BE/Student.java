package BE;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

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
    protected List<LocalDateTime> daysAttended = new ArrayList<>();
    protected BorderPane studentPane;
    protected List<LocalDate> absentDays = new ArrayList<>();
    protected static Set<LocalDate> dateSet = new HashSet<LocalDate>();
    protected Set<LocalDate> studentDateSet = new HashSet<LocalDate>();

    public Student() {
    }

    public Student(int id, String firstName) {
        this.id.setValue(id);
        this.firstName.set(firstName);
        GUIHelper.createStudentBorderPane(this);
    }

    public Student(int id, String firstName, String lastName) {
        this.id.setValue(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        GUIHelper.createStudentBorderPane(this);
    }

    public Student(int id, String firstName, String lastName, String pictureUrl) {
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

    public double getAbsencePercentage() {
        return (double) (absentDays.size() * 100) / Student.dateSet.size();
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
     * Get the student's most absent day.
     *
     * @return
     */
    public OptionalInt getMostAbsenceDay() {
        int[] dayFreq = new int[5];
        absentDays.forEach(d -> {
                    if (d.getDayOfWeek().getValue() < 6)
                        dayFreq[d.getDayOfWeek().getValue() - 1] += 1;
                }
        );
        return Arrays.stream(dayFreq).max();
    }

    /**
     * Get the student's total absence.
     *
     * @return
     */
    public int getTotalAbsence() {
        return absentDays.size();
    }

    /**
     * gets days with atleast one attend
     * @return a set of days
     */
    public Set<LocalDate> getDaysWithAtleastOneAttend() {
        return dateSet;
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

    /**
     * Gets the days attended.
     *
     * @return the days attended.
     */
    public List<LocalDateTime> getDaysAttended() {
        return daysAttended;
    }

    /**
     * Attends current date
     */
    public void attend() {
        daysAttended.add(LocalDateTime.now());
        studentDateSet.add(LocalDate.now());
        dateSet.add(LocalDate.now());
        absentDays = new ArrayList<>(Student.dateSet);
        absentDays.removeAll(this.studentDateSet);
    }

    /**
     * Attends a specific date.
     *
     * @param localDateTime the date you want to add
     */
    public void attend(LocalDateTime localDateTime) {
        daysAttended.add(localDateTime);
        studentDateSet.add(localDateTime.toLocalDate());
        dateSet.add(localDateTime.toLocalDate());
        absentDays = new ArrayList<>(Student.dateSet);
        absentDays.removeAll(this.studentDateSet);
    }

    /**
     * Get the student's weeks attended.
     *
     * @return
     */
    public int[] getWeekDaysAttended() {
        int[] dayFreq = new int[5];
        this.studentDateSet.forEach(d -> {
            if (d.getDayOfWeek().getValue() < 6)
                dayFreq[d.getDayOfWeek().getValue() - 1] += 1;
        });
        return dayFreq;
    }
}
