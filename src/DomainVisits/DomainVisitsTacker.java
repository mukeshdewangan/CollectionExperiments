package DomainVisits;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DomainVisitsTacker {
    static class DomainVisit{
        public String domain;
        public Integer visitCount;
        DomainVisit(String dom, Integer count){
            domain = dom;
            visitCount = count;
        }
    }
    public static void main(String[] args) {
        String first = "900 mail.google.com";
        String second = "4000 google.com";
        String third = "600 google.in";
        String fourth = "800 facebook.com";

        List<String> input = Arrays.asList(first,second,third,fourth);
        Map<String, Integer> result = domainVisitTracker(input);

        DomainVisit[] visits = new DomainVisit[result.size()];
        int i = 0;
        for( Map.Entry<String, Integer> entry : result.entrySet()){
            //System.out.println(entry.getKey() +" : "+  entry.getValue());
            visits[i++] = new DomainVisit(entry.getKey(), entry.getValue());
        }

        Arrays.sort(visits, (p1, p2) ->{ return -1 * p1.visitCount.compareTo(p2.visitCount);});

        for (DomainVisit d : visits) {
            System.out.println( d.domain + " : " + d.visitCount);
        }


    }

    public static Map<String, Integer> domainVisitTracker(List<String> domains){
        Map<String, Integer> domainVisits = new HashMap<>();

        for(String domain : domains){
            String[] visits = domain.split("\\s");
            Integer count = Integer.parseInt(visits[0]);
            addDomainVisits(visits[1], count, domainVisits);
        }

        return domainVisits;
    }
    private static void addDomainVisits( String domain, Integer count , Map<String, Integer> domainVisits){
        String[] splits = domain.split("[.]");
        String initial = "";
        int i = splits.length-1;
        while( i >=0 ){
            initial = splits[i] + "." +  initial;
            Integer existingCount = domainVisits.getOrDefault(initial,0);
            domainVisits.put(initial, existingCount+count);
            i--;
        }
    }
}


