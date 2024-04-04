package PostgreSQLPreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostgreTest {
    public static  void main(String... args) throws SQLException, ClassNotFoundException {
        //System.out.println("GOOGLE_APPLICATION_CREDENTIALS : " + System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
        //DBAbstract postgre = new Postgre(HikariConnectionPool.createConnectionPool());
        HikariConnectionPool.createConnection();


        //CreateTable(postgre);
        //RetrieveTest(postgre);
        //InsertTest(postgre);
        //CreatePlanBTable(postgre);
    }



    public static void CreatePlanBTable(Postgre postgre) throws  SQLException{
        String tableName = "connector_upload_planb";
        String createStmt = String.format("CREATE TABLE IF NOT EXISTS %s "
                        + "( "
                        + "group_id TEXT NOT NULL, "
                        + "schema_name TEXT NOT NULL, "
                        + "last_upload_time TIMESTAMP NOT NULL, "
                        + "PRIMARY KEY (group_id, schema_name, last_upload_time)"
                        + " );"
                , tableName);
        postgre.createTable(createStmt);
    }

    public static void CreateTable(Postgre postgre) throws SQLException {
        String tableName = "connector_upload";
        String createStmt = String.format("CREATE TABLE IF NOT EXISTS %s "
                + "( "
                + "group_id TEXT NOT NULL, "
                + "schema_name TEXT NOT NULL, "
                + "upload_id TEXT UNIQUE NOT NULL, "
                + "is_flushed BOOLEAN NOT NULL, "
                + "upload_time TIMESTAMP NOT NULL, "
                + "PRIMARY KEY (group_id, schema_name, upload_id)"
                + " );"
                , tableName);
        postgre.createTable(createStmt);
    }

    public static void RetrieveTest(DBAbstract postgre) throws SQLException {
        String tableName = "accounts";
        String condition = "vote_id > 1";
        List<String > columns  = new ArrayList<>();
        columns.add("vote_id");
        columns.add("candidate");

        List<Map<String, String>> results = postgre.retrieveRecord(tableName, columns, condition );
        results.forEach(
                record -> {
                    record.forEach(
                            (col, value) -> {
                                System.out.println("Key : " + col + " Value : " + value);
                            }
                    );
                }
        );
    }
}
