package com.proyek.rahmanjai.eatit.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyek.rahmanjai.eatit.Interface.ItemClickListener;
import com.proyek.rahmanjai.eatit.R;

public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_restaurant_name;
    public ImageView img_restaurant;

    private ItemClickListener itemClickListener;

    public RestaurantViewHolder(View itemView) {
        super(itemView);

        txt_restaurant_name = itemView.findViewById(R.id.restaurant_name);
        img_restaurant = itemView.findViewById(R.id.restaurant_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
