package PostgreSQLPreparedStatement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HikariConnectionPool {
    private static String DB_USER = "postgres";
    private static String DB_PASS = "c<D>_=A%p%9f3VC~";
    private static String DB_NAME = "postgres";
    private static String INSTANCE = "mukesh-bhabha-testdb";
    private static String CLOUD_SQL_CONNECTION_NAME= "singular-vector-135519:asia-south1:mukesh-bhabha-testdb";
    public static DataSource createConnectionPool() {
        HikariConfig config = new HikariConfig();

        // Configure which instance and what database user to connect with.
        config.setJdbcUrl(String.format("jdbc:postgresql:///%s", DB_NAME));
        config.setUsername(DB_USER); // e.g. "root", "postgres"
        config.setPassword(DB_PASS);

        // For Java users, the Cloud SQL JDBC Socket Factory can provide authenticated connections.
        // See https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory for details.
        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.postgres.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", CLOUD_SQL_CONNECTION_NAME);

        config.setMaximumPoolSize(5);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(10000); // 10 seconds
        config.setIdleTimeout(600000); // 10 minutes
        config.setMaxLifetime(1800000); // 30 minutes

        DataSource pool = new HikariDataSource(config);
        return pool;
    }

    public static void createConnection() {
        // Database credentials
        String username = DB_USER;
        String password = DB_PASS;
        String instanceConnectionName = CLOUD_SQL_CONNECTION_NAME;// "your_project_id:your_region:your_instance_id";
        String databaseName = DB_NAME;

        // JDBC variables
        Connection connection = null;

        try {
            // Register the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Create JDBC URL
            String jdbcUrl = String.format("jdbc:postgresql://google/%s?socketFactory=com.google.cloud.sql.postgres.SocketFactory&cloudSqlInstance=%s", databaseName, instanceConnectionName);

            // Establish connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Check if connection is successful
            if (connection != null) {
                System.out.println("Connected to the Cloud SQL PostgreSQL server successfully.");

                // Perform database operations here...

                // Don't forget to close the connection when done
                connection.close();
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
