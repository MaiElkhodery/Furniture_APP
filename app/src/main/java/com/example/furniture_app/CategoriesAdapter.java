package com.example.furniture_app;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.FavoriteProducts;
import com.example.furniture_app.Database.Product;
import com.example.furniture_app.Database.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.Holder> {

    ArrayList<Product> products;
    ViewModel viewModel;
    private CategoryAdapterInterface listener;
    FavoriteProducts favProduct;
    public CategoriesAdapter(CategoryAdapterInterface listener){
        this.listener=listener;
        products = new ArrayList<>();
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
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
        Log.d("product-id", product.getId()+"");
        if(product.isInFavorite()){
            holder.favoriteButton.setImageResource(R.drawable.favorite_full);
        }else{
            holder.favoriteButton.setImageResource(R.drawable.favorite);
        }
        favProduct=new FavoriteProducts(product.getImg(),product.getName(),product.getPrice());
        holder.favoriteButton.setOnClickListener(view -> {
            if(product.isInFavorite()){
                holder.favoriteButton.setImageResource(R.drawable.favorite);
                product.setInFavorite(false);
                viewModel.deleteProductFromFav(favProduct);

            }else{
                holder.favoriteButton.setImageResource(R.drawable.favorite_full);
                product.setInFavorite(true);
                viewModel.insertProductToFav(favProduct);
            }

            listener.updateFavList();
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
        this.products=new ArrayList<>();
        this.products.addAll(products);
        notifyDataSetChanged();
    }


    public interface CategoryAdapterInterface{
        void onClick(Product product);
        void updateFavList();
    }
}
