package BE;

import java.time.LocalDate;

public class DateUtility {
    /**
     * Check if the specified date is past a certain date.
     *
     * @param from  The date to compare with.
     * @param until The date to compare to.
     * @return Returns true if it's past otherwise false.
     */
    public static boolean isPast(LocalDate from, LocalDate until) {
        LocalDate today = LocalDate.now();
        return today.isAfter(from) || today.isAfter(until);
    }
}
