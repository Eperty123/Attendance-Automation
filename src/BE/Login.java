package BE;

public class Login {
    protected String username;
    protected String password;

    public Login() {
    }

    public Login(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    /**
     * Get the username.
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password.
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
