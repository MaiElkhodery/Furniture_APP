package com.example.furniture_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.Product;
import com.example.furniture_app.Database.ViewModel;

import java.util.ArrayList;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.Holder>{

    ArrayList<Product> favorite_list;
    ViewModel viewModel;
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_model, parent,false);
        getAllFavProducts();
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Product fav_product = favorite_list.get(position);
        holder.productImage.setImageResource(fav_product.getImg());
        holder.productName.setText(fav_product.getName());
        holder.productPrice.setText(fav_product.getPrice());
    }

    private void getAllFavProducts(){
        viewModel.getAllFavProducts().observeForever(products -> {
            favorite_list = new ArrayList<>();
            favorite_list.addAll(products);
        });
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

        public ImageView getProductImage() {
            return productImage;
        }

        public TextView getProductName() {
            return productName;
        }

        public TextView getProductPrice() {
            return productPrice;
        }
    }
}
