package com.dasayantist.ongeaplatform.models;

public class Counsellor {
    private String id, name, phone, location, area;

    public Counsellor() {
    }

    public Counsellor(String name, String phone, String location, String area) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.area = area;
    }

    public Counsellor(String id, String name, String phone, String location, String area) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
