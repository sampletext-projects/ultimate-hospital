package database;

import models.Admin;
import models.Doctor;
import models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + Configs.dbHost + ":" + Configs.dbPort + "/" + Configs.dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, Configs.dbUser, Configs.dbPass);
        return dbConnection;
    }

    public void SingUpUser(String name, String login, String pass, String post) {
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

    public Admin getAdmin(String login, String password) {
        String table = Const.ADMIN_TABLE;
        String loginColumn = Const.ADMIN_LOGIN;
        String passwordColumn = Const.ADMIN_PASS;

        String select = String.format("SELECT * FROM %s WHERE %s=? AND %s=? LIMIT 1", table, loginColumn, passwordColumn);

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.readFromResultSet(resultSet);
                return admin;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Doctor getDoctor(String login, String password) {
        String table = Const.DOCTOR_TABLE;
        String loginColumn = Const.DOCTOR_LOGIN;
        String passwordColumn = Const.DOCTOR_PASS;

        String select = String.format("SELECT * FROM %s WHERE %s=? AND %s=? LIMIT 1", table, loginColumn, passwordColumn);

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.readFromResultSet(resultSet);
                return doctor;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Patient getPatient(String login, String password) {
        String table = Const.PATIENT_TABLE;
        String loginColumn = Const.PATIENT_LOGIN;
        String passwordColumn = Const.PATIENT_PASS;

        String select = String.format("SELECT * FROM %s WHERE %s=? AND %s=? LIMIT 1", table, loginColumn, passwordColumn);

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Patient patient = new Patient();
                patient.readFromResultSet(resultSet);
                return patient;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addAdmin(Admin admin) {

    }

    public void removeAdmin(Admin admin) {

    }

    public void updateAdmin(Admin admin) {

    }
}
