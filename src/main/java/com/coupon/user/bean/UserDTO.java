package com.coupon.user.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private Boolean active;
    private List<Role> roles = new ArrayList<>();

    public UserDTO() {
        super();
    }

    public UserDTO(User user) {
        super();
        this.id = user.getId();
        this.email = user.getEmail();
        this.first_name = user.getFirstName();
        this.last_name = user.getLastName();
        this.active = user.getActive();
        this.roles = user.getRoles();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
