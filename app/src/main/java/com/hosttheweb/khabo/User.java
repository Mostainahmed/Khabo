package com.hosttheweb.khabo;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("response")
    private String Response;

    @SerializedName("name")
    private String Name;

    @SerializedName("number")
    private String number;

    @SerializedName("location")
    private String location;

    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Name;
    }

    public String getNumber() {
        return number;
    }

    public String getLocation() {
        return location;
    }
}
