package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient extends User {
    private String data;
    private String time;
    private String numKart;

    public Patient(String id, String name, String login, String pass, String data, String time, String numKart) {
        super(id, name, login, pass);
        this.data = data;
        this.time = time;
        this.numKart = numKart;
    }

    public Patient(String data, String time, String numKart) {
        this.data = data;
        this.time = time;
        this.numKart = numKart;
    }

    public Patient() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumKart() {
        return numKart;
    }

    public void setNumKart(String numKart) {
        this.numKart = numKart;
    }

    @Override
    public void readFromResultSet(ResultSet resultSet) {
        super.readFromResultSet(resultSet);
        try {
            setData(resultSet.getString("data"));
            setTime(resultSet.getString("time"));
            setNumKart(resultSet.getString("numKart"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
