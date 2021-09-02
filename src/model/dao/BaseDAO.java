package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
    Connection connection = null;
    String url;
    String user;
    String senha;

    public Connection getConnection(){
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, senha);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }else
        return connection;
    }
}
