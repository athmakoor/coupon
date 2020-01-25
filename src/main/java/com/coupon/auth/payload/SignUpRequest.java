package com.coupon.auth.payload;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignUpRequest {
    @NotBlank
    private String first_name;

    private String last_name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private List<Integer> role_ids;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getRole_ids() {
        return role_ids;
    }

    public void setRole_ids(List<Integer> role_ids) {
        this.role_ids = role_ids;
    }
}
