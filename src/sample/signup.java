package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

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

public class signup {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signupname;

    @FXML
    private TextField signuplogin;

    @FXML
    private TextField signuppass;

    @FXML
    private CheckBox postadmin;

    @FXML
    private CheckBox postpatient;

    @FXML
    private CheckBox postdoctor;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        signUpButton.setOnAction(event -> {
            String post = "";
            if(postadmin.isSelected()) post = "admin";
            else if (!postpatient.isSelected()) {
                if (postdoctor.isSelected()) post = "doctor";
            } else post = "patient";

            dbHandler.SingUpUser(signupname.getText(), signuplogin.getText(), signuppass.getText(), post);
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
