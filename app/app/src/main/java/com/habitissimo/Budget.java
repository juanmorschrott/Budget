package com.habitissimo;

import android.location.Location;

public class Budget {

    private String description;

    private String subCategory;

    private String name;

    private String email;

    private Integer phone;

    private Location location;

    public Budget() {
        super();
    }

    public Budget(String description, String subCategory, String name, String email, Integer phone, Location location) {
        this.description = description;
        this.subCategory = subCategory;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
