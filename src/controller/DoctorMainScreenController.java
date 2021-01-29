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
import utils.Utils;

import java.util.List;

public class DoctorMainScreenController {

    @FXML
    private TableView<Patient> tableViewTime;

    @FXML
    private TableColumn<Patient, String> tableColumnName;

    @FXML
    private TableColumn<Patient, String> tableColumnCard;

    @FXML
    private TableColumn<Patient, String> tableColumnTime;

    @FXML
    private TableColumn<Patient, String> tableColumnData;

    @FXML
    private TextArea textAreaCard;

    @FXML
    private Button buttonSee;

    @FXML
    private Button buttonSave;

    @FXML
    public void initialize() {
        tableColumnName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        tableColumnCard.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNumKart()));
        tableColumnTime.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTime()));
        tableColumnData.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getData()));

        List<Patient> patients = DatabaseHandler.getPatients();

        tableViewTime.setItems(FXCollections.observableArrayList(patients));
        tableViewTime.refresh();
    }

    public void onButtonShowClick(ActionEvent actionEvent) {
        try {
            String text = FileUtils.readFile("src/card.txt");
            textAreaCard.setText(text);
        } catch (Exception ignored) {
            Utils.alertAndWait("Ошибка", "Операция не выполнена", "Карта пациента не существует");
        }

    }

    public void onButtonSaveClick(ActionEvent actionEvent) {
        String text = textAreaCard.getText();
        FileUtils.writeFile("src/card.txt", text);
    }
}


