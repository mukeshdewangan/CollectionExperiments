package CustomAnnotation;

import java.lang.reflect.Field;
import java.util.*;

public class PiiDetectionDriver {
    public static void main(String[] args) {

        Optional<Boolean> now = Optional.empty();
        Scanner in = new Scanner(System.in);
        int flag = in.nextInt();
        System.out.println("You entered string "+flag);

        if (flag % 2 == 0) {
            now = Optional.of(true);
        }
        System.out.println(now.isPresent());

        StaticSchemaTable1 schemaTable1 = new StaticSchemaTable1();
        schemaTable1.emailContent = "email content - Thanks ABC";
        schemaTable1.emailId="mukesh@gmail.com";
        schemaTable1.index= UUID.randomUUID().toString();

        Map<String, List<PiiType>> piiFields = getPiiFieldsAndTypes(schemaTable1);
        for (Map.Entry<String, List<PiiType>> entry: piiFields.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().stream().forEach(System.out::println);
        }
    }

    static Map<String, List<PiiType>> getPiiFieldsAndTypes(StaticSchemaTable1 any){
        Set<String> piiFields = new HashSet<>();
        Map<String, List<PiiType>> piiFieldTypes = new HashMap<>();
        for (Field field : any.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(PIIField.class)) {
                piiFields.add(field.getName());
                PIIField annotation = field.getAnnotation(PIIField.class);
                PiiType[] piiTypes = annotation.piiTypes();
                System.out.println(annotation.stmtForConnectorSpecific());
                piiFieldTypes.put( field.getName(), Arrays.asList(piiTypes));
            }
        }
        return piiFieldTypes;
    }


}
