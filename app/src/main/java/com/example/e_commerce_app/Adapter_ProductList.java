package com.example.e_commerce_app;

import static com.example.e_commerce_app.Product_Details.PRODUCT_KEY;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class Adapter_ProductList extends RecyclerView.Adapter<Adapter_ProductList.viewHolder>{
    List<Product> productsList;
    Context context;

    public Adapter_ProductList(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.productTitle.setText(productsList.get(position).getName());
        holder.productPrice.setText("$"+Double.toString(productsList.get(position).getPrice()));
        Glide.with(context)
                .asBitmap()
                .load(productsList.get(position).getUrl())
                .into(holder.productImg);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Product_Details.class);
                intent.putExtra(PRODUCT_KEY,productsList.get(position));
                context.startActivity(intent);
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
          MaterialCardView cardView;
          TextView productTitle,productPrice;
          ImageView productImg;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.ProductCard);
            productTitle=itemView.findViewById(R.id.productTitle);
            productPrice=itemView.findViewById(R.id.productPrice);
            productImg=itemView.findViewById(R.id.productImg);

        }
    }
}
