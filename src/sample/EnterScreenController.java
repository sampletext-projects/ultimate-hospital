package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Admin;
import models.Doctor;
import models.Patient;

import java.io.IOException;

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

        DatabaseHandler dbHandler = new DatabaseHandler();

        if (checkBoxIsAdmin.isSelected()) {
            Admin admin = dbHandler.getAdmin(login, password);
            if (admin == null) {
                System.out.println("Admin not found");
                return;
            }

            // Logined Admin
            buttonEnter.getScene().getWindow().hide();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/sample/AdminScreen0.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (checkBoxIsDoctor.isSelected()) {
            Doctor doctor = dbHandler.getDoctor(login, password);
            if (doctor == null) {
                System.out.println("Doctor not found");
                return;
            }

            // Logined Doctor
        }

        if (checkBoxIsPatient.isSelected()) {
            Patient patient = dbHandler.getPatient(login, password);
            if (patient == null) {
                System.out.println("Patient not found");
                return;
            }

            // Logined Patient
        }
    }
}

