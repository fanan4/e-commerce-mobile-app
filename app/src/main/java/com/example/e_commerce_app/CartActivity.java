package com.example.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setContentView(R.layout.cart_layout);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,new FragmentCartDet());
        transaction.commit();
    }
    public void initView(){

    }
}