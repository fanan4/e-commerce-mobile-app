package com.example.e_commerce_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class fragment_pay extends Fragment {
    EditText CardNumber,CVV,Expired;
    LinearLayout payAmount;
    TextView TotalAmount,warningPayment;
    DB_E_COMMERCE myDb;
    ImageView prieviousView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.payment_view,container,false);
        initView(view);
        Bundle bundle=getArguments();
        if(null!=bundle){
            int id=bundle.getInt("OrderId");
            if(id!=0){
                Order order=myDb.getProduct_DAO().getOrderById(id);
                if(null!=order){
                   handlePayAmount(order);
                   handlePreviousView(order.getAddressId());
                }
            }

        }


        return view;

    }
    public void handlePreviousView(int addressId){
        prieviousView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adress_fragment adress_fragment=new Adress_fragment();
                Bundle bundle=new Bundle();
                bundle.putInt("AddressId",addressId);
                adress_fragment.setArguments(bundle);
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout,adress_fragment);
                transaction.commit();
            }
        });
    }
    public void handlePayAmount(Order order){
          TotalAmount.setText("$"+Double.toString(TotalAmountValue()));
          payAmount.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                 if(CardNumber.getText().toString().equals("")||CVV.getText().toString().equals("")||Expired.getText().toString().equals("")){
                      warningPayment.setVisibility(View.VISIBLE);
                 }
                 else{
                     warningPayment.setVisibility(View.GONE);
                     order.setPaymentStatus("succes");
                 }
              }
          });
    }
    public void initView(View view){
        CardNumber=view.findViewById(R.id.CardNumber);
        CVV=view.findViewById(R.id.CVV);
        Expired=view.findViewById(R.id.ExpireDate);
        TotalAmount=view.findViewById(R.id.AmountToPay);
        payAmount=view.findViewById(R.id.amountCont);
        myDb=DB_E_COMMERCE.getInstance(getActivity());
        warningPayment=view.findViewById(R.id.warningPaymentMsg);
        prieviousView=view.findViewById(R.id.arraow_back);
    }
    public double TotalAmountValue(){
        List<Product> productsCart=myDb.getProduct_DAO().getCartProduct(true);
        double totalAmount=0;
        for (Product p:productsCart){
             totalAmount+=p.getPrice()*p.getAvailable_amount();
        }
        return totalAmount;
    }
}
