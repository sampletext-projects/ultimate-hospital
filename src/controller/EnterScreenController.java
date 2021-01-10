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
            System.out.println("login or password is empty");
            return;
        }

        if (checkBoxIsAdmin.isSelected()) {
            Admin admin = DatabaseHandler.getAdmin(login, password);
            if (admin == null) {
                System.out.println("Admin not found");
                return;
            }

            // Logined Admin

            Utils.loadScreen("AdminMainScreen");
        }
        else if (checkBoxIsDoctor.isSelected()) {
            Doctor doctor = DatabaseHandler.getDoctor(login, password);
            if (doctor == null) {
                System.out.println("Doctor not found");
                return;
            }

            // Logined Doctor

            Utils.loadScreen("DoctorMainScreen");
        }

        else if (checkBoxIsPatient.isSelected()) {
            Patient patient = DatabaseHandler.getPatient(login, password);
            if (patient == null) {
                System.out.println("Patient not found");
                return;
            }

            // Logined Patient

            Utils.loadScreen("PatientMainScreen");
        }
    }


    public void onButtonBackClick(ActionEvent actionEvent) {
        Utils.backScreen();
    }
}

