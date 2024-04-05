package ConcurrentExceptionExperiment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MandrillState2  {
    public Integer[] arr;
    public Double aDouble;

    public PiiType1 type1;

    public MandrillState2(){}
    public MandrillState2(Integer[] array, Double a, PiiType1 piiType){
        arr = Arrays.copyOf(array,array.length);
        aDouble = a;
        type1 = piiType;
        //random = new Random();
        //values = new HashMap<>(map);
    }
}