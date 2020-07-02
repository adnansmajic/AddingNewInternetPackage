package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import model.Duration;
import model.Flow;
import model.Person;
import model.Speed;

public class PersonController {

    @FXML
    private ToggleGroup speed;
    @FXML
    private ToggleGroup flow;
    @FXML
    private ToggleGroup duration;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;

    @FXML
    private Button saveBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button deleteBtn;

    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    String speedOption;

    ObservableList<Person> persons = FXCollections.<Person>observableArrayList();
    Person person;

    @FXML
    TableView<Person> table = new TableView<>();

    public PersonController() {
    }

    @FXML
    private void initialize() {
        person = new Person();

        speed.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            if (newValue != null) {
                ToggleButton selected = (ToggleButton) newValue;
                switch (selected.getId()) {
                    case "five":
                        person.speedProperty().set(Speed.FIVE);

                        break;
                    case "ten":
                        person.speedProperty().set(Speed.TEN);
                        break;
                    case "twenty":
                        person.speedProperty().set(Speed.TWENTY);
                        break;
                    case "fifty":
                        person.speedProperty().set(Speed.FIFTY);
                        break;
                    case "hundred":
                        person.speedProperty().set(Speed.HUNDRED);
                        break;
                }
            }
        });
        flow.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            if (newValue != null) {
                ToggleButton selected = (ToggleButton) newValue;
                switch (selected.getId()) {
                    case "fiveF":
                        person.flowProperty().set(Flow.FIVE);
                        break;
                    case "tenF":
                        person.flowProperty().set(Flow.TEN);
                        break;
                    case "hundredF":
                        person.flowProperty().set(Flow.HUNDRED);
                        break;
                    case "flat":
                        person.flowProperty().set(Flow.FLAT);
                        break;
                }
            }
        });
        duration.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            if (newValue != null) {
                ToggleButton selected = (ToggleButton) newValue;
                switch (selected.getId()) {
                    case "oneYear":
                        person.durationProperty().set(Duration.ONE);
                        break;
                    case "twoYear":
                        person.durationProperty().set(Duration.TWO);
                        break;
                }
            }
        });
        firstName.textProperty().bindBidirectional(person.firstNameProperty());
        lastName.textProperty().bindBidirectional(person.lastNameProperty());
        address.textProperty().bindBidirectional(person.addressProperty());

    }

    @FXML
    private int savePerson() {
        if (person.isValid()) {
            persons = table.getItems();
            ToggleButton selectedspeed = (ToggleButton) speed.getSelectedToggle();
            ToggleButton selectedflow = (ToggleButton) flow.getSelectedToggle();
            ToggleButton selectedduration = (ToggleButton) duration.getSelectedToggle();

            int newId;
            persons.add(new Person(person.getSpeed(), person.getFlow(), person.getDuration(), person.getNumberIdentification(), firstName.getText(), lastName.getText(), address.getText()));
            newId = person.getNumberIdentification() + 1;
            person.setNumberIdentification(newId);
            table.setItems(persons);
        } else {
            StringBuilder errMsg = new StringBuilder();

            ArrayList<String> errList = person.errorsProperty().get();
            errList.forEach((errList1) -> {
                errMsg.append(errList1);
            });

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Person can not  save !");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();
        }
        return 0;
    }
    
    @FXML
    private void clearPerson() {
        person.speedProperty().set(Speed.TWO);
        person.flowProperty().set(Flow.ONE);
        person.durationProperty().set(Duration.ONE);
        person.firstNameProperty().set("");
        person.lastNameProperty().set("");
        person.addressProperty().set("");
        

        if (speed.getSelectedToggle() != null) {
            speed.getSelectedToggle().setSelected(false);
        }
        if (flow.getSelectedToggle() != null) {
            flow.getSelectedToggle().setSelected(false);
        }
        if (duration.getSelectedToggle() != null) {
            duration.getSelectedToggle().setSelected(false);
        }
    }
   
    @FXML
    private void deletePerson() {
        persons = table.getItems();
        int index = table.selectionModelProperty().getValue().getSelectedIndex();
        persons.remove(index);
        table.setItems(persons);
    }
     
}
