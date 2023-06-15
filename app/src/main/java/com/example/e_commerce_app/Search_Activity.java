package com.example.e_commerce_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.time.chrono.JapaneseDate;
import java.util.ArrayList;
import java.util.List;

public class Search_Activity extends AppCompatActivity implements DialogCatList.GetCategorie{
      Button firstBtn,secondBtn,thirdBtn,fourthBtn;
      RecyclerView SearchRecView;
      Adapter_ProductList adapter_productList;
      EditText searchProduct;
      ImageView searchBtn;
      DB_E_COMMERCE myDb;
      TextView categorieName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity_layout);
        myDb=DB_E_COMMERCE.getInstance(this);
        initView();
        handleCategorie();
        handleSerach();
        getCategorie();
        initNavigationView();
    }
    public void initNavigationView(){

      }
    public void getCategorie(){
        Intent intent=getIntent();
        if(null!=intent){
            String categorie=intent.getStringExtra("Cat");
            if(null!=categorie){
                  List<Product> products=myDb.getProduct_DAO().getProductByCategorie(categorie);
                  categorieName.setText(categorie);
                  handlRecView(products);
            }
        }
    }
    public void initView(){
        firstBtn=findViewById(R.id.firstCat);
        secondBtn=findViewById(R.id.secCat);
        thirdBtn=findViewById(R.id.thirdCat);
        fourthBtn=findViewById(R.id.fourthCat);
        SearchRecView=findViewById(R.id.serachProductRec);
        adapter_productList=new Adapter_ProductList(this);
        searchProduct=findViewById(R.id.searchInput);
        searchBtn=findViewById(R.id.searchIcon);
        categorieName=findViewById(R.id.categorieName);
    }
    public  void handleSerach(){
        searchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   String name=searchProduct.getText().toString();
                   categorieName.setText("");
                   List<Product> SimilarProduct= myDb.getProduct_DAO().getSimilarProduct(name);
                   handlRecView(SimilarProduct);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public void handleCategorie(){
         firstBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 List<Product> foodProduct= myDb.getProduct_DAO().getProductByCategorie("Food");
                 categorieName.setText("Food");
                 handlRecView(foodProduct);
             }
         });
         secondBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 List<Product> DrinkProduct= myDb.getProduct_DAO().getProductByCategorie("Drink");
                 categorieName.setText("Drink");
                 handlRecView(DrinkProduct);
             }
         });
         thirdBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 List<Product> CleanserProduct= myDb.getProduct_DAO().getProductByCategorie("Cleanser");
                 categorieName.setText("Cleanser");
                 handlRecView(CleanserProduct);
             }
         });
         fourthBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 List<Product> NutsProduct= myDb.getProduct_DAO().getProductByCategorie("Nuts");
                 categorieName.setText("Nuts");
                 handlRecView(NutsProduct);
             }
         });
    }
    public void handlRecView(List<Product> products){
        adapter_productList.setProductsList(products);
        SearchRecView.setAdapter(adapter_productList);
        SearchRecView.setLayoutManager( new GridLayoutManager(this,2));
    }

    @Override
    public void getCategorie(String categorie) {
        List<Product> products=myDb.getProduct_DAO().getProductByCategorie(categorie);
        handlRecView(products);
    }
}