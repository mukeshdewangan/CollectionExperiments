package Leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class ObjectSorting {
    private static String RESPONSE_ID = "11754681624";
    private static String RESPONSE_PAGE_ID = "130247834";
    private static String QUESTION_ID = "498243631";

    public static void main(String... strings) {
        ObjectSorting objectSorting = new ObjectSorting();
        List<ResponseAnswer> unsortedList = generateResponseAnswerList();

        List<ResponseAnswer> listForSorting = unsortedList.stream().filter(r->r.other_id == null).collect(Collectors.toList());
        List<ResponseAnswer> sortedList = objectSorting.sort(listForSorting);
        List<ResponseAnswer> listWithOtherId = unsortedList.stream().filter(r->r.other_id != null).collect(Collectors.toList());

        sortedList.addAll(listWithOtherId);

        for (ResponseAnswer r : sortedList) {
            System.out.println(r.toString());
        }
    }

    public int compare(ResponseAnswer r1, ResponseAnswer r2) {
        if (r1.row_id != null && r2.row_id != null && !r1.row_id.equals(r2.row_id))
            return r1.row_id.compareTo(r2.row_id);

        if (r1.col_id != null && r2.col_id != null && !r1.col_id.equals(r2.col_id))
            return r1.col_id.compareTo(r2.col_id);

        if (r1.choice_id != null && r2.choice_id != null) return r1.choice_id.compareTo(r2.choice_id);

        if(r1.text!= null && r2.text!=null) return r1.text.compareTo(r2.text);

        if(r1.row_id != null || r1.col_id != null || r1.choice_id != null || r1.text != null)
            return 1;
        if(r2.row_id != null || r2.col_id != null || r2.choice_id != null || r2.text != null)
            return -1;
        return 0;
    }

    private List<ResponseAnswer> sort(List<ResponseAnswer> responseAnswers){
        List<ResponseAnswer> sortedList = responseAnswers.stream().sorted(this::compare).collect(Collectors.toList());

        Integer[] arr = new Integer[5];
        arr[0] = 1;
        arr[1] = 6;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = -2;
        List<Integer> list= Arrays.stream(arr).toList();
        Collections.sort(list, (a,b)-> a - b);
        return sortedList;
        //Collections.sort(responseAnswers, this::compare);

    }


    private static List<ResponseAnswer> generateResponseAnswerList(){
        ResponseAnswer answer1 = new ResponseAnswer(RESPONSE_ID, RESPONSE_PAGE_ID,QUESTION_ID);
        answer1.other_id =  "3288898541";
        answer1.text = "Random";

        ResponseAnswer answer2 = new ResponseAnswer(RESPONSE_ID, RESPONSE_PAGE_ID,QUESTION_ID);
        answer2.other_id =  "3288902697";

        ResponseAnswer answer3 = new ResponseAnswer(RESPONSE_ID, RESPONSE_PAGE_ID,QUESTION_ID);
        answer3.row_id =  "3288903142";
        answer3.choice_id = "3288903146";

        ResponseAnswer answer4 = new ResponseAnswer(RESPONSE_ID, RESPONSE_PAGE_ID,QUESTION_ID);
        answer4.row_id =  "3288903143";
        answer4.choice_id = "3288903147";

        ResponseAnswer answer5 = new ResponseAnswer(RESPONSE_ID, RESPONSE_PAGE_ID,QUESTION_ID);
        answer5.row_id =  "3288903144";
        answer5.choice_id = "3288903148";


        ResponseAnswer answer6 = new ResponseAnswer();

        ResponseAnswer answer7 = new ResponseAnswer();
        //return Arrays.asList(answer1, answer2,answer3, answer4, answer6);
        //return Arrays.asList( answer1,answer3, answer7, answer5, answer4,answer6 );
        return Arrays.asList(  answer7, answer2,answer4, answer5, answer6);
    }
}


class ResponseAnswer{
    private String question_id;
    private String response_page_id;
    private String response_id;
    public String choice_id;
    public String row_id;
    public String col_id;
    public String other_id;
    public String text;
    public String download_url;
    public String content_type;

    public ResponseAnswer(){

    }
    public ResponseAnswer(String responseId, String responsePageId, String questionId){
        this.response_id = responseId;
        this.response_page_id = responsePageId;
        this.question_id = questionId;
    }

    @Override
    public String toString(){
        return "row_id " + row_id + " col_id " + col_id + " choice_id " + choice_id +
                " text "+ text + " other_id " + other_id;
    }
}
