package com.arcanum.arcanumstoremanager.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by norman on 24/01/18.
 */

public class User {
    private String name;
    private String email;
    private String phone;
    private Date dob;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

}
