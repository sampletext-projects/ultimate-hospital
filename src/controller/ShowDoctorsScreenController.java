package controller;

import database.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import models.Doctor;
import utils.Utils;

import java.util.List;


public class ShowDoctorsScreenController {

    @FXML
    private TableView<Doctor> tableViewDoctors;

    @FXML
    private TableColumn<Doctor, String> id;

    @FXML
    private TableColumn<Doctor, String> name;

    @FXML
    private TableColumn<Doctor, String> login;

    @FXML
    private TableColumn<Doctor, String> pass;

    @FXML
    private TableColumn<Doctor, String> time;

    @FXML
    private TableColumn<Doctor, String> data;

    @FXML
    private Button addbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private Button upbutton;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private TextField textFieldData;

    @FXML
    private TextField textFieldTime;

    Doctor selectedDoctor;

    @FXML
    public void initialize() {
        id.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId()));
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        login.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLogin()));
        pass.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPassword()));
        time.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTime()));
        data.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getData()));

        List<Doctor> doctors = DatabaseHandler.getDoctors();

        tableViewDoctors.setItems(FXCollections.observableArrayList(doctors));
        tableViewDoctors.refresh();
    }

    public void addDoctor(ActionEvent actionEvent) {
        String name = textFieldName.getText();
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();
        String data = textFieldData.getText();
        String time = textFieldTime.getText();

        if (name.trim().isEmpty() ||
                login.trim().isEmpty() ||
                password.trim().isEmpty() ||
                data.trim().isEmpty() ||
                time.trim().isEmpty()) {
            Utils.alertAndWait("Ошибка", "Операция не выполнена", "Одно из полей не заполнено");
            return;
        }

        Doctor d = new Doctor();
        d.setLogin(login);
        d.setName(name);
        d.setPassword(password);
        d.setData(data);
        d.setTime(time);
        DatabaseHandler.addDoctor(d);

        List<Doctor> doctors = DatabaseHandler.getDoctors();

        tableViewDoctors.setItems(FXCollections.observableArrayList(doctors));
        tableViewDoctors.refresh();
    }

    public void deleteDoctor(ActionEvent actionEvent) {
        if (selectedDoctor == null) {
            Utils.alertAndWait("Ошибка", "Операция не выполнена", "Доктор не выбран");
            return;
        }
        DatabaseHandler.removeDoctor(selectedDoctor);

        List<Doctor> doctors = DatabaseHandler.getDoctors();

        tableViewDoctors.setItems(FXCollections.observableArrayList(doctors));
        tableViewDoctors.refresh();

    }

    public void editDoctor(ActionEvent actionEvent) {
        if (selectedDoctor == null) {
            Utils.alertAndWait("Ошибка", "Операция не выполнена", "Доктор не выбран");
            return;
        }

        selectedDoctor.setName(textFieldName.getText());
        selectedDoctor.setLogin(textFieldLogin.getText());
        selectedDoctor.setPassword(textFieldPassword.getText());
        selectedDoctor.setTime(textFieldTime.getText());

        DatabaseHandler.updateDoctor(selectedDoctor);

        List<Doctor> doctors = DatabaseHandler.getDoctors();

        tableViewDoctors.setItems(FXCollections.observableArrayList(doctors));
        tableViewDoctors.refresh();
    }

    public void onTableViewMouseClicked(MouseEvent mouseEvent) {
        int selectedIndex = tableViewDoctors.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            selectedDoctor = null;
            return;
        }
        selectedDoctor = tableViewDoctors.getSelectionModel().getSelectedItem();

        textFieldName.setText(selectedDoctor.getName());
        textFieldLogin.setText(selectedDoctor.getLogin());
        textFieldPassword.setText(selectedDoctor.getPassword());
        textFieldTime.setText(selectedDoctor.getTime());
    }
}
