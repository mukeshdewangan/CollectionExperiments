import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BetterCompression {

    public static void main(String[] args) {
//        String output = correctlyCompressed("a13b12c5a12");
//        System.out.println(output);
//        output = correctlyCompressed("a13b1c5a1c13");
//        System.out.println(output);
//        output = correctlyCompressed("a10b12c59a12");
//        System.out.println(output);
       // OnceADaySync();
        //String output = optimizedCompression("v10b12c59a12v10");
        String output = betterCompression2("v10c12o8x67z12l96h75x5s3d4b8b12c59a12v10");
        System.out.println(output);
    }

    public static String correctlyCompressed(String s){
        String output = "";
        Map<Character, Integer> count = new TreeMap<>();
        // a1b33c4d23
        int i = 0;
        while(i < s.length()){
            char charAt = s.charAt(i); // i=0
            int j =++i; // j=1;
            String digits = "";
            while(j < s.length()) {
                String potentialInt = String.valueOf(s.charAt(j));
                try {
                    int d = Integer.parseInt(potentialInt);
                    digits = digits + potentialInt;
                }
                catch (Exception ex){
                    break;
                }
                j++;
            }
            int digitValue = Integer.parseInt(digits);
            count.put(charAt, count.getOrDefault(charAt, 0)+ digitValue);
            i = j;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Character, Integer> chEntry: count.entrySet()) {
            stringBuilder.append(chEntry.getKey().toString()+ chEntry.getValue());
        }
        output = stringBuilder.toString();
        return output;
    }

    static void OnceADaySync(){
        Instant cursor = Instant.parse("2023-12-18T07:16:20.150Z");
        Instant now = Instant.parse("2023-12-19T07:03:08.371Z");
        Boolean onceADayResync = Duration.between(cursor, now).compareTo(Duration.ofDays(1)) > 0;
        System.out.println(onceADayResync);
    }



    /*
     * Complete the 'betterCompression' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String betterCompression2(String s) {
        List<Character> l = new ArrayList<Character>();
        l.add('0');l.add('1');l.add('2');l.add('3');l.add('4');l.add('5');l.add('6');l.add('7');l.add('8');l.add('9');

        HashMap<Character, Integer> sm = new HashMap<>();
        char key = s.charAt(0);
        String num = "";
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (l.contains(c)){
                num = num + c;
            } else {
                if (!num.isEmpty()){
                    if (sm.containsKey(key)){
                        sm.put(key, sm.get(key) + Integer.parseInt(num));
                    } else {
                        sm.put(key, Integer.parseInt(num));
                    }
                }
                key = s.charAt(i);
                num = "";
            }
        }

        if (sm.containsKey(key)){
            sm.put(key, sm.get(key) + Integer.parseInt(num));
        } else {
            sm.put(key, Integer.parseInt(num));
        }
        String finalS = "";
        for (Map.Entry e : sm.entrySet()){
            finalS = finalS + e.getKey() + e.getValue();
        }
        return finalS;
    }

    static String optimizedCompression(String str){
        int[] charFreq = new int[26];
        Pattern pattern = Pattern.compile("\\w\\d+");
        Matcher matcher = pattern.matcher(str);

        while(matcher.find()){
            String charAndDigit = matcher.group();
            char ch = charAndDigit.charAt(0);
            String digit = charAndDigit.substring(1);

            int value = Integer.parseInt(digit);
            charFreq[(ch - 'a')]+= value;
        }

        List<Character> chars = new ArrayList<>();
        for(int i=0; i< charFreq.length;i++){
            if(charFreq[i]>0) {
                System.out.println((char) ('a' + i) + " : " + charFreq[i]);
                chars.add((char) ('a' + i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character ch : chars) {
            sb.append(ch);
            sb.append(charFreq[ch-'a']);
        }

        //return String.valueOf(chars);
        //String string = Joiner.on("").join(str);
        return  sb.toString();
    }

}
