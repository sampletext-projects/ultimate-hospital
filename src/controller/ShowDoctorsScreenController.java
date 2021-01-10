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
import models.Doctor;

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
    private TextField txt_id;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_login;

    @FXML
    private TextField txt_pass;

    @FXML
    private TextField txt_data;

    @FXML
    private TextField txt_time;

    @FXML
    public void initialize() {
        id.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId()));
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        login.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLogin()));
        pass.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPass()));
        time.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTime()));
        data.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getData()));

        List<Doctor> doctors = DatabaseHandler.getDoctors();

        tableViewDoctors.setItems(FXCollections.observableArrayList(doctors));
        tableViewDoctors.refresh();
    }

    public void Add_doctors(ActionEvent actionEvent) {
        String name = txt_name.getText();
        String login = txt_login.getText();
        String password = txt_pass.getText();
        String data = txt_data.getText();
        String time = txt_time.getText();

        Doctor d = new Doctor();
        d.setLogin(login);
        d.setName(name);
        d.setPass(password);
        d.setData(data);
        d.setTime(time);
        DatabaseHandler.addDoctors(d);

        List<Doctor> doctors = DatabaseHandler.getDoctors();

        tableViewDoctors.setItems(FXCollections.observableArrayList(doctors));
        tableViewDoctors.refresh();
    }

    public void Delete_doctors(ActionEvent actionEvent) {
        String id = txt_id.getText();
        DatabaseHandler.removeDoctor(id);

        List<Doctor> doctors = DatabaseHandler.getDoctors();

        tableViewDoctors.setItems(FXCollections.observableArrayList(doctors));
        tableViewDoctors.refresh();

    }

    public void Edit_doctors(ActionEvent actionEvent) {
        String data = txt_data.getText();
        String time = txt_time.getText();
        String id = txt_id.getText();
        DatabaseHandler.updateDoctor(data, time, id);

        List<Doctor> doctors = DatabaseHandler.getDoctors();

        tableViewDoctors.setItems(FXCollections.observableArrayList(doctors));
        tableViewDoctors.refresh();
    }
}
