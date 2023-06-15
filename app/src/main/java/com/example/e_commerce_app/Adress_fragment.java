package com.example.e_commerce_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Adress_fragment extends Fragment {
      EditText addressTitle,addressType,adressStreet,adressVillage,phone;
      TextView warning;
      RadioGroup radioGroup;
      RelativeLayout saveAdress;
      DB_E_COMMERCE myDb=DB_E_COMMERCE.getInstance(getContext());
      ImageView backAdress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          View view=inflater.inflate(R.layout.address_view,container,false);
          initView(view);
          handleAdressComing();
          handleSaveAdress();
          handleBackAddress();

          return view ;
    }
    public void handleAdressComing(){
        Bundle bundle=getArguments();
        if(null!=bundle){
            int addressID=bundle.getInt("AddressId");
            if(addressID!=0){
                Address address=myDb.getProduct_DAO().getAdressById(addressID);
                if(null!=address){
                    System.out.println("adresssssssssssssss;"+address.toString());
                    addressTitle.setText(address.getTitle());
                    addressType.setText(address.getType());
                    adressVillage.setText(address.getCity());
                    adressStreet.setText(address.getStreet());
                    phone.setText(address.getPhone());
                }
            }
        }
    }
    public boolean validate(){
        if(addressTitle.getText().toString().equals("")||addressType.getText().toString().equals("")||adressStreet.getText().toString().equals("")||
               adressVillage.getText().toString().equals("")||phone.getText().toString().equals("")){
            return false;
        }
        else{
            return true;
        }
    }
    public void initView(View view){
          addressTitle=view.findViewById(R.id.titleAdress);
          addressType=view.findViewById(R.id.addressType);
          adressStreet=view.findViewById(R.id.addrssStreet);
          adressVillage=view.findViewById(R.id.city);
          phone=view.findViewById(R.id.phone);
          radioGroup=view.findViewById(R.id.radioGrp);
          saveAdress=view.findViewById(R.id.saveAdress);
          warning=view.findViewById(R.id.warning);
          backAdress=view.findViewById(R.id.backAddress);
    }
    public void handleSaveAdress(){
        saveAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validate()){
                    warning.setVisibility(View.VISIBLE);
                    warning.setText("please fill all the blanks");
                }
                else{
                    warning.setVisibility(View.VISIBLE);
                    String title=addressTitle.getText().toString();
                    String type=addressType.getText().toString();
                    String Street=adressStreet.getText().toString();
                    String Phone=phone.getText().toString();
                    String city=adressVillage.getText().toString();
                    Address address=new Address(title,type,Street,Phone,city);
                    long Addressid=myDb.getProduct_DAO().addAdress(address);
                    //System.out.println("the new address issssssssssssssssssss: "+id);

                     sendOrderToPay(Addressid);


                }

            }
        });
    }
    public void sendOrderToPay(long Addressid){
        List<Integer> productItems=myDb.getProduct_DAO().getProductsID(true);
        Order order=new Order((int)Addressid,(ArrayList<Integer>) productItems,"");
        long orderId=myDb.getProduct_DAO().addOrder(order);
        fragment_pay fragment_pay=new fragment_pay();
        Bundle bundle=new Bundle();
        bundle.putInt("OrderId",(int)orderId);
        fragment_pay.setArguments(bundle);
        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,fragment_pay);
        transaction.commit();
    }
    public void handleBackAddress(){
        backAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCartDet fragmentCartDet=new FragmentCartDet();
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout,fragmentCartDet);
                transaction.commit();
            }
        });
    }
}