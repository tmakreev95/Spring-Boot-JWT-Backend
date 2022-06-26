package com.example.springsecurityjwt.dto.address;

public class RegisterAddressRequest {
    private String country;
    private String district;
    private String municipality;
    private String city;
    private String address;

    public RegisterAddressRequest() {
    }

    public RegisterAddressRequest(String country, String district, String municipality, String city, String address) {
        this.country = country;
        this.district = district;
        this.municipality = municipality;
        this.city = city;
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
