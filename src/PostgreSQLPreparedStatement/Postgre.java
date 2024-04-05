package PostgreSQLPreparedStatement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Postgre extends DBAbstract {
    public Postgre(DataSource source) {
        super(source);
    }

    //Postgre specific implementation
    public void upsertRecord(String tableName, Map<String, String> record, List<String> uniqueColumns, String updateColumn) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String columns = record.keySet()
                    .stream()
                    .collect(Collectors.joining(", "));
            String uniqueCols = uniqueColumns.stream().collect(Collectors.joining(", "));

            String values = record.values().stream().collect(Collectors.joining("', '"));
            String stmt = String.format(
                    "INSERT INTO " +
                            tableName + " ("
                            + columns
                            + ")"
                            + " values ( '%s' )"
                            + " ON CONFLICT ( " +  uniqueCols + " ) DO UPDATE SET "+ updateColumn + "= EXCLUDED." + updateColumn + ";"
                    , values );
            try (PreparedStatement insertStatement = conn.prepareStatement(stmt); ) {
                insertStatement.execute();
            }
        }
    }
}
