package BE;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import java.io.File;

public class Teacher extends Person {
    public static final String password = "123";
    public Teacher(int id, String firstName, String lastName, String pictureUrl) {
        super(id, firstName,lastName,pictureUrl);
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public LongProperty idProperty() {
        return super.idProperty();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    @Override
    public String getFullName() {
        return super.getFullName();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public Image getPicture() {
        return super.getPicture();
    }

    @Override
    public void setPicture(String picture) {
        File file = new File(picture);
        if (file.exists()) {
            super.picture.set(new Image(picture));
        }
    }

    @Override
    public BorderPane getPersonPane() {
        return super.getPersonPane();
    }

    @Override
    public void setPersonPane(BorderPane studentPane) {
        super.setPersonPane(studentPane);
    }

    @Override
    public StringProperty firstNameProperty() {
        return super.firstNameProperty();
    }

    @Override
    public StringProperty lastNameProperty() {
        return super.lastNameProperty();
    }

    @Override
    public ObjectProperty<Image> pictureProperty() {
        return super.pictureProperty();
    }
}
