package controller;

import java.net.URL;
import java.util.ResourceBundle;

import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import models.Admin;
import models.Doctor;
import models.Patient;
import utils.Utils;

public class RegisterScreenController {

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
        String login = textFieldLogin.getText();
        String name = textFieldName.getText();
        String password = textFieldPassword.getText();

        // CHECK DATA

        if (checkBoxIsAdmin.isSelected()) {
            Admin a = new Admin();
            a.setLogin(login);
            a.setName(name);
            a.setPass(password);
            DatabaseHandler.addAdmin(a);
        } else if (checkBoxIsPatient.isSelected()) {
            Patient p = new Patient();
            p.setLogin(login);
            p.setName(name);
            p.setPass(password);
            DatabaseHandler.addPatient(p);
        } else if (checkBoxIsDoctor.isSelected()) {
            Doctor d = new Doctor();
            d.setLogin(login);
            d.setName(name);
            d.setPass(password);
            DatabaseHandler.addDoctor(d);
        }

        Utils.alertAndWait("Успех", "Операция выполнена", "Регистрация прошла успешно");
        Utils.backScreen();
    }
}
