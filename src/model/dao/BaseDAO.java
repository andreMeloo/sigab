package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
    Connection connection = null;
    private String url = "jdbc:postgresql://localhost:5432/sigab";
    private String user = "postgres";
    private String senha = "postgres";

    public Connection getConnection(){
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, senha);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return connection;
    }
}
