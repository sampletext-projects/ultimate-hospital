package controller;

import database.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import models.Patient;

import java.util.List;

public class DoctorMainScreenController {

    @FXML
    private TableView<Patient> tableViewTime;

    @FXML
    private TableColumn<Patient, String> name;

    @FXML
    private TableColumn<Patient, String> card;

    @FXML
    private TableColumn<Patient, String> time;

    @FXML
    private TableColumn<Patient, String> data;

    @FXML
    private TextArea textAreaCard;

    @FXML
    private Button seeButton;

    @FXML
    private Button saveButton;

    @FXML
    public void initialize() {
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        card.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNumKart()));
        time.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTime()));
        data.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getData()));

        List<Patient> patients = DatabaseHandler.getPatients();

        tableViewTime.setItems(FXCollections.observableArrayList(patients));
        tableViewTime.refresh();
    }


}


