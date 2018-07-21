package com.welt.data.dto;

public class AddressDto {

    private GeoDto geo;

    private String zipcode;

    private String street;

    private String suite;

    private String city;

    public GeoDto getGeo() {
        return geo;
    }

    public void setGeo(GeoDto geo) {
        this.geo = geo;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
