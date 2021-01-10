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
    private TableColumn<Patient, String> id;

    @FXML
    private TableColumn<Patient, String> name;

    @FXML
    private TableColumn<Patient, String> login;

    @FXML
    private TableColumn<Patient, String> pass;

    @FXML
    private TableColumn<Patient, String> card;

    @FXML
    private TableColumn<Patient, String> data;

    @FXML
    private TableColumn<Patient, String> time;

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
        id.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId()));
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        login.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLogin()));
        pass.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPass()));
        card.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNumKart()));
        time.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTime()));
        data.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getData()));

        List<Patient> patients = DatabaseHandler.getPatients();

        tableViewPatients.setItems(FXCollections.observableArrayList(patients));
        tableViewPatients.refresh();
    }

    public void onButtonAddClick(ActionEvent actionEvent) {
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

    public void onButtonDeleteClick(ActionEvent actionEvent) {
        String id = textFieldId.getText();
        DatabaseHandler.removePatient(id);

        List<Patient> patients = DatabaseHandler.getPatients();

        tableViewPatients.setItems(FXCollections.observableArrayList(patients));
        tableViewPatients.refresh();

    }

    public void onButtonEditClick(ActionEvent actionEvent) {
        int selectedIndex = tableViewPatients.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            Utils.alertAndWait("Error", "No rows selected", "Please select patient to edit");
            return;
        }

        Patient patient = tableViewPatients.getSelectionModel().getSelectedItem();
        String numKart = textFieldCard.getText();
        patient.setNumKart(numKart);

        DatabaseHandler.updatePatient(patient);
        tableViewPatients.refresh();
    }
}

