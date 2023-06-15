package com.example.e_commerce_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_Reviews extends RecyclerView.Adapter<Adapter_Reviews.viewHolder>{
    Context context;
    ArrayList<Review> reviews;

    public Adapter_Reviews(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.review_list_items,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
            holder.reviewText.setText(reviews.get(position).getReviewText());
            holder.reviewDate.setText(reviews.get(position).getRivewDate());
            holder.userName.setText("Mohamed");
    }
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        RelativeLayout relativeLayout;
        TextView reviewText,reviewDate,userName;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout=itemView.findViewById(R.id.reviewcontainer);
            reviewText=itemView.findViewById(R.id.reviewText);
            reviewDate=itemView.findViewById(R.id.reviewDate);
            userName=itemView.findViewById(R.id.userName);
        }
    }
}
