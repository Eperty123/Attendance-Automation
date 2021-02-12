package DAL.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Author: Carlo De Leon
 * Version: 1.1
 */
public class DbMysqlConnectionProvider implements DAL.DB.IINTERFACE.IDbConnectionProvider {

    protected String host;
    protected String user;
    protected String password;
    protected String database;
    protected int port = 3306;

    protected Connection connection;

    public DbMysqlConnectionProvider() {
    }

    /**
     * Connect to the database.
     */
    @Override
    public void connect() {
        try {
            // Connect to the database.
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", getHost(), getPort(), getDatabase()), getUser(), getPassword());
        } catch (SQLException e) {
            System.out.println(String.format("MySQL connect exception: %s", e.getMessage()));
        }
    }


    /**
     * Get the current connection.
     *
     * @return The current connection.
     */
    @Override
    public Connection getConnection() {
        return connection;
    }

    /**
     * Get the databse name.
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
     * Get the database's user.
     *
     * @return The database's user.
     */
    @Override
    public String getUser() {
        return user;
    }

    /**
     * Get the database user password.
     *
     * @return The database user's password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Get the port of the database.
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
     * Set the host for the database.
     *
     * @param host The host for the database.
     */
    @Override
    public void setHost(String host) {
        if (host.isEmpty()) return;
        this.host = host;
    }

    /**
     * Set the database user.
     *
     * @param user The database user.
     */
    @Override
    public void setUser(String user) {
        if (user.isEmpty()) return;
        this.user = user;
    }

    /**
     * Set the database user's password.
     *
     * @param password The database user's password.
     */
    @Override
    public void setPassword(String password) {
        if (password.isEmpty()) return;
        this.password = password;
    }

    /**
     * Set the database port.
     *
     * @param port The database port.
     */
    @Override
    public void setPort(int port) {
        if (port <= 0) return;
        this.port = port;
    }
}
