package PIIDetection;

public class PiiField {

    /**
     *
     */
    public enum Field {
            EmailAndSSNAndMobileNumber("EmailSSN","(\\d{3}[- ]?\\d{2}[- ]?\\d{4})|([\\w\\.=-]+@[\\w\\.-]+\\.[\\w]{2,5})|((\\\\d{5}-\\\\d{4})|(\\d{5})|([A-Z]\\d[A-Z]\\s\\d[A-Z]\\d))|(\\+\\d{1,3}[- ]?)?\\d{10}");
            //EmailAddress("Email Address", "^[\\w\\.=-]+@[\\w\\.-]+\\.[\\w]{2,5}$"); // ^[^\s@]+@[^\s@]+$
            //PostalCode("Postal code", "^((\\d{5}-\\d{4})|(\\d{5})|([A-Z]\\d[A-Z]\\s\\d[A-Z]\\d))$"),
            //PostalCode("Postal code", "\\b\\d{5}\\b(-\\d{4})?\\b");
            //SSN("SSN", "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$");//
            //SSN("SSN", "\\d{3}[- ]?\\d{2}[- ]?\\d{4}"); //- Works
            //IPV4("IP v4 address", "^\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}$");
            //MobileNumber("Mobile Number", "^(\\+\\d{1,3}[- ]?)?\\d{10}$");// ^(?!0+$)(\+\d{1,3}[- ]?)?(?!0+$)\d{10}$ // ^(?!0+$)(\+\d{1,3}[- ]?)?(?!0+$)\d{10}$
            //Fax("Fax", "^\\+?[0-9]{6,}$"), // at least 6 digits and the + in the beginning is optional
            //MasterCard("Master card number", "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$"),
            //VisaCard("VISA card number", "\\b([4]\\d{3}[\\s]\\d{4}[\\s]\\d{4}[\\s]\\d{4}|[4]\\d{3}[-]\\d{4}[-]\\d{4}[-]\\d{4}|[4]\\d{3}[.]\\d{4}[.]\\d{4}[.]\\d{4}|[4]\\d{3}\\d{4}\\d{4}\\d{4})\\b\n"),
            //AmericanExpressCard("American Express card number","^3[47][0-9]{13}$");

            Field(String fieldDefinition, String regex) {
                this.fieldDefinition = fieldDefinition;
                this.regex = regex;
            }

            /** Field definition */
            public final String fieldDefinition;

            /** Regular Expression for the field */
            public final String regex;
        }

}
