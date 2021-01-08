package models;

import java.sql.ResultSet;

public class Admin extends User {
    @Override
    public void readFromResultSet(ResultSet resultSet) {
        super.readFromResultSet(resultSet);
    }
}
