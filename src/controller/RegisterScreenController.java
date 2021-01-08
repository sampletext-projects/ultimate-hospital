package controller;

import java.net.URL;
import java.util.ResourceBundle;

import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class RegisterScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonRegister;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private CheckBox checkBoxIsAdmin;

    @FXML
    private CheckBox checkBoxIsPatient;

    @FXML
    private CheckBox checkBoxIsDoctor;

    public void onButtonRegisterClick(ActionEvent actionEvent) {
        String post = "";
        if (checkBoxIsAdmin.isSelected()) {
            post = "admin";
        } else if (checkBoxIsPatient.isSelected()) {
            post = "patient";
        } else if (checkBoxIsDoctor.isSelected()) {
            post = "doctor";
        }


        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.SingUpUser(textFieldName.getText(), textFieldLogin.getText(), textFieldPassword.getText(), post);

        Utils.alertAndWait("Успех", "Операция выполнена", "Регистрация прошла успешно");
    }
}
