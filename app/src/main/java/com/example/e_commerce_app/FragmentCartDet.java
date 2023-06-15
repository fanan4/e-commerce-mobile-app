package com.example.e_commerce_app;

import android.content.Intent;
import android.location.Address;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;


public class FragmentCartDet extends Fragment {
    ImageView searchBtn,backBtn;
    RecyclerView recyclerView;
    Adapter_cart adapter_cartList;
    DB_E_COMMERCE myDb;
    LinearLayout buyNowBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          View view=inflater.inflate(R.layout.fragment_cart_det,container,false);
          initView(view);
          List<Product> cartProducts=myDb.getProduct_DAO().getCartProduct(true);
          adapter_cartList.setProductsList(cartProducts);
          recyclerView.setAdapter(adapter_cartList);
          recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
          searchBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent=new Intent(getActivity(),Search_Activity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                  getActivity().startActivity(intent);
              }
          });
        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adress_fragment adress_fragment=new Adress_fragment();
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout,adress_fragment);
                transaction.commit();
            }
        });
        return view;
    }
    public void initView(View view){
        backBtn=view.findViewById(R.id.back);
        searchBtn=view.findViewById(R.id.cartSearchBtn);
        recyclerView=view.findViewById(R.id.cartRecylerView);
        adapter_cartList=new Adapter_cart(getActivity());
        myDb=DB_E_COMMERCE.getInstance(getActivity());
        buyNowBtn=view.findViewById(R.id.buyNowCont);
    }
}