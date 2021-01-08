package controller;

import javafx.beans.property.SimpleStringProperty;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import models.Doctor;


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
    }
}
