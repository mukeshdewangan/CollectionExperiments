package Reflection;


public class SQLPattern {
    public static void main(String[] args) {
        SQLPattern pattern = new SQLPattern();
        pattern.TestCases();
    }

    private void TestCases(){
        String q1 = "update integrations z set state={} where a=c;";
        String q2 = "update\n integrations\n set state\n={} where a=c;";
        String q3 = "update\n integrations\n set status = false, state\n={} where a=c;";
        String q4 = "--some comments\n\n\nupdate\n integrations\n set status = false,\n state\n={};";

        boolean res = isUpdateState(q1);
        System.out.println(res);
        res = isUpdateState(q2);
        System.out.println(res);
        res = isUpdateState(q3);
        System.out.println(res);
        res = isUpdateState(q4);
        System.out.println(res);

        String q5 = "select update_id from integrations\n where utc_offset='+05:00' and state_updated <= '2021-06-12'";
        res = isUpdateState(q5);
        System.out.println(res);// corner test case // probability is very low -> all these string in that sequence;

        //String q51 = "select update_id from integrations where ";
        //res = isUpdateState(51);
        //System.out.println(res);


        String q6 = "INSERT INTO integrations ()";
        res = isUpdateState(q6);
        System.out.println(res);

        String q7 = "update\n integrations\n set status = false where a=c;";
        res = isUpdateState(q7);
        System.out.println(res);

        String q8 = "update\n integrations\n set status = false where col=value;";
        res = isUpdateState(q8);
        System.out.println(res);

        String q9 = "update integrations set state={}";
        res = isUpdateState(q9);
        System.out.println(res);

        String q10 = "UPDATE\\n\\n integrations set xyz={state} where";
        res = isUpdateState(q10);
        System.out.println(res);
    }

    private boolean isUpdateState( String str){
        String[] strings = {"update", "integrations", "set", "state","=", "where"};
        String input = str.toUpperCase();
        int startIndex = 0;
        int wordIndex = 0;
        int pos = -1;
        while(wordIndex < strings.length){
            pos = input.indexOf(strings[wordIndex].toUpperCase(), startIndex);
            if(pos == -1)
                return false;
            startIndex = pos + strings[wordIndex].length();
            wordIndex++;
        }
        return true;
    }
}
