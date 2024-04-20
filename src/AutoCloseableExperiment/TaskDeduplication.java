package AutoCloseableExperiment;

import java.util.*;
import java.util.stream.Collectors;
public class TaskDeduplication {
    public static void main(String[] args) {
        List<DropConstraints> list = new ArrayList<>();
        DropConstraints constraint1 = new DropConstraints("obj1", "tab1", "col1");
        DropConstraints constraint2 = new DropConstraints("obj1", "tab1", "col1");
        DropConstraints constraint3 = new DropConstraints("obj1", "tab1", "col3");
        DropConstraints constraint4 = new DropConstraints("obj1", "tab1", "col4");
        list.add(constraint1);
        list.add(constraint2);
        list.add(constraint3);
        list.add(constraint4);

        List<Map<String, String>> uniqueList = list.stream()
                .map(t -> Map.of("object", t.object, "table", t.table, "column", t.column))
                .collect(Collectors.toList());
                //list.stream().distinct().toList();
        for ( Map<String, String> element : uniqueList)
            for (Map.Entry<String,String> entry : element.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
}

class DropConstraints {
    public String object;public String column;public String table;
    DropConstraints(String obj,String tab, String col){
        this.object = obj; this.table = tab;this.column = col;
    }
    @Override
    public String toString() {
        return this.object + " " + this.table + " " + this.column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        DropConstraints other = (DropConstraints) obj;
        return Objects.equals(object, other.object)
                && Objects.equals(table, other.table)
                && Objects.equals(column, other.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object, table, column);
    }
}
