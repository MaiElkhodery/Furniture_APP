package com.example.furniture_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.FavoriteProducts;

import java.util.ArrayList;
import java.util.List;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.Holder>{

    ArrayList<FavoriteProducts> favorite_list= new ArrayList<>();
    public FavoriteListAdapter(List<FavoriteProducts> favProducts){
        favorite_list.addAll(favProducts);
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_data_model, parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        FavoriteProducts fav_product = favorite_list.get(position);
        holder.productImage.setImageResource(fav_product.getImg());
        holder.productName.setText(fav_product.getName());
        holder.productPrice.setText(fav_product.getPrice());
    }

    public void setFavProductsList(List<FavoriteProducts> products){
        favorite_list.clear();
        favorite_list.addAll(products);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return favorite_list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName;
        TextView productPrice;

        public Holder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImageFav);
            productName = itemView.findViewById(R.id.productNameFav);
            productPrice = itemView.findViewById(R.id.productPriceFav);
        }
    }

    public FavoriteProducts getProduct(int position){
        return favorite_list.get(position);
    }
}
