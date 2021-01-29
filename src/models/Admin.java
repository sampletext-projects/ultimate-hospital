package models;

import java.sql.ResultSet;

public class Admin extends User {

    public static Admin getLogined() {
        return User.getLogined();
    }

    @Override
    public void readFromResultSet(ResultSet resultSet) {
        super.readFromResultSet(resultSet);
    }
}
