package edu.java.course.core.task_03;

public class DbConfig {
    private final String connectionUrl;
    private final String dbUser;
    private final String dbPassword;

    public DbConfig(String connectionUrl, String dbUser, String dbPassword) {
        this.connectionUrl = connectionUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public String getConnectionUrl() {
        return this.connectionUrl;
    }

    public String getDbUser() {
        return this.dbUser;
    }

    public String getDbPassword() {
        return this.dbPassword;
    }
}
