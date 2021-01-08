package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EnterOrRegisterScreenController {

    @FXML
    private Button buttonEnter;

    @FXML
    private Button buttonRegister;

    public void onButtonRegisterClick(ActionEvent actionEvent) {

        Utils.loadScreen("RegisterScreen");
    }

    public void onButtonEnterClick(ActionEvent actionEvent) {

        Utils.loadScreen("EnterScreen");
    }
}
