package APIResponseFieldChange.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Card {
    public String country;
    public String fingerprint;

    @JsonProperty("three_d_secure")
    public Object threeDSecure;
    public SecureUsage secureUsage;
    public String three_d_secure;

    public static class SecureUsage {
        public Boolean authenticated;
        public String authentication_flow;
        public String result;
        public String result_reason;
        public Boolean succeeded;
        public String version;

        @Override
        public String toString(){
            return "SecureUsage { " + "" +
                    "authenticated='" + authenticated + '\'' +
                    ",authentication_flow='" + authentication_flow +'\'' +
                    ",version='" + version +'\'' +
                    ",result='" + result +"\"}";
        }

        public SecureUsage(){}
    }

    @Override
    public String toString() {
        return "Card{" +
                "fingerprint='" + fingerprint + '\'' +
                ", three_d_secure='" + three_d_secure + '\'' +
                '}';
    }


}
