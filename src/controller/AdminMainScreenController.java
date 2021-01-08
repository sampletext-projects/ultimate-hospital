package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import utils.Utils;

public class AdminMainScreenController {

    @FXML
    private Button buttonShowDoctors;

    @FXML
    private Button buttonShowPatients;

    public void onButtonShowDoctorsClick(ActionEvent actionEvent) {
        Utils.loadScreen("ShowDoctorsScreen");
    }

    public void onButtonShowPatientsClick(ActionEvent actionEvent) {
        Utils.loadScreen("ShowPatientsScreen");
    }
}
