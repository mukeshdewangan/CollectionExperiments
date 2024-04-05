package BraintreeDeserilzer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShipTo {
        @JsonProperty("ContactEmail")
        public Object contactEmailOrEmails;

        public ContactEmail contactEmail;
        public ContactEmail secondaryEmail;

//        public ContactEmail getContactEmail(){
//
//        }
//        public ContactEmail getContactEmail(){
//
//        }
        public static class ContactEmail {
            public String useType;
            public String address;
        }
}
