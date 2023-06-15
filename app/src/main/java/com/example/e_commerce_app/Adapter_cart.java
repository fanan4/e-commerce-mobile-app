package com.example.e_commerce_app;

import static com.example.e_commerce_app.Product_Details.PRODUCT_KEY;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class Adapter_cart extends RecyclerView.Adapter<Adapter_cart.viewHolder>{
    List<Product> productsList;
    Context context;
    DB_E_COMMERCE myDb;
    public Adapter_cart(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public Adapter_cart.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_view,parent,false);
        return new Adapter_cart.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.productTitle.setText(productsList.get(position).getName());
        holder.productPrice.setText("$"+Double.toString(productsList.get(position).getPrice()));
        Glide.with(context)
                .asBitmap()
                .load(productsList.get(position).getUrl())
                .into(holder.productImg);
        holder.productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Product_Details.class);
                intent.putExtra(PRODUCT_KEY,productsList.get(position));
                context.startActivity(intent);
            }
        });
        holder.qtyValue.setText(Integer.toString(productsList.get(position).getAvailable_amount()));
        myDb=DB_E_COMMERCE.getInstance(context);
        holder.minus_One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty=  productsList.get(position).getAvailable_amount();
                Product product=productsList.get(position);
                product.setAvailable_amount(qty-1);
                myDb.getProduct_DAO().updateProduct(product);
                holder.qtyValue.setText(Integer.toString(qty-1));

            }
        });
        holder.addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty=  productsList.get(position).getAvailable_amount();
                Product product=productsList.get(position);
                product.setAvailable_amount(qty+1);
                myDb.getProduct_DAO().updateProduct(product);
                holder.qtyValue.setText(Integer.toString(qty+1));

            }
        });
    }


    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView productTitle,productPrice,qtyValue;
        ImageView productImg;
        RelativeLayout minus_One,addOne;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            productTitle=itemView.findViewById(R.id.cartItemTitle);
            productPrice=itemView.findViewById(R.id.cartItemPrice);
            productImg=itemView.findViewById(R.id.cartItemImg);
            minus_One=itemView.findViewById(R.id.minus_icon);
            addOne=itemView.findViewById(R.id.addOne);
            qtyValue=itemView.findViewById(R.id.quantityVal);
        }
    }
}
