package BE;

import javafx.beans.property.*;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;


public class Student {

    private IntegerProperty studentId = new SimpleIntegerProperty(-1);
    private StringProperty name = new SimpleStringProperty("");
    private StringProperty pictureUrl = new SimpleStringProperty("GUI/IMG/noIMG.png");
    public static final Font personPaneFont = Font.font("System Bold", 24);
    public static DoubleProperty width = new SimpleDoubleProperty(100);
    public static DoubleProperty height = new SimpleDoubleProperty(100);
    public static final boolean PRESERVE_RATIO = true;
    private BorderPane personPane = new BorderPane();
    private ImageView imageView = new ImageView(pictureUrl.getValue());
    private Label label = new Label("");

    public Label getLabel() {
        return label;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setLabel(String labelText) {
        label.setText(labelText);
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Student(String name){
        this.name.set(name);
    }

    public Student(int studentId, String name){
        this.studentId.set(studentId);
        this.name.set(name);

    }

    public Student(int studentId, String name, String pictureUrl){
        this.studentId.set(studentId);
        this.name.set(name);
        this.pictureUrl.set(pictureUrl);
    }

    public int getStudentId() {
        return studentId.get();
    }

    public IntegerProperty studentIdProperty() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId.set(studentId);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPictureUrl() {
        return pictureUrl.get();
    }

    public StringProperty pictureUrlProperty() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl.set(pictureUrl);
    }

    public BorderPane makePersonPane() {
        imageView.setImage(new Image(pictureUrl.getValue()));
        imageView.setPreserveRatio(PRESERVE_RATIO);
        imageView.setFitHeight(height.get());
        imageView.setFitWidth(width.get());

        this.personPane.setCenter(imageView);
        label.setText(name.getValue());
        BorderPane.setAlignment(label,Pos.CENTER);
        label.setFont(personPaneFont);
        this.personPane.setTop(label);
        this.personPane.setStyle("-fx-background-color: lightgrey; -fx-background-radius: 10;");

        return personPane;
    }

    public void updatePersonPane() {
        imageView.setImage(new Image(pictureUrl.getValue()));
        imageView.setPreserveRatio(PRESERVE_RATIO);
        imageView.setFitHeight(height.get());
        imageView.setFitWidth(width.get());
        this.personPane.setCenter(imageView);

        label.setText(name.getValue());
        label.setFont(personPaneFont);
        this.personPane.setTop(label);
        this.personPane.setStyle("-fx-background-color: lightgrey; -fx-background-radius: 10;");
    }

    public BorderPane getPersonPane(){
        return  personPane;
    }

    public void setPersonPane(BorderPane personPane) {
        this.personPane = personPane;
    }
}
