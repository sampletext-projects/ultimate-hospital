package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.User;

import javax.swing.JOptionPane;

public class mysqlconnect {
    Connection conn = null;
    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/market", "root", "295Lub77");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static ObservableList<User> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from doctor ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new User(rs.getString("id"), rs.getString("name"),rs.getString("login"), rs.getString("pass"), rs.getString("time"), rs.getString("data")));
            }
        } catch (Exception e) {
        }
        return list;
    }

}
