package com.example.e_commerce_app;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ConverterClass {
    @TypeConverter
    public ArrayList<Review> GsonToReviews(String gson){
        Gson gson1=new Gson();
        Type type=new TypeToken<ArrayList<Review>>(){}.getType();
        return gson1.fromJson(gson,type);
    }
    @TypeConverter
    public String ReviewsToGson(ArrayList<Review> reviews){
        Gson gson=new Gson();
        return gson.toJson(reviews);
    }


}
