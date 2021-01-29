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

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        String connectionString = String.format(
                "jdbc:mysql://%s:%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                Configs.dbHost,
                Configs.dbPort,
                Configs.dbName);
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionString, Configs.dbUser, Configs.dbPass);
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
                statement.getConnection().close();
                return admin;
            }
            statement.getConnection().close();
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
                statement.getConnection().close();
                return doctor;
            }
            statement.getConnection().close();
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
            statement.getConnection().close();
            return doctors;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Patient getPatientByLoginAndPassword(String login, String password) {
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
                statement.getConnection().close();
                return patient;
            }
            statement.getConnection().close();
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
            statement.getConnection().close();
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
            statement.setString(3, admin.getPassword());
            statement.execute();
            statement.getConnection().close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addPatient(Patient patient) {
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
            statement.setString(3, patient.getPassword());
            statement.setString(4, patient.getNumKart());
            statement.execute();
            statement.getConnection().close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removePatient(Patient patient) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM " + Const.PATIENT_TABLE + " WHERE id = ?");
            statement.setString(1, patient.getId());
            statement.execute();
            statement.getConnection().close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void updatePatient(Patient patient) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(
                    String.format("UPDATE %s SET %s=? %s=? %s=? %s=? %s=? %s=? WHERE id=?",
                            Const.PATIENT_TABLE, Const.PATIENT_NAME, Const.PATIENT_LOGIN, Const.PATIENT_PASS, Const.PATIENT_DATA, Const.PATIENT_TIME, Const.PATIENT_NUMKART)
            );
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getLogin());
            statement.setString(3, patient.getPassword());
            statement.setString(3, patient.getData());
            statement.setString(3, patient.getTime());
            statement.setString(3, patient.getNumKart());
            statement.execute();
            statement.getConnection().close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void addDoctor(Doctor doctor) {
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
            statement.setString(3, doctor.getPassword());
            statement.setString(4, doctor.getTime());
            statement.setString(5, doctor.getData());
            statement.execute();
            statement.getConnection().close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removeDoctor(Doctor doctor) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM " + Const.DOCTOR_TABLE + " WHERE id = ?");
            statement.setString(1, doctor.getId());
            statement.execute();
            statement.getConnection().close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateDoctor(Doctor doctor) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(
                    String.format("UPDATE %s SET %s=? %s=? %s=? %s=? %s=? WHERE id=?",
                            Const.DOCTOR_TABLE, Const.DOCTOR_NAME, Const.DOCTOR_LOGIN, Const.DOCTOR_PASS, Const.DOCTOR_DATA, Const.DOCTOR_TIME)
            );
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getLogin());
            statement.setString(3, doctor.getPassword());
            statement.setString(4, doctor.getData());
            statement.setString(5, doctor.getTime());
            statement.setString(6, doctor.getId());
            statement.execute();
            statement.getConnection().close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
