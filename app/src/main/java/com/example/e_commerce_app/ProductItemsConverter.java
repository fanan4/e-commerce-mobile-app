package com.example.e_commerce_app;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ProductItemsConverter {
    @TypeConverter
    public String ProductItemToGson(ArrayList<Integer> productsItem){
        Gson gson=new Gson();
        return gson.toJson(productsItem);
    }
    @TypeConverter
    public ArrayList<Integer> GsonToProductsItems(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Integer>>(){}.getType();
        return gson.fromJson(json,type);
    }
}
