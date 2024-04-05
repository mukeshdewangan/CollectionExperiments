package WorkdayRaaS;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

public class DynamicParameters {
    public static void main(String[] args) {

        //String uri = "https://wd5-services1.myworkday.com/ccx/service/customreport2/atlassian/PeopleData_ISU/CR_All_Worker_Time_Off?End_Date={end_date}&Organizations%21WID=164717b62cdb106cde71a2062e203962&Start_Date={start_date}&Include_Subordinate_Organizations=1&format={csv}";
        String uri = "https://services1.myworkday.com/ccx/service/customreport2/atlanticmedia/ISU_Report_by_Project/Expense_Report_Lines_by_Project?Company!WID=1d578d6ad1ba10fd77f702eb03da2a5e&AcctingStartDate={2017-01-01-08%3A00}&AcctingEndDate={end_date}&ProjectHierarchy!WID=b045a38881dc1000be01804c0a0a0000!0bba9086f9a30109e791f8c13b434724";
        //String uri = "https://wd5-services1.myworkday.com/ccx/service/customreport2/atlassian/PeopleData_ISU/CR_Data_Source_Employee_Data_as_of_date_for_Historical_Hierarchy?Include_Managers=1&Effective_as_of_Date={2017-01-01-08%3A00}&Organizations%21WID=164717b62cdb106cde71a2062e203962&Include_Subordinate_Organizations=1&format=csv";

        // Extract query string from URI
        String queryString = uri.split("\\?")[1];

        // Define a regular expression pattern to match specific query parameters
        Pattern pattern = Pattern.compile("([a-zA-Z_]+)=\\{([%a-zA-Z_\\d-]+)\\}");

        // Create a matcher with the input query string
        Matcher matcher = pattern.matcher(queryString);

        // Create a map to store parameter names and values
        Map<String, String> parameters = new HashMap<>();

        // Find and print parameter names and values
        while (matcher.find()) {
            try {
                String paramName = URLDecoder.decode(matcher.group(1), "UTF-8");
                String templateVariable = matcher.group(2);
                parameters.put(paramName, templateVariable);
                System.out.println("Parameter name: " + paramName + ", Template Variable: " + templateVariable);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // If no matching parameters found
        if (parameters.isEmpty()) {
            System.out.println("No matching query parameters found in the URI.");
        }

    }
}
