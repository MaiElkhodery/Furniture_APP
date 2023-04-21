package com.example.furniture_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.Product;
import com.example.furniture_app.Database.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoricalProductsAdapter extends RecyclerView.Adapter<CategoricalProductsAdapter.Holder> {

    ArrayList<Product> products= new ArrayList<>();
    ViewModel viewModel;
    private CategoryAdapterInterface listener;
    public CategoricalProductsAdapter(CategoryAdapterInterface listener){
        this.listener=listener;
        viewModel = HomeFragment.viewModel;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_model, parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position){
        Product product = products.get(position);
        holder.productImage.setImageResource(product.getImg());
        holder.productName.setText(String.valueOf(product.getName()));
        holder.productPrice.setText(String.valueOf(product.getPrice()));
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
            viewModel.updateProduct(product);
        });
        holder.itemView.setOnClickListener(view -> listener.onClick(product));
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
    public void setProducts(List<Product> products) {
        this.products.clear();
        this.products.addAll(products);
        notifyDataSetChanged();
    }


    public interface CategoryAdapterInterface{
        void onClick(Product product);
    }
}
