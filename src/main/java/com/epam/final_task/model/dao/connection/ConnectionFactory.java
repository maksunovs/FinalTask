package com.epam.final_task.model.dao.connection;

import com.epam.final_task.model.dao.exception.ConnectionException;
import com.epam.final_task.util.PropertyLoader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String PROPERTIES_PATH = "database.properties";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String url;
    private final String user;
    private final String password;

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    public ConnectionFactory(PropertyLoader propertyLoader) throws ConnectionException {
        try {
            Class.forName(DRIVER);
            Properties properties = propertyLoader.load(PROPERTIES_PATH);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new ConnectionException("Class com.mysql.cj.jdbc.Driver not found", e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new ConnectionException("Failed to load properties", e);
        }
    }

    public Connection createConnection() throws ConnectionException {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new ConnectionException("Connection creating error", e);
        }
    }
}
