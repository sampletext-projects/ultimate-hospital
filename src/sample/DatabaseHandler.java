package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection()throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort +"/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void SingUpUser (String name, String login, String pass, String post){
        switch (post) {
            case "admin": {
                String insert = "INSERT INTO " + Const.ADMIN_TABLE + "(" + Const.ADMIN_NAME + "," + Const.ADMIN_LOGIN + "," + Const.ADMIN_PASS + ")" + "VALUES(?,?,?)";

                try {
                    PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                    prSt.setString(1, name);
                    prSt.setString(2, login);
                    prSt.setString(3, pass);
                    prSt.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;
            }
            case "doctor": {
                String insert = "INSERT INTO " + Const.DOCTOR_TABLE + "(" + Const.DOCTOR_NAME + "," + Const.DOCTOR_LOGIN + "," + Const.DOCTOR_PASS + ")" + "VALUES(?,?,?)";

                try {
                    PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                    prSt.setString(1, name);
                    prSt.setString(2, login);
                    prSt.setString(3, pass);
                    prSt.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "patient": {
                String insert = "INSERT INTO " + Const.PATIENT_TABLE + "(" + Const.PATIENT_NAME + "," + Const.PATIENT_LOGIN + "," + Const.PATIENT_PASS + ")" + "VALUES(?,?,?)";

                try {
                    PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                    prSt.setString(1, name);
                    prSt.setString(2, login);
                    prSt.setString(3, pass);
                    prSt.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    public ResultSet getUser (User user, String post) {
        ResultSet resSet = null;
        switch (post) {
            case "admin": {
                String select = "SELECT * FROM " + Const.ADMIN_TABLE + " WHERE " + Const.ADMIN_LOGIN + "=? AND " + Const.ADMIN_PASS + "=? ";

                try {
                    PreparedStatement prSt = getDbConnection().prepareStatement(select);
                    prSt.setString(1, user.getLogin());
                    prSt.setString(2, user.getPass());
                    resSet = prSt.executeQuery();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;
            }
            case "doctor": {
                String select = "SELECT * FROM " + Const.DOCTOR_TABLE + " WHERE " + Const.DOCTOR_LOGIN + "=? AND " + Const.DOCTOR_PASS + "=? ";

                try {
                    PreparedStatement prSt = getDbConnection().prepareStatement(select);
                    prSt.setString(1, user.getLogin());
                    prSt.setString(2, user.getPass());
                    resSet = prSt.executeQuery();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "patient": {
                String select = "SELECT * FROM " + Const.PATIENT_TABLE + " WHERE " + Const.PATIENT_LOGIN + "=? AND " + Const.PATIENT_PASS + "=? ";

                try {
                    PreparedStatement prSt = getDbConnection().prepareStatement(select);
                    prSt.setString(1, user.getLogin());
                    prSt.setString(2, user.getPass());
                    resSet = prSt.executeQuery();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }

        }
        return resSet;
    }
}
