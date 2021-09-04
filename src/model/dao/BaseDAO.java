package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
    Connection connection = null;
    String url = "jdbc:postgresql://localhost:5432/sigab";
    String user = "sigab";
    String senha = "123";

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
