package org.pincio.games.dto;

public class Recipient {

    private String email; // (String, Mandatory) email of the recipient.
    private String name; // (String, Optional) name of the recipient.
   //private String phone; // (Object, Optional) phone number of the recipient.
   //private String country_code; // (String) country code.
   //private String phone_number; // (String) phone number.
   //private String contacts; // (Array[String]) the System ID of the contact.
   //private String lists; // (Array[String]) the System ID of the list.


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
