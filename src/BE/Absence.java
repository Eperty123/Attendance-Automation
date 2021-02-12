package BE;

/**
 * The class responsible for displaying a Student's absence.
 * Implementation should be straightforward though.
 */
public class Absence {
    protected int day;
    protected float absencePercentage;

    /**
     * Get the day in int.
     * @return
     */
    public int getDay() {
        return day;
    }

    /**
     * Get the absence percentage.
     * @return
     */
    public float getAbsencePercentage() {
        return absencePercentage;
    }

    /**
     * Set the absence day.
     * @param day The day in int (1-7).
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Set the absence percentage.
     * @param absencePercentage The total absence percentage.
     */
    public void setAbsencePercentage(float absencePercentage) {
        this.absencePercentage = absencePercentage;
    }
}
