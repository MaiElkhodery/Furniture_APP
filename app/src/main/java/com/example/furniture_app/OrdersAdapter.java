package com.example.furniture_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.Product;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.Holder> {
    ArrayList<Product> ordersList=new ArrayList<>();
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details_model, parent,false);
        return new OrdersAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Product product = ordersList.get(position);
        holder.productImage.setImageResource(product.getImg());
        holder.productName.setText(product.getName());
        holder.productQuantity.setText(String.valueOf(product.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    static class Holder extends RecyclerView.ViewHolder{
        ImageView productImage;
        TextView productQuantity;
        TextView productName;
        TextView productPrice;
        public Holder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImageOrder);
            productName=itemView.findViewById(R.id.productNameOrder);
            productPrice=itemView.findViewById(R.id.productPriceOrder);
            productQuantity=itemView.findViewById(R.id.productQuantityValueOrder);
        }
    }
    public void setOrdersList(List<Product> products){
        ordersList.clear();
        ordersList.addAll(products);
        notifyDataSetChanged();
    }

}
