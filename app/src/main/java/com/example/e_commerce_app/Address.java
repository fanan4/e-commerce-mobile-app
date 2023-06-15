package com.example.e_commerce_app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Address {
    @PrimaryKey(autoGenerate = true)
    private  int id;
    private  String title;
    private  String type;
    private  String Street;
    private  String Phone;
    private  String city;

    public Address(String title, String type, String street, String phone, String city) {
        this.title = title;
        this.type = type;
        Street = street;
        Phone = phone;
        this.city = city;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", Street='" + Street + '\'' +
                ", Phone='" + Phone + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
