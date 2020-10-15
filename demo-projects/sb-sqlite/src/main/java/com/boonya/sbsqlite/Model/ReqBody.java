package com.boonya.sbsqlite.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqBody {

    private String Name;

    private String Email;

    public String getName() {
        return Name;
    }
    @JsonProperty(value="Name")
    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }
    @JsonProperty(value="Email")
    public void setEmail(String email) {
        Email = email;
    }




}
