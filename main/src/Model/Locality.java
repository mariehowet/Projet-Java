package Model;

import java.util.HashSet;

public class Locality {
    private String city;
    private String postCode;
    private String country;

    public Locality(String city, String postCode, String country) {
        this.city = city;
        this.postCode = postCode;
        this.country = country;
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
