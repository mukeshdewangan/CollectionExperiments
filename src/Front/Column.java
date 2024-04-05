package Front;

public class Column {
    public String name;
    public String dataType;
    public boolean primaryKey;

    public Column(String name, String dataType, boolean pk){
        this.name = name;
        this.dataType = dataType;
        this.primaryKey = pk;
    }
}
