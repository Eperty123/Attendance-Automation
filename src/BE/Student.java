package BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Student implements Initializable {

    public static IntegerProperty FIT_SIZE = new SimpleIntegerProperty(100);
    public static final boolean PRESERVE_RATIO = true;
    private IntegerProperty studentId = new SimpleIntegerProperty(-1);
    private StringProperty name = new SimpleStringProperty("");
    private StringProperty pictureUrl = new SimpleStringProperty("");
    private BorderPane personPane = new BorderPane();
    private Label label = new Label("");
    private ImageView imageView = new ImageView("GUI/IMG/noIMG.png");

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

    public static void setFitSize(int fitSize) {
        FIT_SIZE.set(fitSize);
    }

    public static IntegerProperty getFitSize(){
        return FIT_SIZE;
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
        if(!this.pictureUrl.getValue().isBlank()){
            imageView.setImage(new Image(pictureUrl.getValue()));
        }
        imageView.setFitHeight(FIT_SIZE.get());
        imageView.setFitWidth(FIT_SIZE.get());

        imageView.setPreserveRatio(PRESERVE_RATIO);
        this.personPane.setCenter(imageView);
        label.setText(name.getValue());
        this.personPane.setTop(label);
        this.personPane.setPrefSize(FIT_SIZE.get()*1.2,FIT_SIZE.get()*1.2);
        return personPane;
    }

    public BorderPane getPersonPane(){
        return  personPane;
    }

    public void setPersonPane(BorderPane personPane) {
        this.personPane = personPane;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.addListener((Observable,t1,t2)-> this.label.setText(t2));
        pictureUrl.addListener((Observable,t1,t2)-> this.imageView.setImage(new Image(t2)));
    }
}
