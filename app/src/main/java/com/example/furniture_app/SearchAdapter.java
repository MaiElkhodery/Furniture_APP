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

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Holder> {

    SetOnChangeCartListener listener;
    private int quantity;
    private int totalPrice;
    ArrayList<Product> cartList=new ArrayList<>();
    public SearchAdapter(SetOnChangeCartListener listener){
        this.listener=listener;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_data_model, parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Product product = cartList.get(position);
        holder.productImage.setImageResource(product.getImg());
        holder.productName.setText(product.getName());
        holder.productQuantity.setText(String.valueOf(product.getQuantity()));

        totalPrice = product.getPrice() * product.getQuantity();
        holder.productPrice.setText(String.valueOf(totalPrice));

        holder.cancelProductButton.setOnClickListener(view -> {
            listener.delete(product);
        });
        holder.addButton.setOnClickListener(view -> {
            quantity=product.getQuantity();
            product.setQuantity(++quantity);
            listener.updateProductQuantity(product);
        });
        holder.subButton.setOnClickListener(view -> {
            quantity=product.getQuantity();
            if(quantity>0)
                --quantity;
            if (quantity==0)
                product.setIsProductInCart(false);
            product.setQuantity(quantity);
            listener.updateProductQuantity(product);
        });
        holder.itemView.setOnClickListener(view -> listener.goToProductDetails(product));
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    static class Holder extends RecyclerView.ViewHolder{

        ImageView productImage;
        TextView productQuantity;
        TextView productName;
        TextView productPrice;
        AppCompatImageButton cancelProductButton;
        AppCompatImageButton addButton;
        AppCompatImageButton subButton;
        public Holder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImageShopping);
            productName=itemView.findViewById(R.id.productNameShopping);
            productPrice=itemView.findViewById(R.id.productPriceShopping);
            productQuantity=itemView.findViewById(R.id.quantityShopping);
            cancelProductButton=itemView.findViewById(R.id.cancelShopping);
            addButton=itemView.findViewById(R.id.addButtonShopping);
            subButton=itemView.findViewById(R.id.subButtonShopping);
        }
    }
    public void setCartList(List<Product> products){
        cartList.clear();
        cartList.addAll(products);
        notifyDataSetChanged();
    }
    public interface SetOnChangeCartListener{
        void delete(Product product);
        void updateProductQuantity(Product product);
        void goToProductDetails(Product product);
    }
}
