package com.arcanum.arcanumstoremanager.domain.entity;

import java.util.Date;

/**
 * Created by norman on 24/01/18.
 */

public class User {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private Date dob;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public static class Builder {
        User user = new User();

        public Builder username(String username) {
            user.setUsername(username);
            return this;
        }

        public Builder password(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder fullname(String fullname) {
            user.setFullname(fullname);
            return this;
        }

        public Builder email(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder phone(String phone) {
            user.setPhone(phone);
            return this;
        }

        public Builder dob(Date dob) {
            user.setDob(dob);
            return this;
        }

        public User build() {
            return user;
        }
    }

}
