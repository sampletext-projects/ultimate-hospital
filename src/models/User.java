package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private static User instance;

    private String id;
    private String name;
    private String login;
    private String password;

    public User(String id, String name, String login, String pass) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = pass;
    }

    public User() {
    }

    public static <T extends User> T getLogined() {
        return (T) instance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void readFromResultSet(ResultSet resultSet) {
        try {
            this.setId(resultSet.getString("id"));
            this.setName(resultSet.getString("name"));
            this.setLogin(resultSet.getString("login"));
            this.setPassword(resultSet.getString("password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
