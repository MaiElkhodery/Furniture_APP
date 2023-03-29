package com.example.furniture_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.Product;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.Holder> {

    ArrayList<Product> products;
    public CategoriesAdapter(LiveData<ArrayList<Product>>  productsData){
        products=productsData.getValue();
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_model, parent);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position){
        Product product = products.get(position);
        holder.productImage.setImageResource(product.getImg());
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
        if(product.isInFavorite()){
            holder.favoriteButton.setImageResource(R.drawable.favorite_full);
        }else{
            holder.favoriteButton.setImageResource(R.drawable.favorite);
        }
        holder.favoriteButton.setOnClickListener(view -> {
            if(product.isInFavorite()){
                holder.favoriteButton.setImageResource(R.drawable.favorite);
                product.setInFavorite(false);
            }else{
                holder.favoriteButton.setImageResource(R.drawable.favorite_full);
                product.setInFavorite(true);
            }
        });
        holder.itemView.setOnClickListener(view -> {
            Navigation.findNavController(holder.itemView).navigate(HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(product));
        });
    }

    static class Holder extends RecyclerView.ViewHolder{

        ImageView productImage;
        AppCompatImageButton favoriteButton;
        TextView productName;
        TextView productPrice;
        public Holder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName=itemView.findViewById(R.id.productName);
            productPrice=itemView.findViewById(R.id.productPrice);
            favoriteButton=itemView.findViewById(R.id.favoriteButton);
        }
    }
    @Override
    public int getItemCount() {
        return products.size();
    }
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

}
