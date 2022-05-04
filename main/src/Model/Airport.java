package Model;

import java.util.HashSet;

public class Airport {
    private int code;
    private String name;
    private String city;
    private String postCode;
    private String country;


    public Airport(String name, String city, String postCode, String country) {
        this.name = name;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }


}
