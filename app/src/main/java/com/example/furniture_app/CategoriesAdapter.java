package com.example.furniture_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.Product;
import com.example.furniture_app.Database.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.Holder> {

    ArrayList<Product> products;
    ViewModel viewModel;
    Context context;
    private SetOnClickProductListener listener;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public CategoriesAdapter(Context context,SetOnClickProductListener listener){
        this.context = context;
        this.listener=listener;
        products = new ArrayList<>();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModel.class);
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
        Log.d("IsProductNull", product.getName());
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
                Log.d("Favorite", "NotYet");
                holder.favoriteButton.setImageResource(R.drawable.favorite);
                product.setInFavorite(false);
                viewModel.deleteProductFromFav(product);

            }else{
                Log.d("Favorite", "it is");
                holder.favoriteButton.setImageResource(R.drawable.favorite_full);
                product.setInFavorite(true);
                viewModel.insertProductToFav(product);
            }

        });
        holder.itemView.setOnClickListener(view -> {
            //Navigation.findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_productDetailsFragment);
            listener.onClick(product);
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
        Log.d("getItemCount", products.size()+"");
        return products.size();
    }
    public void setProducts(List<Product> products) {
        this.products=new ArrayList<>();
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    public interface SetOnClickProductListener{
        void onClick(Product product);
    }
}
