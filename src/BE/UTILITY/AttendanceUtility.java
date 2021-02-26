package BE.UTILITY;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class AttendanceUtility {
    static List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
    protected static Set<LocalDate> dateSet = new HashSet<LocalDate>();
    protected List<LocalDateTime> daysAttended;
    protected Set<LocalDate> studentDateSet;

    public AttendanceUtility(){
        this.daysAttended = new ArrayList<LocalDateTime>();
        this.studentDateSet = new HashSet<LocalDate>();
    }

    /**
     * Attends current date
     */
    public void attend() {
        this.daysAttended.add(LocalDateTime.now());
        this.studentDateSet.add(LocalDate.now());
        dateSet.add(LocalDate.now());
    }

    /**
     * Attends a specific date.
     *
     * @param localDateTime the date you want to add
     */
    public void attend(LocalDateTime localDateTime) {
        this.daysAttended.add(localDateTime);
        this.studentDateSet.add(localDateTime.toLocalDate());
        dateSet.add(localDateTime.toLocalDate());
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
     * gets days with at least one attend
     *
     * @return a set of days
     */
    public Set<LocalDate> getDaysWithAtleastOneAttend() {
        return dateSet;
    }


    /**
     * Get the student's weekdays attended.
     *
     * @return an array of integers where day[i] = i's frequency
     */
    public int[] getWeekDaysAttended() {
        int[] dayFreq = new int[5];
        this.studentDateSet.forEach(d -> {
            if (d.getDayOfWeek().getValue() < 6)
                dayFreq[d.getDayOfWeek().getValue() - 1] += 1;
        });
        return dayFreq;
    }

    /**
     * Gets the absence percentage as a percentage of days where at least one student attended
     *
     * @return A percentage of total attendance
     */
    public double getAbsencePercentage() {
        Set<LocalDate> absentDays = updateAbsentDates();
        return (double) (absentDays.size() * 100) / AttendanceUtility.dateSet.size();
    }

    /**
     * Get the student's most absent day.
     *
     * @return the day with most absence or none if there is no max.
     */
    public String getMostAbsentDay() {
        Set<LocalDate> absentDays = updateAbsentDates();
        int[] dayFreq = new int[5];
        absentDays.forEach(d -> {
                    if (d.getDayOfWeek().getValue() < 6)
                        dayFreq[d.getDayOfWeek().getValue() - 1] += 1;
                }
        );
        return Arrays.stream(dayFreq).max().isPresent() ? days.get(Arrays.stream(dayFreq).max().getAsInt()) : "none";
    }

    /**
     * Get the student's total absence.
     *
     * @return the amount of absence
     */
    public int getTotalAbsence() {
        Set<LocalDate> absentDays = updateAbsentDates();
        return absentDays.size();
    }

    /**
     * Get the students absence each day, where day[i] = the absence of day i
     *
     * @return a array of frequencies
     */
    public int[] getWeekDaysAbsent() {
        Set<LocalDate> absentDays = updateAbsentDates();
        int[] dayFreq = new int[5];
        absentDays.forEach(d -> {
                    if (d.getDayOfWeek().getValue() < 6)
                        dayFreq[d.getDayOfWeek().getValue() - 1] += 1;
                }
        );
        return dayFreq;
    }

    private Set<LocalDate> updateAbsentDates() {
        Set<LocalDate> absentDays = new HashSet<>(getDaysWithAtleastOneAttend());
        daysAttended.forEach(d->absentDays.remove(d.toLocalDate()));
        return absentDays;
    }

    /**
     * gets the list of absent days
     *
     * @return a list of the absent days
     */
    public Set<LocalDate> getAbsentDays() {
        Set<LocalDate> absentDays = updateAbsentDates();
        return absentDays;
    }


    /**
     * Get the last attendance date.
     *
     * @return the last attendance of this instance of attendance util
     */
    public LocalDateTime getLastAttendance() {
        return getDaysAttended().get(getDaysAttended().size() - 1);
    }
}
