package Front;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static Front.EventType.*;

public class NewEnumDriver {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        System.out.print("Enter a string: ");
        String str= sc.nextLine();              //reads string
        //System.out.print("You have entered: "+str);
        getEnumValue(str);

        Map<String, String> map = new HashMap<>();
        map.put("sanjeev", "kumar");
        map.put("sandeep", "kumar");
        map.put("sandeep", "kumar vagle");
    }

    private static void getEnumValue(String eventType){
        switch (EventType.valueOf(eventType.toUpperCase())) {
            case ASSIGN:
            case UNASSIGN:
            case ARCHIVE:
            case TRASH:
            case REOPEN:
            case RESTORE:
            case REMINDER:
                System.out.println("updateConversationStatusChange");
                break;
            case TAG:
            case UNTAG:
                System.out.println("updateConversationTagChange");
                break;
            case COMMENT:
            case MENTION:
                System.out.println("updateCommentFromEvent");
                break;
            case INBOUND:
            case OUTBOUND:
            case FORWARD:
            case OUT_REPLY:
            case SENDING_ERROR:
            case MESSAGE_BOUNCE_ERROR:
                System.out.println("updateMessageFromEvent");
                break;
            case CONVERSATIONS_MERGED:
                System.out.println("updateMergedConversationFromEvent");
                break;
            case MOVE:
                break;
            default:
                throw new RuntimeException("Unhandled event type " + EventType.valueOf(eventType.toUpperCase()));
        }
    }
}
