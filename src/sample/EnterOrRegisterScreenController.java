package sample;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EnterOrRegisterScreenController {

    @FXML
    private Button buttonEnter;

    @FXML
    private Button buttonRegister;

    public void onButtonRegisterClick(ActionEvent actionEvent) {
        buttonRegister.getScene().getWindow().hide();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/RegisterScreen.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onButtonEnterClick(ActionEvent actionEvent) {
        buttonEnter.getScene().getWindow().hide();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/EnterScreen.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
