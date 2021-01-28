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
import models.Patient;

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
    private TextField textFieldId;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPass;

    @FXML
    private TextField textFieldCard;

    @FXML
    private TextField textFieldData;

    @FXML
    private TextField textFieldTime;

    @FXML
    public void initialize() {
        tableColumnId.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId()));
        tableColumnName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        tableColumnLogin.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLogin()));
        tableColumnPassword.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPass()));
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
        String password = textFieldPass.getText();
        String numKart = textFieldCard.getText();

        Patient p = new Patient();
        p.setLogin(login);
        p.setName(name);
        p.setPass(password);
        p.setNumKart(numKart);
        DatabaseHandler.addPatients(p);

        List<Patient> patients = DatabaseHandler.getPatients();

        tableViewPatients.setItems(FXCollections.observableArrayList(patients));
        tableViewPatients.refresh();
    }

    public void deletePatient(ActionEvent actionEvent) {
        String id = textFieldId.getText();
        DatabaseHandler.removePatient(id);

        List<Patient> patients = DatabaseHandler.getPatients();

        tableViewPatients.setItems(FXCollections.observableArrayList(patients));
        tableViewPatients.refresh();

    }

    public void editPatient(ActionEvent actionEvent) {
        String numKart = textFieldCard.getText();
        String id = textFieldId.getText();
        DatabaseHandler.updatePatient(numKart, id);

        List<Patient> patients = DatabaseHandler.getPatients();

        tableViewPatients.setItems(FXCollections.observableArrayList(patients));
        tableViewPatients.refresh();
    }
}

