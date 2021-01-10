package controller;

import database.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Doctor;

import java.util.List;

public class PatientMainScreenController {

    @FXML
    private TableView<Doctor> tableViewDoctors;

    @FXML
    private TableColumn<Doctor, String> name;

    @FXML
    private TableColumn<Doctor, String> data;

    @FXML
    private TableColumn<Doctor, String> time;

    @FXML
    private TextField textFieldData;

    @FXML
    private TextField textFieldTime;

    @FXML
    private Button UpButton;

    @FXML
    private Button cardButton;

    @FXML
    private TextArea textAreaCard;

    @FXML
    private TextField textFieldPass;

    @FXML
    public void initialize() {
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        time.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTime()));
        data.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getData()));

        List<Doctor> doctors = DatabaseHandler.getDoctors();

        tableViewDoctors.setItems(FXCollections.observableArrayList(doctors));
        tableViewDoctors.refresh();
    }

    public void Up_Me(ActionEvent actionEvent) {
        String password = textFieldPass.getText();
        String data = textFieldData.getText();
        String time = textFieldTime.getText();
        DatabaseHandler.updatePatients(data, time, password);

    }


    public void Open(ActionEvent actionEvent) {

    }
}

