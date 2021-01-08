package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUpButton;

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

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        signUpButton.setOnAction(event -> {
            String post = "";
            if(checkBoxIsAdmin.isSelected()) post = "admin";
            else if (!checkBoxIsPatient.isSelected()) {
                if (checkBoxIsDoctor.isSelected()) post = "doctor";
            } else post = "patient";

            dbHandler.SingUpUser(textFieldName.getText(), textFieldLogin.getText(), textFieldPassword.getText(), post);
            signUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/yes.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });


    }
}
