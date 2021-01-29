package controller;

import database.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Patient;
import utils.Utils;

import java.util.List;

public class ShowPatientsScreenController {

    @FXML
    private TableView<Patient> tableViewPatients;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonUpdate;

    @FXML
    private TableColumn<Patient, String> tableColumnId;

    @FXML
    private TableColumn<Patient, String> tableColumnName;

    @FXML
    private TableColumn<Patient, String> tableColumnLogin;

    @FXML
    private TableColumn<Patient, String> tableColumnPassword;

    @FXML
    private TableColumn<Patient, String> tableColumnCard;

    @FXML
    private TableColumn<Patient, String> tableColumnData;

    @FXML
    private TableColumn<Patient, String> tableColumnTime;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private TextField textFieldCard;

    @FXML
    private TextField textFieldData;

    @FXML
    private TextField textFieldTime;

    private Patient selectedPatient;

    @FXML
    public void initialize() {
        tableColumnId.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId()));
        tableColumnName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        tableColumnLogin.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLogin()));
        tableColumnPassword.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPassword()));
        tableColumnCard.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNumKart()));
        tableColumnTime.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTime()));
        tableColumnData.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getData()));

        List<Patient> patients = DatabaseHandler.getPatients();

        tableViewPatients.setItems(FXCollections.observableArrayList(patients));
        tableViewPatients.refresh();
    }

    public void addPatient(ActionEvent actionEvent) {
        String name = textFieldName.getText();
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();
        String numKart = textFieldCard.getText();


        if (name.trim().isEmpty() ||
                login.trim().isEmpty() ||
                password.trim().isEmpty() ||
                numKart.trim().isEmpty()) {
            Utils.alertAndWait("Ошибка", "Операция не выполнена", "Одно из полей не заполнено");
            return;
        }

        Patient p = new Patient();
        p.setLogin(login);
        p.setName(name);
        p.setPassword(password);
        p.setNumKart(numKart);
        DatabaseHandler.addPatient(p);

        List<Patient> patients = DatabaseHandler.getPatients();

        tableViewPatients.setItems(FXCollections.observableArrayList(patients));
        tableViewPatients.refresh();
    }

    public void deletePatient(ActionEvent actionEvent) {

        if (selectedPatient == null) {
            Utils.alertAndWait("Ошибка", "Операция не выполнена", "Пациент не выбран");
            return;
        }

        DatabaseHandler.removePatient(selectedPatient);

        List<Patient> patients = DatabaseHandler.getPatients();

        tableViewPatients.setItems(FXCollections.observableArrayList(patients));
        tableViewPatients.refresh();

    }

    public void editPatient(ActionEvent actionEvent) {

        if (selectedPatient == null) {
            Utils.alertAndWait("Ошибка", "Операция не выполнена", "Пациент не выбран");
            return;
        }

        selectedPatient.setName(textFieldName.getText());
        selectedPatient.setLogin(textFieldLogin.getText());
        selectedPatient.setPassword(textFieldPassword.getText());
        selectedPatient.setNumKart(textFieldCard.getText());
        selectedPatient.setTime(textFieldTime.getText());

        DatabaseHandler.updatePatient(selectedPatient);

        List<Patient> patients = DatabaseHandler.getPatients();

        tableViewPatients.setItems(FXCollections.observableArrayList(patients));
        tableViewPatients.refresh();
    }

    public void onTableViewMouseClicked(MouseEvent mouseEvent) {
        int selectedIndex = tableViewPatients.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            selectedPatient = null;
            return;
        }
        selectedPatient = tableViewPatients.getSelectionModel().getSelectedItem();

        textFieldName.setText(selectedPatient.getName());
        textFieldLogin.setText(selectedPatient.getLogin());
        textFieldPassword.setText(selectedPatient.getPassword());
        textFieldCard.setText(selectedPatient.getNumKart());
        textFieldTime.setText(selectedPatient.getTime());
    }
}

