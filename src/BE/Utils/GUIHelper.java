package BE.Utils;

import BE.Person;
import BE.Student;
import GUI.CONTROLLER.AttendanceOverviewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GUIHelper {
    /**
     * Create a BorderPane for a PieChart.
     *
     * @param pieChart The input PieChart.
     * @return Returns the created BorderPane with the input PieChart attached.
     */
    public static BorderPane createPieChartBorderPane(PieChart pieChart, Student student, Font font) {
        if (pieChart != null) {
            var pane = new BorderPane();

            BorderPane.setAlignment(pieChart, Pos.CENTER);
            pane.setCenter(pieChart);

            // Chart data.
            var pieData = pieChart.getData();

            // TODO: Correct the calculation of total absence (if wrong).
            double totalPercentage = student.getAttendanceUtil().getAbsencePercentage();

        var totalAbsencePercentageText = new Text(String.format("Total absence: %s%%", totalPercentage));
        totalAbsencePercentageText.setFont(font);
        BorderPane.setAlignment(totalAbsencePercentageText, Pos.CENTER);
        pane.setBottom(totalAbsencePercentageText);

        // Add styling.
        pane.setPadding(new Insets(8, 8, 8, 8));
        FlowPane.setMargin(pane, new Insets(10, 10, 10, 10));

        // Return the created BorderPane.
        return pane;
    }
        return null;
}

    /**
     * Creates a borderpane for the person
     * @param person
     */
    public static void createPersonBorderPane(Person person) {
        if (person != null) {
            var pane = new BorderPane();

            // Get the student's first and last name as well as picture path.
            Text studentName = new Text(String.format("%s \n%s", person.getFirstName(), person.getLastName()));
            studentName.setFont(AttendanceOverviewController.FONT);
            ImageView picture = new ImageView(person.getPicture());
            picture.setPreserveRatio(true);
            picture.setFitWidth(AttendanceOverviewController.WIDTH);
            picture.setFitHeight(AttendanceOverviewController.HEIGHT);

            // Adjust to fit the BorderPane.
            BorderPane.setAlignment(studentName, Pos.TOP_CENTER);
            BorderPane.setAlignment(picture, Pos.CENTER);

            // Add the elements (nodes) to the BorderPane.
            pane.setTop(studentName);
            pane.setCenter(picture);

            // Adding styling.
            pane.setStyle(AttendanceOverviewController.DEFAULT_STYLE);
            pane.setPadding(new Insets(8, 8, 8, 8));
            FlowPane.setMargin(pane, new Insets(10, 10, 10, 10));

            // Assign an id.
            pane.setAccessibleText(String.format("%d", person.getId()));

            // Assign the BorderPane to the student.
            person.setPersonPane(pane);
        }
    }
}
