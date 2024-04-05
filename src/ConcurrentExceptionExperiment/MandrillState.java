package ConcurrentExceptionExperiment;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


class Random{
    private static int ElementCount = 1000;
    public List<Integer> list;


    //public Map<String, >

    public Random() {
        list = new ArrayList<>();
        for (int i = 0; i < ElementCount; i++) {
            list.add( new Integer ((int)(Math.random() * ElementCount)));
        }
    }
    public Random(List<Integer> aList) {
        list = new ArrayList<>(aList);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //Random r = (Random) o;
        return list.equals(((Random) o).list);
    }
}


enum PiiType1{
    EMAIL_ADDRESS,
    PASSPORT,
    SSN,
    ADDRESS,
    PHONE_NUMBER
}
public class MandrillState {
    public Integer[] arr;
    public Double aDouble;
    //public Random random;
    public Map<String, String> values = new HashMap<>();
    public PiiType1 type1;

    public MandrillState() {
    }

    public MandrillState(Integer[] array, Double a, Map<String, String> map, PiiType1 piiType) {
        arr = Arrays.copyOf(array, array.length);
        aDouble = a;
        type1 = piiType;
        //random = new Random();
        values = new HashMap<>(map);
    }
}

