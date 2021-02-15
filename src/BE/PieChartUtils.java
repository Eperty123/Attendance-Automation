package BE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author DennisPC-bit
 */

public class PieChartUtils {

    /**
     * Gets the students fraction of total attendance
     * @param studentList the students you want to examine
     * @return a pie chart of the students' attendance
     */
    public static PieChart getStudentPieChart(List<Student> studentList) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Student student : studentList){
            AtomicInteger i = new AtomicInteger(0);
            student.getDaysAttended().forEach(d->{
                if(d.getDayOfWeek().getValue()<6)
                    i.incrementAndGet();
            });
            pieChartData.add(new PieChart.Data(student.getName(), i.get()));
        }

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Fraction of total attendance");
        return pieChart;
    }

    /**
     * Finds the frequency of each day the student attends and makes a pie chart of it.
     * @param student the student you want to examine
     * @return a pie chart
     */
    public static PieChart getStudentPieChart(Student student) {
        List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int[] dayFreq = student.getWeekDaysAttended();
        for (int i = 0; i <= 4; i++) {
            if (dayFreq[i] > 0)
                pieChartData.add(new PieChart.Data(days.get(i), dayFreq[i]));
        }
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Pie chart of " + student.getName() + "'s Attendance");
        return pieChart;
    }

    /**
     * Gets the attendance per day for all students and turns it into a pie chart
     * @param studentList the students you want to examine
     * @return a pie chart of the data
     */
    public static PieChart getAttendancePerDayPieChart(List<Student> studentList) {
        List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int[] dayFreq = new int[5];
        studentList.forEach(student -> {
            student.getDaysAttended().forEach(d -> {
                if (d.getDayOfWeek().getValue() < 6)
                    dayFreq[d.getDayOfWeek().getValue() - 1] += 1;
            });
        });
        for (int i = 0; i < 5; i++) {
            if (dayFreq[i] > 0)
                pieChartData.add(new PieChart.Data(days.get(i), dayFreq[i]));
        }
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Fraction of total attendance");
        return pieChart;
    }
}
