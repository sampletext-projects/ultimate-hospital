package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Enter {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enretButton;

    @FXML
    private TextField siginlogin;

    @FXML
    private TextField siginpass;

    @FXML
    private CheckBox Postadmin;

    @FXML
    private CheckBox Postdoctor;

    @FXML
    private CheckBox Postpatirnt;


    @FXML
    void initialize() {
        enretButton.setOnAction(event -> {
            String loginText = siginlogin.getText().trim();
            String loginPass = siginpass.getText().trim();
            if (!loginText.isEmpty() && !loginPass.equals("")){
                loginUser(loginText, loginPass);
            }
            else{
                System.out.println("login or password is empty");
            }
        });
    }

    private void loginUser(String loginText, String loginPass) {
        DatabaseHandler dbHandler =  new DatabaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPass(loginPass);
        if(Postadmin.isSelected() && !Postpatirnt.isSelected() && !Postdoctor.isSelected()) {
            ResultSet result1 = dbHandler.getUser(user, "admin");
            int counter = 0;
            while(true){
                try {
                    if (!result1.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                counter++;
            }
            if(counter >= 1){
                enretButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/admin.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        }
        else if (Postdoctor.isSelected() && !Postpatirnt.isSelected() && !Postadmin.isSelected()) {
                ResultSet result2 = dbHandler.getUser(user, "doctor");
                int counter = 0;
                while(true){
                    try {
                        if (!result2.next()) break;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    counter++;
                }
                if(counter >= 1){
                    System.out.println("Su!");
                }
            }
        else if (Postpatirnt.isSelected() && !Postadmin.isSelected() && !Postdoctor.isSelected()){
            ResultSet result3 = dbHandler.getUser(user, "patient");
            int counter = 0;
            while(true){
                try {
                    if (!result3.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                counter++;
            }
            if(counter >= 1){
                System.out.println("Su!");
            }
        }
    }
}

