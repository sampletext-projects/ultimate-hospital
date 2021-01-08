package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String id;
    private String name;
    private String login;
    private String pass;

    public User(String id, String name, String login, String pass) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.pass = pass;
    }

    public User() {
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void readFromResultSet(ResultSet resultSet){
        try {
            this.setId(resultSet.getString("id"));
            this.setName(resultSet.getString("name"));
            this.setLogin(resultSet.getString("login"));
            this.setPass(resultSet.getString("pass"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
