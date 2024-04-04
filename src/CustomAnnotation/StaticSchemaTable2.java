package CustomAnnotation;

public class StaticSchemaTable2{
    @PIIField(piiTypes = {PiiType.EMAIL_ADDRESS, PiiType.PHONE_NUMBER})
    String emailContent;

    @PIIField(piiTypes = {PiiType.SSN})
    String Ssn;
}
