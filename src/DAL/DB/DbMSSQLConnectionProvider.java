/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.DB;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author: Carlo De Leon
 * Version: 1.0
 */
public class DbMSSQLConnectionProvider implements DAL.DB.IINTERFACE.IDbConnectionProvider {

    protected String host;
    protected String user;
    protected String password;
    protected String database;
    protected int port = 1433;
    private SQLServerDataSource ds;

    public DbMSSQLConnectionProvider() {
    }

    /**
     * Connect to the database.
     */
    public void connect() {
        ds = new SQLServerDataSource();
        ds.setServerName(getHost());
        ds.setDatabaseName(getDatabase());
        ds.setUser(getUser());
        ds.setPassword(getPassword());
        ds.setPortNumber(getPort());
    }

    /**
     * Get the current connection.
     *
     * @return The current Connection instance.
     */
    @Override
    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Get the database source.
     *
     * @return the database source.
     */
    public SQLServerDataSource getDataSource() {
        return ds;
    }

    /**
     * Get the database name.
     *
     * @return The database name.
     */
    @Override
    public String getDatabase() {
        return database;
    }

    /**
     * Get the host.
     *
     * @return The host.
     */
    @Override
    public String getHost() {
        return host;
    }

    /**
     * Gets the user.
     *
     * @return The user.
     */
    @Override
    public String getUser() {
        return user;
    }

    /**
     * Get the password.
     *
     * @return The password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Get the port.
     *
     * @return The port.
     */
    @Override
    public int getPort() {
        return port;
    }

    /**
     * Set the database name.
     *
     * @param database The database name to connect to.
     */
    @Override
    public void setDatabase(String database) {
        if (database.isEmpty()) return;
        this.database = database;
    }

    /**
     * Set the host.
     *
     * @param host The host.
     */
    @Override
    public void setHost(String host) {
        if (host.isEmpty()) return;
        this.host = host;
    }

    /**
     * Set the user for the database.
     *
     * @param user The user for the database.
     */
    @Override
    public void setUser(String user) {
        if (user.isEmpty()) return;
        this.user = user;
    }

    /**
     * Set the password for the database's user.
     *
     * @param password The password for the user.
     */
    @Override
    public void setPassword(String password) {
        if (password.isEmpty()) return;
        this.password = password;
    }

    /**
     * Set the port for the database.
     *
     * @param port The database port.
     */
    @Override
    public void setPort(int port) {
        if (port <= 0) return;
        this.port = port;
    }

}
