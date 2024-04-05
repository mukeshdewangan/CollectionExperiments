package InternInterview;


import java.util.*;

public class SmallestSetCoveringAllIntervals {

    private int getMax(List<Integer> end){
        int result = Integer.MIN_VALUE;
        for (Integer i : end) {
            if( i > result ){
                result = i;
            }
        }
        return result;
    }
    public int getSmallestSetCoveringAllIntervals(List<Integer> start, List<Integer> end) {
        int endLimit = getMax(end);
        int[] occurrenceArr = new int[endLimit+2];

        for(int i =0; i < start.size(); i++){
            int startIndex = start.get(i);
            int endIndex = end.get(i);
            occurrenceArr[startIndex]++;
            occurrenceArr[endIndex+1]--;
        }

        // Calculate the occurrence
        List<Occurrence> occurrenceList  = new ArrayList<>();

        occurrenceList.add(new Occurrence(0,occurrenceArr[0]));
        for(int i = 0; i < occurrenceArr.length-1; i++){
            occurrenceArr[i+1] = occurrenceArr[i]+ occurrenceArr[i+1];
            occurrenceList.add(new Occurrence(i+1,occurrenceArr[i+1]));
        }

        occurrenceList.sort((a,b)-> b.count - a.count);

        Map<Integer, Integer> currentOccurrenceCountPerInterval = new HashMap<>();


        Set<Integer> smallestSet = new HashSet<>();
        for (Occurrence o: occurrenceList) {
            System.out.println(o);
            Map<Integer, Integer> cloneOccurrence = deepCopyMap(currentOccurrenceCountPerInterval);
            boolean should = includedNumberToSet(o.index, start, end, cloneOccurrence);
            if(should && cloneOccurrence.size() == currentOccurrenceCountPerInterval.size()) {
                smallestSet.add(o.index);
                System.out.println("Included " + o.index + " to set");
            }
        }

        return 0;
    }

    private Map<Integer, Integer>  deepCopyMap(Map<Integer, Integer> source){
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry: source.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return  result;
    }

    private boolean includedNumberToSet(Integer number, List<Integer> start, List<Integer> end, Map<Integer, Integer> occurrenceCountPerInterval){
        boolean flag= false;
        for ( int i = 0; i< start.size(); i++){
            if(occurrenceCountPerInterval.getOrDefault(i, 0) < 2){
                if (number >= start.get(i) && number <= end.get(i)) {
                    occurrenceCountPerInterval.put(i,occurrenceCountPerInterval.getOrDefault(i, 0)+1);
                    flag = true;
                }
            }
        }
        return flag;
    }

    class Occurrence{
        Integer index;
        Integer count;
        Occurrence(Integer i, Integer c){ index = i; count = c;}
        @Override
        public String toString(){
            return index + " : " + count +" ";
        }
    }

    public static void main(String[] args) {

        String s1=new String("hello");
        String s2="hello";
        String s3=s1.intern();//returns string from pool, now it will be same as s2
        System.out.println(s1==s2);//false because reference variables are pointing to different instance
        System.out.println(s2==s3);//true because reference variables are pointing to same instance

        SmallestSetCoveringAllIntervals sm = new SmallestSetCoveringAllIntervals();
        List<Integer> start = Arrays.asList(3, 2, 0, 4);
        List<Integer> end = Arrays.asList(6, 4, 2, 7);

        int[] startInt = {3, 2, 0, 4};
        int[] endInt = {6, 4, 2, 7};

        //int result = sm.getSmallestSetCoveringAllIntervals(start, end);
        //System.out.println(result);
        Set<Integer> result = sm.findMinimalSetOf2ItemFromEachInterval(startInt, endInt);
        System.out.println(result.size());
    }


    public  Set<Integer> findMinimalSetOf2ItemFromEachInterval(int[] start, int[] end){
        Set<Integer> minSet  = new HashSet<Integer>();
        if(!(start.length != end.length || start.length == 0 || end.length == 0)) {
            List<Interval> list = new ArrayList<Interval>();

            for(int i=0; i < start.length; i++) {
                list.add(new Interval(start[i], end[i], IntervalType.START));
                list.add(new Interval(start[i], end[i], IntervalType.END));
            }

            list.sort((o1, o2) -> {
                if(o1.intervalType == o2.intervalType) {
                    return  o1.intervalType == IntervalType.START ? o1.start.compareTo(o2.start) :
                            o1.end.compareTo(o2.end);
                }else if(o1.intervalType == IntervalType.START) {
                    return o1.start.compareTo(o2.end);

                }else {
                    int c = o1.end.compareTo(o2.start);
                    return c == 0 ? 1 : c;
                }
            });

            HashMap<String, Integer> map = new HashMap<>();
            for(Interval i : list){
                System.out.println(i.start +" " + i.end + "  " + i.intervalType);
                String key = getKey(i);
                if(i.intervalType == IntervalType.START) {
                    map.put(key, 0);
                }else {
                    int e = i.end;
                    while(map.get(key) < 2) {
                        if(minSet.contains(e)) {
                            e--;
                            map.put(key, map.get(key)+1);
                        }else {
                            minSet.add(e);
                        }
                    }
                }

            }
        }
        return minSet;
    }

    private static String getKey(Interval i) {
        return i.start+"_"+i.end;
    }

    class Interval {

        public Integer start;
        public Integer end;
        public IntervalType intervalType;

        public Interval(Integer start, Integer end, IntervalType type) {
            this.start = start;
            this.end = end;
            this.intervalType = type;
        }
    }
    enum IntervalType {
        START,
        END
    }
}
