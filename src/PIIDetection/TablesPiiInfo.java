package PIIDetection;

import CustomAnnotation.PiiType;

import java.util.HashMap;
import java.util.Map;

public class TablesPiiInfo {
    public Map<String, TablePii> tables = new HashMap<>();
}

class TablePii{
    public Map<String, PiiType> columns = new HashMap<>();
}
