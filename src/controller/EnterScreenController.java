package controller;

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

public class EnterScreenController {

    @FXML
    private Button buttonEnter;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private CheckBox checkBoxIsAdmin;

    @FXML
    private CheckBox checkBoxIsDoctor;

    @FXML
    private CheckBox checkBoxIsPatient;

    public void onButtonEnterClick(ActionEvent actionEvent) {
        String login = textFieldLogin.getText().trim();
        String password = textFieldPassword.getText().trim();

        if (login.isEmpty() || password.isEmpty()) {
            Utils.alertAndWait("Ошибка", "Операция не выполнена", "Одно из полей не заполнено");
            return;
        }

        if (checkBoxIsAdmin.isSelected()) {
            Admin admin = DatabaseHandler.getAdmin(login, password);
            if (admin == null) {
                Utils.alertAndWait("Ошибка", "Операция не выполнена", "Админ не найден");
                return;
            }

            // Logined Admin

            Utils.loadScreen("AdminMainScreen");
            return;
        }

        if (checkBoxIsDoctor.isSelected()) {
            Doctor doctor = DatabaseHandler.getDoctor(login, password);
            if (doctor == null) {
                Utils.alertAndWait("Ошибка", "Операция не выполнена", "Доктор не найден");
                return;
            }

            // Logined Doctor

            Utils.loadScreen("DoctorMainScreen");
            return;
        }

        if (checkBoxIsPatient.isSelected()) {
            Patient patient = DatabaseHandler.getPatientByLoginAndPassword(login, password);
            if (patient == null) {
                Utils.alertAndWait("Ошибка", "Операция не выполнена", "Пациент не найден");
                return;
            }

            // Logined Patient

            Utils.loadScreen("PatientMainScreen");
            return;
        }
    }

    public void onButtonBackClick(ActionEvent actionEvent) {
        Utils.backScreen();
    }
}

