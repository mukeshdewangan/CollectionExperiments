package PostgreSQLPreparedStatement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class DBAbstract{
    DataSource dataSource;

    DBAbstract(DataSource source) {
        dataSource = source;
    }

    // It is just executing statement and has nothing specific to create table operation
    public void createTable(String createStmt) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement createTableStatement = conn.prepareStatement(createStmt)) {
                createTableStatement.execute();
            }
            catch (SQLException ex ){
                ex.printStackTrace();
            }
        }
    }

    public void insertRecord(String tableName, Map<String, String> record) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String columns = record.keySet()
                    .stream()
                    .collect(Collectors.joining(", "));

            String values = record.values().stream().collect(Collectors.joining("', '"));
            String stmt = String.format(
                    "INSERT INTO " +
                            tableName + " ("
                            + columns
                            + ")"
                            + " values ( '%s' )", values );
            try (PreparedStatement insertStatement = conn.prepareStatement(stmt); ) {
                insertStatement.execute();
            }
        }
    }

    public List<Map<String, String>> retrieveRecord(String tableName, List<String> columns, String condition) throws SQLException {
        return retrieveRecord(tableName, columns, condition, true);
    }

    public List<Map<String, String>> retrieveRecord(String tableName, List<String> columns, String condition, boolean ascending) throws SQLException {
        List<Map<String, String>> records = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String cols = columns.stream().collect(Collectors.joining("', '"));

            String query = String.format( "SELECT %s FROM %s WHERE %s " + " ORDER BY candidate %s ;", cols, tableName, condition, ascending? "ASC" : "DESC" );

            try (ResultSet results =
                         conn.createStatement().executeQuery(query)) {
                while(results.next()) {
                    Map<String,String > record = new HashMap<>();
                    columns.forEach(x -> {
                            try {
                                record.put(x, results.getString(x));
                            }
                            catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    );
                    records.add(record);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            return  records;
        }
    }

    public void upsertRecord(String tableName, Map<String, String> record, List<String> uniqueColumns, String updateColumn) throws SQLException {
        throw new SQLException("Upsert not valid for this Database");
    }
}
