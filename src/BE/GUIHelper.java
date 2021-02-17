package BE;

import GUI.CONTROLLER.AttendanceOverviewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;

public class GUIHelper {

    /**
     * Create a BorderPane scene element for the given Student.
     *
     * @param student The student to create a BorderPane for.
     * @param font    The font to use for the first and last name.
     * @param width   The width for the student's picture.
     * @param height  The height for the student's picture.
     * @return Returns the created BorderPane. Null if the student is null.
     */
    public static BorderPane createStudentBorderPane(Student student, Font font, double width, double height, String style) {
        if (student != null) {
            var paneReadyStudent = createStudentBorderPane(student);

            // Get the student's first and last name as well as picture path.
            Text studentName = new Text(String.format("%s \n%s", student.getFirstName(), student.getLastName()));
            studentName.setFont(font);
            ImageView picture = new ImageView(student.getPicture());
            picture.setPreserveRatio(true);
            picture.setFitWidth(width);
            picture.setFitHeight(height);

            // Adjust to fit the BorderPane.
            BorderPane.setAlignment(studentName, Pos.TOP_CENTER);
            BorderPane.setAlignment(picture, Pos.CENTER);

            // Add the elements (nodes) to the BorderPane.
            paneReadyStudent.getStudentPane().setTop(studentName);
            paneReadyStudent.getStudentPane().setCenter(picture);

            // Adding styling.
            paneReadyStudent.getStudentPane().setStyle(style);

            // Return the created BorderPane.
            return paneReadyStudent.getStudentPane();
        }
        return null;
    }

    /**
     * Create a BorderPane scene element for the given Student.
     *
     * @param student The student to create a BorderPane for.
     * @return Returns the created BorderPane. Null if the student is null.
     */
    public static Student createStudentBorderPane(Student student) {
        if (student != null) {
            var pane = new BorderPane();

            // Get the student's first and last name as well as picture path.
            Text studentName = new Text(String.format("%s \n%s", student.getFirstName(), student.getLastName()));
            studentName.setFont(AttendanceOverviewController.FONT);
            ImageView picture = new ImageView(student.getPicture());
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
            pane.setAccessibleText(String.format("%d", student.getId()));

            // Assign the BorderPane to the student.
            student.studentPane = pane;

            // Return the student with the created BorderPane.
            return student;
        }
        return null;
    }

    /**
     * Create a BorderPane for a PieChart.
     *
     * @param pieChart The input PieChart.
     * @return Returns the created BorderPane with the input PieChart attached.
     */
    public static BorderPane createPieChartBorderPane(PieChart pieChart, Font font) {
        if (pieChart != null) {
            var pane = new BorderPane();

            BorderPane.setAlignment(pieChart, Pos.CENTER);
            pane.setCenter(pieChart);

            // Chart data.
            var pieData = pieChart.getData();
            double totalPercentage = 0;

            // TODO: Correct the calculation of total absence (if wrong).
            for (int i = 0; i < pieData.size(); i++) {
                var percentage = pieData.get(i);
                totalPercentage += percentage.getPieValue();
            }

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
}
