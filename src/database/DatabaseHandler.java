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
        String connectionString = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", Configs.dbHost, Configs.dbPort, Configs.dbName);
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

    public static List<Patient> getPatients() {
        String select = String.format("SELECT * FROM %s", Const.PATIENT_TABLE);

        try {
            PreparedStatement statement = getConnection().prepareStatement(select);
            ResultSet resultSet = statement.executeQuery();
            List<Patient> patients = new ArrayList<>();
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.readFromResultSet(resultSet);
                patients.add(patient);
            }
            return patients;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
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

    public static void addPatients(Patient patient) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(
                    String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?)",
                            Const.PATIENT_TABLE,
                            Const.PATIENT_NAME,
                            Const.PATIENT_LOGIN,
                            Const.PATIENT_PASS,
                            Const.PATIENT_NUMKART)
            );
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getLogin());
            statement.setString(3, patient.getPass());
            statement.setString(4, patient.getNumKart());
            statement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removePatient(String id) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM patient WHERE id = ?");
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void updatePatient(Patient patient) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(String.format("UPDATE %s SET numKart = ? WHERE id = ?", Const.PATIENT_TABLE));
            statement.setString(1, patient.getNumKart());
            statement.setString(2, patient.getId());
            statement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void updatePatient(String numKart, String id) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE patient SET numKart = ? WHERE id = ?");
            statement.setString(1, numKart);
            statement.setString(2, id);
            statement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void updatePatients(String data, String time, String pass) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE patient SET data = ?, time = ? WHERE pass = ?");
            statement.setString(1, data);
            statement.setString(2, time);
            statement.setString(3, pass);
            statement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

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

    public static void addDoctors(Doctor doctor) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(
                    String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?,?,?,?,?)",
                            Const.DOCTOR_TABLE,
                            Const.DOCTOR_NAME,
                            Const.DOCTOR_LOGIN,
                            Const.DOCTOR_PASS,
                            Const.DOCTOR_TIME,
                            Const.DOCTOR_DATA)
            );
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getLogin());
            statement.setString(3, doctor.getPass());
            statement.setString(4, doctor.getTime());
            statement.setString(5, doctor.getData());
            statement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removeDoctor(String id) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM doctor WHERE id = ?");
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateDoctor(String data, String time, String id) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE doctor SET data = ?, time = ? WHERE id = ?");
            statement.setString(1, data);
            statement.setString(2, time);
            statement.setString(3, id);
            statement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }
}
