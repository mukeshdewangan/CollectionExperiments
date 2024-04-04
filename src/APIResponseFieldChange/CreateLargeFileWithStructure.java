package APIResponseFieldChange;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CreateLargeFileWithStructure {
    public static void main(String[] args) throws IOException {
        String filePath = "/Users/mukesh.dewangan/Documents/GSON_Exp/GSON_Exp/src/main/resources/input_file.json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Model> jsonDataObject = generateRandomModelObjects(200000);

        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(filePath), jsonDataObject);
    }

    static List<Model> generateRandomModelObjects(long number){
        List<Model> list = new ArrayList<>();

        for (long num = 0; num < number ; num++) {
            list.add(Model.randomModel());
        }
        return list;
    }

    static class Model {
        public int documentId;
        public int parentDocId;
        public String docType,docAuthor,docTitle;
        public boolean isParent;
        public List<String> docLanguage;

        @Override
        public String toString() {
            return "Model {" +
                    "documentId=" + documentId +
                    ", parentDocId=" + parentDocId +
                    ", docType='" + docType + '\'' +
                    ", docAuthor='" + docAuthor + '\'' +
                    ", docTitle='" + docTitle + '\'' +
                    ", isParent=" + isParent +
                    ", docLanguage=" + docLanguage +
                    '}';
        }
        static Random rand = new Random();
        static int min = 1;
        static int max = 999999;
        static Model randomModel(){
            Model m = new Model();
            m.documentId = rand.nextInt((max - min) + 1) + min;
            m.parentDocId = rand.nextInt((max - min) + 1) + min;
            m.isParent = rand.nextBoolean();
            m.docAuthor = UUID.randomUUID().toString();
            m.docTitle = UUID.randomUUID().toString();
            m.docType = UUID.randomUUID().toString();
            List<String> lang = new ArrayList<>();
            lang.add("fr-FR");
            lang.add("en-US");
            lang.add("hr-HR");
            m.docLanguage = lang;
            return m;

        }
    }


}
