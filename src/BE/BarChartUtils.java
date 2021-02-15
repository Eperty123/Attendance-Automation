package BE;

import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author DennisPC-bit
 *
 */

public class BarChartUtils {

    /**
     * Loops thought the students to get the frequency of each date and returns a barchart of these data.
     * @param students a list of students
     * @return a bar chart of the students' attendance
     */
    public static BarChart<String, Number> getTotalIndividualAttendanceBarChart(List<Student> students) {
        List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableList(days));
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Attendance");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Individual Attendance");

        for(Student student : students) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(student.getName());
            List<LocalDateTime> attendedDates = new ArrayList<>(student.getDaysAttended());
            int[] dayFreq = student.getWeekDaysAttended();
            for (int i = 0; i <= 4; i++) {
                series.getData().add(new XYChart.Data<>(days.get(i), dayFreq[i]));
            }
            barChart.getData().add(series);
        }
        return barChart;
    }

    /**
     * Runs through the attended dates of a student to determine the frequency of each date
     * @param student the student
     * @return A barchart containing the frequency of the days for the student
     */
    public static BarChart<String, Number> getTotalAttendanceBarChart(Student student) {
        List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableList(days));
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Attendance");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle(student.getName() + "'s Attendance");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(student.getName());
        int[] dayFreq = student.getWeekDaysAttended();
        for (int i = 0; i <= 4; i++) {
            series.getData().add(new XYChart.Data<>(days.get(i), dayFreq[i]));
        }
        barChart.getData().add(series);

        return barChart;
    }

    /**
     * Loops through the students to get the attended dates.
     * @param studentList the students you want to examine
     * @return A barchart containing the data on the students in the studentList.
     */
    public static BarChart<String, Number> getTotalAttendanceBarChart(List<Student> studentList) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Student");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Attendance");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Total Attendance");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Total attendance");
        studentList.forEach(p -> {
            AtomicInteger i = new AtomicInteger();
            p.getDaysAttended().forEach(d -> {
                if (d.getDayOfWeek().getValue() < 6)
                    i.getAndIncrement();
            });
            series1.getData().add(new XYChart.Data<>(p.getName(), i));
        });
        barChart.getData().add(series1);
        return barChart;
    }

    /**
     * Runs through the students' attendance to get the attendance per day.
     * @param studentList the students you want to examine
     * @return A bar chart with the data.
     */
    public static BarChart<String, Number> getAttendancePerDay(List<Student> studentList) {
        List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.observableList(days));
        xAxis.setLabel("Day");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Attendance");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Total Attendance");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Total attendance");
        int[] dayFreq = new int[5];
        studentList.forEach(student -> {
            student.getDaysAttended().forEach(d -> {
                if (d.getDayOfWeek().getValue() < 6)
                    dayFreq[d.getDayOfWeek().getValue() - 1] += 1;
            });
        });
        for (int i = 0; i < 5; i++)
            series.getData().add(new XYChart.Data<>(days.get(i), dayFreq[i]));
        barChart.getData().add(series);
        return barChart;
    }
}
