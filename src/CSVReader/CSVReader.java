package CSVReader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import javax.print.DocFlavor;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static void main(String[] args) {
        try {
            CSVReader r = new CSVReader();
            r.readFile("src/CSVReader/greeenhouse_wps_impacted_integrations.csv");
        }
        catch (IOException ex){
            System.out.println("Error Message " + ex.getMessage());
        }
    }

    public List<GroupIdSchemaName> readFile(String path) throws IOException {
        String input = "group_id,schema_name\n" +
                "1k56c2c4xlti6,greenhouse_internaltesting\n" +
                "1k56c2c4xlti6,greenhouse_123\n";
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        final CsvMapper csvMapper = new CsvMapper();
        csvMapper.configure(CsvParser.Feature.FAIL_ON_MISSING_COLUMNS, true);
        csvMapper.configure(CsvParser.Feature.TRIM_SPACES, true);
        ObjectReader oReader = csvMapper.reader(GroupIdSchemaName.class).with(schema);
        List<GroupIdSchemaName> list = new ArrayList<>();

        MappingIterator<GroupIdSchemaName> mi = oReader.readValues(input);
        while (mi.hasNext()) {
            GroupIdSchemaName current = mi.next();
            list.add(current);
            System.out.println(current);
        }

        return list;
    }
}

class GroupIdSchemaName{
    public String group_id;
    public String schema_name;

    public GroupIdSchemaName(String group_id, String schema_name){
        this.group_id = group_id;
        this.schema_name = schema_name;
    }

    public GroupIdSchemaName(){

    }
    @Override
    public String toString(){
        return "group_id "+ group_id + " schema_name " + schema_name;
    }
}
