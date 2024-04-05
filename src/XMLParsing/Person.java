package XMLParsing;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

public class Person {
    public  String firstName;
    public  String lastName;
    public  List<String> phoneNumbers = new ArrayList<>();
    @JacksonXmlElementWrapper(localName = "address", useWrapping = false)
    public  List<Address> address_1 = new ArrayList<>();
}
