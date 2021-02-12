package BLL;

import BE.Student;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.File;

public class GUIHelper {

    /**
     * Create a BorderPane scene element for the given Student.
     *
     * @param student The student to create a BorderPane for.
     * @return Returns the created BorderPane. Null if the student is null.
     */
    public static BorderPane createStudentBorderPane(Student student) {
        if (student != null) {
            var pane = new BorderPane();

            // Get the student's first and last name as well as picture path.
            Text studentName = new Text(String.format("%s %s", student.getFirstName(), student.getLastName()));
            ImageView picture = new ImageView(new File(student.getPicture()).toURI().toString());

            // Set the picture's width and height.
            picture.setFitWidth(100);
            picture.setFitHeight(100);

            // Adjust to fit the BorderPane.
            BorderPane.setAlignment(studentName, Pos.TOP_CENTER);
            BorderPane.setAlignment(picture, Pos.CENTER);

            // Add the elements (nodes) to the BorderPane.
            pane.setTop(studentName);
            pane.setCenter(picture);

            // Add some styling.
            pane.setStyle("-fx-background-color: lightgrey; -fx-background-radius: 10;");
            pane.setPadding(new Insets(8, 8, 8, 8));

            // We must use FlowPane's setMargin since we'll be adding it to a FlowPane.
            FlowPane.setMargin(pane, new Insets(10, 10, 10, 10));

            // Return the created BorderPane.
            return pane;
        }
        return null;
    }
}
