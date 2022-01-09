package edu.java.course.core.task_03;

import org.postgresql.ds.PGSimpleDataSource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

public class TestDatabase {

    private static final PostgreSQLContainer postgres = (new PostgreSQLContainer(DockerImageName.parse("postgres:latest")))
            .withDatabaseName("test-postgres")
            .withUsername("test-postgres-user")
            .withPassword("31291205");
    private static final DbConfig dbConfig;

    static {
        postgres.start();
        dbConfig = new DbConfig(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
        DbMigrationUtils.migrate(dbConfig);
    }

    public static DbConfig getDbConfig() {
        return dbConfig;
    }

    public static DataSource getDataSource() {
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser(dbConfig.getDbUser());
        dataSource.setPassword(dbConfig.getDbPassword());
        dataSource.setUrl(dbConfig.getConnectionUrl());
        return dataSource;
    }
}
