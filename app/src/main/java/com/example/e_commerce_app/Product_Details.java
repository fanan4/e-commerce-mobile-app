package com.example.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Product_Details extends AppCompatActivity implements DialogAddReview.AddReview{
    public static final String PRODUCT_KEY="PRODUCT_KEY";
    ImageView productImg,firstStarFill,firstStarEpmty,secondStarFill,secondStarEpmty,thirdStarFill,thirdStarEpmty;
    RelativeLayout  addOneImg,minusImg,qtityValue;
    TextView productTitle,productPrice,productDesc,Qtity,AddAReviewBtn;
    Button addToCart;
    Product product;
    RecyclerView reviewRecyclerView;
    DB_E_COMMERCE MyDb;
    Adapter_Reviews adapter_reviews;
    private static int quantity=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_activity);
        MyDb=DB_E_COMMERCE.getInstance(this);
        initView();
        Intent intent=getIntent();
        if(intent!=null){
            product=intent.getParcelableExtra(PRODUCT_KEY);
              if(null!=product){
                  //System.out.println(product.toString());
                  setUIVALUES(product);
                  handlRating(product);
                  handleQtity();
                  initRecylerView(product);
                  handleAddReview(product);
                  handleAddToCart(product);
              }
        }

    }
    public void handleAddToCart(Product product){
          addToCart.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                       product.setCart(true);
                       if(product.isCart()){
                           product.setAvailable_amount(product.getAvailable_amount()+quantity);
                       }
                       else{
                           product.setAvailable_amount(quantity);
                       }
                       MyDb.getProduct_DAO().updateProduct(product);
              }
          });
    }
    public void handleAddReview(Product product){
          AddAReviewBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  DialogAddReview dialogAddReview=new DialogAddReview();
                  Bundle bundle=new Bundle();
                  bundle.putParcelable(PRODUCT_KEY,product);
                  dialogAddReview.setArguments(bundle);
                  dialogAddReview.show(getSupportFragmentManager(),"add review");
              }
          });

    }
    public void initRecylerView(Product product){
         adapter_reviews=new Adapter_Reviews(this);
         Product newProduct=MyDb.getProduct_DAO().getProductById(product.getId());
         //System.out.println("reviewwsssssssssssssssssssssssss"+newProduct.getReviews());
         if(null!=newProduct.getReviews()){
             if( newProduct.getReviews().size()>0){
                 adapter_reviews.setReviews(newProduct.getReviews());
                 reviewRecyclerView.setAdapter(adapter_reviews);
                 reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
             }
         }

    }
    public void setUIVALUES(Product product){
         productTitle.setText(product.getName());
         productPrice.setText(Double.toString(product.getPrice()) +"$");
         productDesc.setText(product.getDesc());
         quantity=1;
         Qtity.setText(Integer.toString(quantity));
         Glide.with(this)
                 .asBitmap()
                 .load(product.getUrl())
                 .into(productImg);
    }
    public void handlRating(Product product){
           switch (product.getRate()){
               case 1:
                   firstStarEpmty.setVisibility(View.GONE);
                   firstStarFill.setVisibility(View.VISIBLE);
                   break;
               case 2:
                   firstStarEpmty.setVisibility(View.GONE);
                   firstStarFill.setVisibility(View.VISIBLE);
                   secondStarEpmty.setVisibility(View.GONE);
                   secondStarFill.setVisibility(View.VISIBLE);
                   break;
               case 3:
                   firstStarEpmty.setVisibility(View.GONE);
                   firstStarFill.setVisibility(View.VISIBLE);
                   secondStarEpmty.setVisibility(View.GONE);
                   secondStarFill.setVisibility(View.VISIBLE);
                   thirdStarEpmty.setVisibility(View.GONE);
                   thirdStarFill.setVisibility(View.VISIBLE);
                   break;

           }
           firstStarEpmty.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   firstStarEpmty.setVisibility(View.GONE);
                   firstStarFill.setVisibility(View.VISIBLE);
                   product.setRate(1);
                   MyDb.getProduct_DAO().updateProduct(product);

               }
           });
           firstStarFill.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   firstStarEpmty.setVisibility(View.VISIBLE);
                   firstStarFill.setVisibility(View.GONE);
                   secondStarEpmty.setVisibility(View.VISIBLE);
                   secondStarFill.setVisibility(View.GONE);
                   thirdStarEpmty.setVisibility(View.VISIBLE);
                   thirdStarFill.setVisibility(View.GONE);
                   product.setRate(0);
                   MyDb.getProduct_DAO().updateProduct(product);
               }
           });
           secondStarEpmty.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   firstStarEpmty.setVisibility(View.GONE);
                   firstStarFill.setVisibility(View.VISIBLE);
                   secondStarEpmty.setVisibility(View.GONE);
                   secondStarFill.setVisibility(View.VISIBLE);
                   product.setRate(2);
                   MyDb.getProduct_DAO().updateProduct(product);
               }
           });
           secondStarFill.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   secondStarEpmty.setVisibility(View.VISIBLE);
                   secondStarFill.setVisibility(View.GONE);
                   thirdStarEpmty.setVisibility(View.VISIBLE);
                   thirdStarFill.setVisibility(View.GONE);
                   product.setRate(1);
                   MyDb.getProduct_DAO().updateProduct(product);
               }
           });
           thirdStarEpmty.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   firstStarEpmty.setVisibility(View.GONE);
                   firstStarFill.setVisibility(View.VISIBLE);
                   secondStarEpmty.setVisibility(View.GONE);
                   secondStarFill.setVisibility(View.VISIBLE);
                   thirdStarEpmty.setVisibility(View.GONE);
                   thirdStarFill.setVisibility(View.VISIBLE);
                   product.setRate(3);
                   MyDb.getProduct_DAO().updateProduct(product);
               }
           });
           thirdStarFill.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   thirdStarEpmty.setVisibility(View.VISIBLE);
                   thirdStarFill.setVisibility(View.GONE);
                   product.setRate(2);
                   MyDb.getProduct_DAO().updateProduct(product);
               }
           });
    }
    public void handleQtity(){
        addOneImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 quantity++;
                 Qtity.setText(Integer.toString(quantity) );
            }
        });
        minusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity>1){
                    quantity--;
                    Qtity.setText(Integer.toString(quantity));
                }
            }
        });
    }
    public void initView(){
        productImg=findViewById(R.id.ProductDetImg);
        productTitle=findViewById(R.id.ProductDetTitle);
        productPrice=findViewById(R.id.ProductDetPrice);
        productDesc=findViewById(R.id.ProductDetDesc);
        addToCart=findViewById(R.id.addToCartBtn);
        reviewRecyclerView=findViewById(R.id.reiviewsRecyclerview);
        firstStarEpmty=findViewById(R.id.firstEmptyStar);
        firstStarFill=findViewById(R.id.firstFillStar);
        secondStarEpmty=findViewById(R.id.secondEmptyStar);
        secondStarFill=findViewById(R.id.secondfillStar);
        thirdStarFill=findViewById(R.id.thirdfillStar);
        thirdStarEpmty=findViewById(R.id.thirdEmptyStar);
        addOneImg=findViewById(R.id.addOne);
        minusImg=findViewById(R.id.minus_icon);
        qtityValue=findViewById(R.id.qtyValue);
        Qtity=findViewById(R.id.quantityVal);
        AddAReviewBtn=findViewById(R.id.addAReview);
    }

    @Override
    public void AddReviewDb(Review review,int Id) {
        Product newProduct=MyDb.getProduct_DAO().getProductById(Id);
        //System.out.println(review.toString()+ " id : "+newProduct.toString());
        if(null!=newProduct){
            if(null!=newProduct.getReviews()){
                ArrayList<Review> reviews=newProduct.getReviews();
                reviews.add(review);
                newProduct.setReviews(reviews);
                adapter_reviews.setReviews(newProduct.getReviews());
                //System.out.println(newProduct.toString());
                MyDb.getProduct_DAO().updateProduct(newProduct);
            }


        }
    }
}