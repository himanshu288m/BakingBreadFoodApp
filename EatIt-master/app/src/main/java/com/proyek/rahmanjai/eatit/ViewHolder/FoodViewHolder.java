package com.proyek.rahmanjai.eatit.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyek.rahmanjai.eatit.Interface.ItemClickListener;
import com.proyek.rahmanjai.eatit.R;



public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView food_name,food_price;
    public ImageView food_image,fav_image,quick_cart;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FoodViewHolder(View itemView) {
        super(itemView);

        food_name = itemView.findViewById(R.id.food_name);
        food_image = itemView.findViewById(R.id.food_image);
        fav_image = itemView.findViewById(R.id.fav);
        food_price = itemView.findViewById(R.id.food_price);
        quick_cart = itemView.findViewById(R.id.btn_quick_cart);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
