package CustomAnnotation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StaticSchemaTable1 {
    String index;
    @PIIField(piiTypes = PiiType.EMAIL_ADDRESS)
    String emailId;

    String subject;
    String replyEmailId;

    @PIIField(piiTypes = PiiType.CONNECTOR_SPECIFIC, stmtForConnectorSpecific = "ProjectId")
    String connProjectId;
    
    String state;
    
    
    String subAccount;
    Map<String, String> map = new HashMap<>();

    @PIIField(piiTypes = {PiiType.PHONE_NUMBER, PiiType.SSN})
    String emailContent;

    @Override
    public int hashCode() {
        return Objects.hash(emailId, subject, replyEmailId, state, subAccount, map);
    }

    public static void main(String[] args) {
        StaticSchemaTable1 schemaTable1 = new StaticSchemaTable1();
        schemaTable1.index = "ads";
        schemaTable1.emailId="mukesh@gmail.com";
        schemaTable1.state = "sfsaf";
        schemaTable1.subject = "new Age";
        schemaTable1.replyEmailId = "seomthin@gmail.com";

        System.out.println(schemaTable1.hashCode());
    }
}
