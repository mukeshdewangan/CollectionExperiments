package XMLParsing;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.XMLInputFactory;

public class WorkDayHcmResponseParsing {

    public static void main(String[] args) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        XMLInputFactory f = XMLInputFactory.newFactory();
        
    }
}
