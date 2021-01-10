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
import models.Patient;
import utils.FileUtils;

import java.util.List;

public class DoctorMainScreenController {

    @FXML
    private TableView<Patient> tableViewTime;

    @FXML
    private TableColumn<Patient, String> tableColumnName;

    @FXML
    private TableColumn<Patient, String> card;

    @FXML
    private TableColumn<Patient, String> time;

    @FXML
    private TableColumn<Patient, String> data;

    @FXML
    private TextArea textAreaCard;

    @FXML
    private Button buttonShow;

    @FXML
    private Button buttonSave;

    @FXML
    public void initialize() {
        tableColumnName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        card.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNumKart()));
        time.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTime()));
        data.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getData()));

        List<Patient> patients = DatabaseHandler.getPatients();

        tableViewTime.setItems(FXCollections.observableArrayList(patients));
        tableViewTime.refresh();

        FileUtils.writeFile("src/data.txt", "192.168.1.1");
    }

    public void onButtonSaveClick(ActionEvent actionEvent) {

        String text = textAreaCard.getText();
        FileUtils.writeFile("src/card.txt", text);

        for (int i = 0; i < 10; i++) {
        }

        switch ("") {
        }
    }

    public void onButtonShowClick(ActionEvent actionEvent) {
        String text = FileUtils.readFile("src/card.txt");
        textAreaCard.setText(text);
    }
}


