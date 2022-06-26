package com.example.springsecurityjwt.dto.contact;

import javax.validation.constraints.Email;

public class RegisterContactRequest {
    private String website;
    private String mobile;
    private String fax;

    @Email
    private String altEmail;

    public RegisterContactRequest() {
    }

    public RegisterContactRequest(String website, String mobile, String fax, @Email String altEmail) {
        this.website = website;
        this.mobile = mobile;
        this.fax = fax;
        this.altEmail = altEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAltEmail() {
        return altEmail;
    }

    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }
}
