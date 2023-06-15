package com.example.e_commerce_app;

import static com.example.e_commerce_app.Product_Details.PRODUCT_KEY;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DialogAddReview extends DialogFragment {
    EditText userNameEdt,AddReviewEdt;
    TextView ProductName,textWarning;
    Button addProduct;
    interface AddReview{
         void AddReviewDb(Review review, int id);
    };
    AddReview addReview;
    @Nullable
    @Override
    public Dialog onCreateDialog(@Nullable  Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view=getActivity().getLayoutInflater().inflate(R.layout.dialog_add_review,null);
        initView(view);
        Bundle bundle=getArguments();
        if(null!=bundle){
            Product product=bundle.getParcelable(PRODUCT_KEY);
            if(null!=product){}
            ProductName.setText(product.getName().toString());
        }

         addProduct.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {
                 if(userNameEdt.getText().toString().equals("")||AddReviewEdt.getText().toString().equals("")){
                        textWarning.setVisibility(View.VISIBLE);
                        textWarning.setText("please enter all the fileds ");
                 }
                 else{
                     Bundle bundle=getArguments();
                     if(null!=bundle){
                         Product product=bundle.getParcelable(PRODUCT_KEY);
                         if(null!=product){

                             textWarning.setVisibility(View.GONE);
                             String userName=userNameEdt.getText().toString();
                             String review=AddReviewEdt.getText().toString();
                             String date=getCurrentDate();
                             try {
                                 addReview=(AddReview) getActivity();
                                 addReview.AddReviewDb(new Review(product.getId(),review,date),product.getId());
                             }catch(ClassCastException e){
                                 e.printStackTrace();
                             }
                         }
                     }
                       dismiss();
                 }
             }
         });
         builder.setView(view);
        return builder.create();
    }
    public String getCurrentDate(){
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd-YYYY");
        return  dateFormat.format(calendar.getTime());
    }
    public void initView(View view){
        userNameEdt=view.findViewById(R.id.addUserName);
        AddReviewEdt=view.findViewById(R.id.addReviewInput);
        ProductName=view.findViewById(R.id.Text);
        addProduct=view.findViewById(R.id.addRaviewBtn);
        textWarning=view.findViewById(R.id.textWarning);
    }
}
