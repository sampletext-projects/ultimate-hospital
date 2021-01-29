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
import models.Patient;
import utils.FileUtils;

import java.util.List;

public class PatientMainScreenController {

    @FXML
    private TableView<Doctor> tableViewDoctors;

    @FXML
    private TableColumn<Doctor, String> tableColumnName;

    @FXML
    private TableColumn<Doctor, String> tableColumnData;

    @FXML
    private TableColumn<Doctor, String> tableColumnTime;

    @FXML
    private TextField textFieldData;

    @FXML
    private TextField textFieldTime;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonCard;

    @FXML
    private TextArea textAreaCard;

    @FXML
    private TextField textFieldPass;

    @FXML
    public void initialize() {
        tableColumnName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        tableColumnTime.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTime()));
        tableColumnData.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getData()));

        List<Doctor> doctors = DatabaseHandler.getDoctors();

        tableViewDoctors.setItems(FXCollections.observableArrayList(doctors));
        tableViewDoctors.refresh();

        Patient logined = Patient.getLogined();
        textFieldPass.setText(logined.getPassword());
        textFieldData.setText(logined.getData());
        textFieldTime.setText(logined.getTime());

    }

    public void updateMe(ActionEvent actionEvent) {
        Patient logined = Patient.getLogined();
        logined.setPassword(textFieldPass.getText());
        logined.setData(textFieldData.getText());
        logined.setTime(textFieldTime.getText());
        DatabaseHandler.updatePatient(logined);
    }


    public void open(ActionEvent actionEvent) {
        String text = FileUtils.readFile("src/card.txt");
        textAreaCard.setText(text);
    }
}

