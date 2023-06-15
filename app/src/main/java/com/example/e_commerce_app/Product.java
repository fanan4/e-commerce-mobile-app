package com.example.e_commerce_app;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;

@Entity(tableName = "Product")
public class Product implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String desc;
    private String url;
    private double price;
    private int available_amount;
    private int rate;
    private int userPoint;
    private int popularityPoint;
    private String Categorie;
    private boolean cart;
    @TypeConverters(ConverterClass.class)
    public ArrayList<Review> reviews;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", available_amount=" + available_amount +
                ", rate=" + rate +
                ", userPoint=" + userPoint +
                ", popularityPoint=" + popularityPoint +
                ", reviews=" + reviews +
                ",Categorie="+ Categorie +
                ",cart="+cart+
                '}';
    }

    public boolean isCart() {
        return cart;
    }

    public void setCart(boolean cart) {
        this.cart = cart;
    }

    public Product(String name, String desc, String url, double price, int available_amount, ArrayList<Review> reviews, String Categorie, boolean cart) {
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.price = price;
        this.available_amount = available_amount;
        this.rate = 0;
        this.userPoint = 0;
        this.popularityPoint = 0;
        this.reviews=reviews;
        this.Categorie=Categorie;
        this.cart=cart;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        desc = in.readString();
        url = in.readString();
        price = in.readDouble();
        available_amount = in.readInt();
        rate = in.readInt();
        userPoint = in.readInt();
        popularityPoint = in.readInt();
        Categorie=in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            cart=in.readBoolean();
        }
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailable_amount() {
        return available_amount;
    }

    public void setAvailable_amount(int available_amount) {
        this.available_amount = available_amount;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public int getPopularityPoint() {
        return popularityPoint;
    }

    public void setPopularityPoint(int popularityPoint) {
        this.popularityPoint = popularityPoint;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(desc);
        parcel.writeString(url);
        parcel.writeDouble(price);
        parcel.writeInt(available_amount);
        parcel.writeInt(rate);
        parcel.writeInt(userPoint);
        parcel.writeInt(popularityPoint);
        parcel.writeString(Categorie);
        parcel.writeBoolean(cart);
    }
}
