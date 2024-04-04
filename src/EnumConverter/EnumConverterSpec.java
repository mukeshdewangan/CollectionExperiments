package EnumConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumConverterSpec {

    public static List<String> takesOperationArray(OperationType... types){
        //ArrayList<String> strArray = new ArrayList<>();

        List<String> strArray = Arrays.stream(types).map(Enum::toString).collect(Collectors.toList());

        System.out.println("From STRING List");
        for (String str: strArray) {
            System.out.println(str);
        }

        List<OperationType> operationTypes = Arrays.stream(types).collect(Collectors.toList());

        System.out.println("From OperationTag List");
        for (OperationType t: operationTypes) {
            System.out.println(t);
        }
        return  strArray;
    }

    public static void main(String[] args) {
        CopyType t1 = CopyType.IMPORT;
        //takesOperationArray(t1.getOperationType());

        CopyType t2 = CopyType.XMIN;
        CopyType[] arr = new CopyType[]{t1, t2};
        //Arrays.stream(arr).map()
        takesOperationArray(t1.getOperationType(), t2.getOperationType());
    }
}
