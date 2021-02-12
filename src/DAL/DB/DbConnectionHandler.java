package DAL.DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Author: Carlo De Leon
 * Version: 1.1
 */
public class DbConnectionHandler implements DAL.DB.IINTERFACE.IDbConnectionProvider {

    protected DbMSSQLConnectionProvider mssqlConnectionProvider;
    protected DbMysqlConnectionProvider mysqlConnectionProvider;
    protected int connectionType;

    protected Properties databaseProperties = new Properties();
    private static DbConnectionHandler instance;

    public static String databaseSettingsFile = "data/database.settings";

    /**
     * Initialize the class.
     */
    public DbConnectionHandler() {
        loadDbSettings();
    }

    /**
     * Load the database settings.
     * The file path is defined in the databaseSettingsFile variable.
     */
    protected void loadDbSettings() {
        try {
            databaseProperties = new Properties();
            databaseProperties.load(new FileInputStream(new File(databaseSettingsFile)));

            String server = databaseProperties.getProperty("Server");
            String database = databaseProperties.getProperty("Database");
            String user = databaseProperties.getProperty("User");
            String password = databaseProperties.getProperty("Password");
            String port = databaseProperties.getProperty("Port");
            connectionType = Integer.parseInt(databaseProperties.getProperty("ConnectionType"));

            switch (connectionType) {
                case 0 -> {
                    mssqlConnectionProvider = new DbMSSQLConnectionProvider();
                    mssqlConnectionProvider.setHost(server);
                    mssqlConnectionProvider.setDatabase(database);
                    mssqlConnectionProvider.setUser(user);
                    mssqlConnectionProvider.setPassword(password);
                    mssqlConnectionProvider.setPort(Integer.parseInt(port));
                    mssqlConnectionProvider.connect();
                }
                case 1 -> {
                    mysqlConnectionProvider = new DbMysqlConnectionProvider();
                    mysqlConnectionProvider.setHost(server);
                    mysqlConnectionProvider.setDatabase(database);
                    mysqlConnectionProvider.setUser(user);
                    mysqlConnectionProvider.setPassword(password);
                    mysqlConnectionProvider.setPort(Integer.parseInt(port));
                    mysqlConnectionProvider.connect();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reconnect to the database.
     */
    protected void reconnect() {
        switch (connectionType) {
            case 0:
                try {
                    if (mssqlConnectionProvider != null && mssqlConnectionProvider.getConnection().isClosed())
                        mssqlConnectionProvider.connect();
                    break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            case 1:
                try {
                    if (mysqlConnectionProvider != null && mysqlConnectionProvider.getConnection().isClosed())
                        mysqlConnectionProvider.connect();
                    break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }
    }

    /**
     * Get the database file path.
     *
     * @return The path of the database settings file.
     */
    public String getDatabaseSettingsFile() {
        return databaseSettingsFile;
    }

    /**
     * Set the file path of the database file.
     * This will be set for all instances including future ones.
     *
     * @param file the new filepath
     */
    public void setDatabaseSettingsFile(String file) {
        databaseSettingsFile = file;
    }

    /**
     * Get the current connection.
     *
     * @return The connection to the specified connection type.
     */
    @Override
    public Connection getConnection() {
        reconnect();
        return switch (connectionType) {
            case 0 -> mssqlConnectionProvider.getConnection();
            case 1 -> mysqlConnectionProvider.getConnection();
            default -> null;
        };
    }

    /**
     * Get the current database connection.
     *
     * @return The database name.
     */
    @Override
    public String getDatabase() {
        return switch (connectionType) {
            case 0 -> mssqlConnectionProvider.getDatabase();
            case 1 -> mysqlConnectionProvider.getDatabase();
            default -> null;
        };
    }

    /**
     * Get the host of the current database.
     *
     * @return Returns the specified host.
     */
    @Override
    public String getHost() {
        return switch (connectionType) {
            case 0 -> mssqlConnectionProvider.getHost();
            case 1 -> mysqlConnectionProvider.getHost();
            default -> null;
        };
    }

    /**
     * Get the user of the current database.
     *
     * @return Returns the specified user.
     */
    @Override
    public String getUser() {
        return switch (connectionType) {
            case 0 -> mssqlConnectionProvider.getUser();
            case 1 -> mysqlConnectionProvider.getUser();
            default -> null;
        };
    }

    /**
     * Get the password of the current database's user.
     *
     * @return Returns the specified database user's password.
     */
    @Override
    public String getPassword() {
        return switch (connectionType) {
            case 0 -> mssqlConnectionProvider.getPassword();
            case 1 -> mysqlConnectionProvider.getPassword();
            default -> null;
        };
    }

    /**
     * Get the port of the current database.
     *
     * @return Returns the specified port.
     */
    @Override
    public int getPort() {
        return switch (connectionType) {
            case 0 -> mssqlConnectionProvider.getPort();
            case 1 -> mysqlConnectionProvider.getPort();
            default -> 0;
        };
    }

    /**
     * Connect to the specified database type.
     */
    @Override
    public void connect() {
        switch (connectionType) {
            case 0 -> mssqlConnectionProvider.connect();
            case 1 -> mysqlConnectionProvider.connect();
        }
    }


    /**
     * Set the database name for the specified database type.
     *
     * @param database The database name to connect the specified database type to.
     */
    @Override
    public void setDatabase(String database) {
        switch (connectionType) {
            case 0 -> mssqlConnectionProvider.setDatabase(database);
            case 1 -> mysqlConnectionProvider.setDatabase(database);
        }
    }

    /**
     * Set the host for the specified database type.
     *
     * @param host the host for the specified database type.
     */
    @Override
    public void setHost(String host) {
        switch (connectionType) {
            case 0 -> mssqlConnectionProvider.setHost(host);
            case 1 -> mysqlConnectionProvider.setHost(host);
        }
    }

    /**
     * Set the user for the specified database type.
     *
     * @param user the user for the specified database type.
     */
    @Override
    public void setUser(String user) {
        switch (connectionType) {
            case 0 -> mssqlConnectionProvider.setUser(user);
            case 1 -> mysqlConnectionProvider.setUser(user);
        }
    }

    /**
     * Set the password for the specified database type's user.
     *
     * @param password the password for the specified database type's user.
     */
    @Override
    public void setPassword(String password) {
        switch (connectionType) {
            case 0 -> mssqlConnectionProvider.setPassword(password);
            case 1 -> mysqlConnectionProvider.setPassword(password);
        }
    }

    /**
     * Set the user for the specified database type.
     *
     * @param port the port for the specified database type.
     */
    @Override
    public void setPort(int port) {
        switch (connectionType) {
            case 0 -> mssqlConnectionProvider.setPort(port);
            case 1 -> mysqlConnectionProvider.setPort(port);
        }
    }

    /**
     * Get the current active DBConnectionHandler singleton instance.
     * If null a new instance will be instantiated.
     *
     * @return Returns the current active DBConnectionHandler instance.
     */
    public static DbConnectionHandler getInstance() {
        if (instance == null)
            instance = new DbConnectionHandler();
        return instance;
    }

    /**
     * Close the connection to the active database based on the connection type.
     */
    public void close() {
        try {
            switch (connectionType) {
                case 0 -> {
                    // Close open connection.
                    if (mssqlConnectionProvider.getConnection() != null)
                        mssqlConnectionProvider.getConnection().close();
                }
                case 1 -> {
                    // Close open connection.
                    if (mysqlConnectionProvider.getConnection() != null)
                        mysqlConnectionProvider.getConnection().close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * Get the connection type.
     *
     * @return The current connection type.
     */
    public int getConnectionType() {
        return connectionType;
    }

    /**
     * Set the connection type.
     *
     * @param type the connection type (0-1) - MSSQL and MySQL respectively.
     */
    public void setConnectionType(int type) {
        // Close any open connection.
        close();
        connectionType = type;
        connect();
    }
}
