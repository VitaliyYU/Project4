package ua.training.database;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by vitaliy on 23.05.17.
 */
public class ConnectionFactory {
    private static ConnectionFactory connectionFactory =new ConnectionFactory();
    private BasicDataSource connectionPool;

    public static  ConnectionFactory getInstance(){
        return connectionFactory;
    }

    private ConnectionFactory() {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("database");

           // Class.forName(resourceBundle.getString("DriverClassName"));
            connectionPool = new BasicDataSource();
            connectionPool.setDriverClassName(resourceBundle.getString("DriverClassName"));
            connectionPool.setUrl(resourceBundle.getString("url"));
            connectionPool.setUsername(resourceBundle.getString("Username"));
            connectionPool.setPassword(resourceBundle.getString("password"));
            connectionPool.setMinIdle(5);
            connectionPool.setMaxIdle(10);
            connectionPool.setMaxOpenPreparedStatements(100);
    }
    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

}
