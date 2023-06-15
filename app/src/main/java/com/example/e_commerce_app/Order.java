package com.example.e_commerce_app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
@Entity
public class Order {
    @PrimaryKey(autoGenerate = true)
    int id;
    int addressId;
    @TypeConverters(ProductItemsConverter.class)
    ArrayList<Integer> productsItems;
    String paymentStatus;

    public Order(int addressId, ArrayList<Integer> productsItems, String paymentStatus) {
        this.addressId = addressId;
        this.productsItems = productsItems;
        this.paymentStatus = paymentStatus;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public ArrayList<Integer> getProductsItems() {
        return productsItems;
    }

    public void setProductsItems(ArrayList<Integer> productsItems) {
        this.productsItems = productsItems;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", addressId=" + addressId +
                ", productsItems=" + productsItems +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
