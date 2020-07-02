package model;

import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

    private final ObjectProperty<Speed> speed = new SimpleObjectProperty(this, "speed", Speed.TWO);
    private final ObjectProperty<Flow> flow = new SimpleObjectProperty(this, "flow", Flow.ONE);
    private final ObjectProperty<Duration> duration = new SimpleObjectProperty(this, "duration", Duration.ONE);
    
    int numberIdentification1 = 0;

    public void setNumberIdentification(int numberIdentification1) {
        this.numberIdentification1 = numberIdentification1;
    }

    public int getNumberIdentification() {
        return numberIdentification1;
    }

    private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    private final StringProperty address = new SimpleStringProperty(this, "address", "");

    public Person() {

    }

    public Person(Speed speed, Flow flow, Duration duration, int numberIdentification1, String firstName, String lastName, String address) {
        this.speed.set(speed);
        this.flow.set(flow);
        this.duration.set(duration);
        this.numberIdentification1 = numberIdentification1;
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.address.set(address);
    }

    public Speed getSpeed() {
        return speed.get();
    }

    public void setSpeed(Speed speed) {
        this.speed.set(speed);
    }

    public ObjectProperty<Speed> speedProperty() {
        return speed;
    }

    public Flow getFlow() {
        return flow.get();
    }

    public void setFlow(Flow flow) {
        this.flow.set(flow);
    }

    public ObjectProperty<Flow> flowProperty() {
        return flow;
    }

    public Duration getDuration() {
        return duration.get();
    }

    public void setDuration(Duration duration) {
        this.duration.set(duration);
    }

    public ObjectProperty<Duration> durationProperty() {
        return duration;
    }

    public Person(String firstName) {
        this.firstName.set(firstName);
    }

    public Person(String firstName, String lastName) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    private final ObjectProperty<ArrayList<String>> errorList = new SimpleObjectProperty<>(this, "errorList", new ArrayList<>());

    public ObjectProperty<ArrayList<String>> errorsProperty() {
        return errorList;
    }

    public boolean isValid() {
        boolean isValid = true;
        if (firstName.get() != null && firstName.get().equals("")) {
            errorList.getValue().add("First name can't be empty!");
            isValid = false;
        }
        if (lastName.get().equals("")) {
            errorList.getValue().add(" Last name can't be empty!");
            isValid = false;
        }
        if (address.get().equals("")) {
            errorList.getValue().add(" Address can't be empty!");
            isValid = false;
        }
        return isValid;
    }

}
