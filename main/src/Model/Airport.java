package Model;

import java.util.HashSet;

public class Airport {
    private int code;
    private String name;
    private String city;
    private String postCode;
    private String country;


    public Airport(int code, String name, String city, String postCode, String country) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }
}
