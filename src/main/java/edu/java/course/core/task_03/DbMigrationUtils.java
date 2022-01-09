package edu.java.course.core.task_03;

import org.flywaydb.core.Flyway;

public class DbMigrationUtils {

    public static void migrate(DbConfig dbConfig) {
        Flyway flyway = Flyway.configure().dataSource(dbConfig.getConnectionUrl(), dbConfig.getDbUser(), dbConfig.getDbPassword()).load();
        flyway.migrate();
    }
}
