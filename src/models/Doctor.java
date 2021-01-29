package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor extends User {
    private String data;
    private String time;

    public Doctor(String id, String name, String login, String pass, String data) {
        super(id, name, login, pass);
        this.data = data;
    }

    public Doctor(String data, String time) {
        this.data = data;
        this.time = time;
    }

    public Doctor() {
    }

    public static Doctor getLogined() {
        return User.getLogined();
    }

    public Doctor(String data) {
        this.data = data;
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

    @Override
    public void readFromResultSet(ResultSet resultSet) {
        super.readFromResultSet(resultSet);
        try {
            setData(resultSet.getString("data"));
            setTime(resultSet.getString("time"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
