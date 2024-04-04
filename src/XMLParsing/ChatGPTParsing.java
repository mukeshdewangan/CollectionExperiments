package XMLParsing;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME;

public class ChatGPTParsing {
    public static void main(String[] args) throws IOException {
        //String filePath = "chat_gpt_person.xml";
        //String xmlData =  new String (Files.readAllBytes(Paths.get(filePath))) ;;

//        try {
//            XmlMapper xmlMapper = new XmlMapper();
//            PersonGPT person = xmlMapper.readValue(xmlData, PersonGPT.class);
//            System.out.println(person);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        PersonGPT pers1 = new PersonGPT();
        AddressPermanent add1 = new AddressPermanent(); add1.type="Home"; add1.street="strre1"; add1.city="Raipur"; add1.state="CG";
        AddressCurrent add2 = new AddressCurrent(); add2.type="Office"; add2.street="BEllandur"; add2.city="Bangalore"; add2.state="KA";
        Contact con1 = new Contact(); con1.email = "abc@ema.com"; con1.phone="+91-2134343";
        pers1.age = 30;
        pers1.name="Mukesh";
        //pers1. = List.of(add1, add2);
        pers1.addressCurrent = add2;
        pers1.addressPermanent = add1;
        pers1.contact = con1;

        try {
            // Create an XmlMapper object
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(USE_WRAPPER_NAME_AS_PROPERTY_NAME, false);
            // Write the object to an XML file
            File outputFile = new File("person1.xml");
            xmlMapper.writeValue(outputFile, pers1);

            System.out.println("Person object written to XML file: " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
@JacksonXmlRootElement(localName = "address_current")
class AddressCurrent {
    public String type;
    public String street;
    public String city;
    public String state;

    // Getter and Setter methods for street, city, and state

    @Override
    public String toString() {
        return "Address [type= " + type + ", street=" + street + ", city=" + city + ", state=" + state + "]";
    }
}

@JacksonXmlRootElement(localName = "address_permanent")
class AddressPermanent {
    public String type;
    public String street;
    public String city;
    public String state;

    // Getter and Setter methods for street, city, and state

    @Override
    public String toString() {
        return "Address [type= " + type + ", street=" + street + ", city=" + city + ", state=" + state + "]";
    }
}

class Contact {
    public String email;
    public String phone;

    // Getter and Setter methods for email and phone

    @Override
    public String toString() {
        return "Contact [email=" + email + ", phone=" + phone + "]";
    }
}


@JacksonXmlRootElement(localName = "person")
class PersonGPT {
    public String name;
    public int age;
    //@JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlText(value = false)
    public AddressCurrent addressCurrent;

    //@JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlText(value = false)
    public AddressPermanent addressPermanent;

    public Contact contact;

    // Getter and Setter methods for name, age, address, and contact

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", address size =" + ", contact=" + contact + "]";
    }
}


