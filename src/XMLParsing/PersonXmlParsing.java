package XMLParsing;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.io.*;
import java.nio.file.Paths;

public class PersonXmlParsing {
    public static void main(String[] args) throws IOException {
        String filePath = "person.xml";
        String personXml = new String (Files.readAllBytes(Paths.get(filePath))) ;
//        String personXml = "<Person>\n" +
//                "    <firstName>Rohan</firstName>\n" +
//                "    <lastName>Daye</lastName>\n" +
//                "    <phoneNumbers>\n" +
//                "        <phoneNumbers>9911034731</phoneNumbers>\n" +
//                "        <phoneNumbers>9911033478</phoneNumbers>\n" +
//                "    </phoneNumbers>\n" +
//                "    <address>\n" +
//                "        <streetName>Name1</streetName>\n" +
//                "        <city>City1</city>\n" +
//                "    </address>\n" +
//                "    <address>\n" +
//                "        <streetName>Name2</streetName>\n" +
//                "        <city>City2</city>\n" +
//                "    </address>\n" +
//                "</Person>";

        //assertEquals(XML, byteArrayOutputStream.toString());
        xmlParsing(personXml);
    }

    public static void xmlParsing(String personXml) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        Person value = xmlMapper.readValue(personXml, Person.class);
        System.out.println(value.firstName);
    }
}
