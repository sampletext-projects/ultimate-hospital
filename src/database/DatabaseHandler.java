package database;

import models.Admin;
import models.Doctor;
import models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    private static Connection connection;

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        String connectionString = String.format("jdbc:mysql://%s:%s/%s", Configs.dbHost, Configs.dbPort, Configs.dbName);
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionString, Configs.dbUser, Configs.dbPass);
        return connection;
    }

    public static Admin getAdmin(String login, String password) {
        String table = Const.ADMIN_TABLE;
        String loginColumn = Const.ADMIN_LOGIN;
        String passwordColumn = Const.ADMIN_PASS;

        String select = String.format("SELECT * FROM %s WHERE %s=? AND %s=? LIMIT 1", table, loginColumn, passwordColumn);

        try {
            PreparedStatement statement = getConnection().prepareStatement(select);
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

    public static Doctor getDoctor(String login, String password) {
        String table = Const.DOCTOR_TABLE;
        String loginColumn = Const.DOCTOR_LOGIN;
        String passwordColumn = Const.DOCTOR_PASS;

        String select = String.format("SELECT * FROM %s WHERE %s=? AND %s=? LIMIT 1", table, loginColumn, passwordColumn);

        try {
            PreparedStatement statement = getConnection().prepareStatement(select);
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

    public static List<Doctor> getDoctors() {
        String select = String.format("SELECT * FROM %s", Const.DOCTOR_TABLE);

        try {
            PreparedStatement statement = getConnection().prepareStatement(select);
            ResultSet resultSet = statement.executeQuery();
            List<Doctor> doctors = new ArrayList<>();
            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.readFromResultSet(resultSet);
                doctors.add(doctor);
            }
            return doctors;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Patient getPatient(String login, String password) {
        String table = Const.PATIENT_TABLE;
        String loginColumn = Const.PATIENT_LOGIN;
        String passwordColumn = Const.PATIENT_PASS;

        String select = String.format("SELECT * FROM %s WHERE %s=? AND %s=? LIMIT 1", table, loginColumn, passwordColumn);

        try {
            PreparedStatement statement = getConnection().prepareStatement(select);
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

    public static void addAdmin(Admin admin) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(
                    String.format("INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)",
                            Const.ADMIN_TABLE,
                            Const.ADMIN_NAME,
                            Const.ADMIN_LOGIN,
                            Const.ADMIN_PASS)
            );
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getLogin());
            statement.setString(3, admin.getPass());
            statement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removeAdmin(Admin admin) {

    }

    public static void updateAdmin(Admin admin) {

    }


    public static void addPatient(Patient patient) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(
                    String.format("INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)",
                            Const.PATIENT_TABLE,
                            Const.PATIENT_NAME,
                            Const.PATIENT_LOGIN,
                            Const.PATIENT_PASS)
            );
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getLogin());
            statement.setString(3, patient.getPass());
            statement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removePatient(Patient patient) {

    }

    public static void updatePatient(Patient patient) {

    }

    public static void addDoctor(Doctor doctor) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(
                    String.format("INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)",
                            Const.DOCTOR_TABLE,
                            Const.DOCTOR_NAME,
                            Const.DOCTOR_LOGIN,
                            Const.DOCTOR_PASS)
            );
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getLogin());
            statement.setString(3, doctor.getPass());
            statement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removeDoctor(Doctor doctor) {

    }

    public static void updateDoctor(Doctor doctor) {

    }
}
