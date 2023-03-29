package com.example.furniture_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.furniture_app.Database.Product;
import com.example.furniture_app.databinding.FragmentProductDetailsBinding;

public class ProductDetailsFragment extends Fragment {

    Product product;
    FragmentProductDetailsBinding detailsBinding;

    public ProductDetailsFragment(Product product){
        this.product=product;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        detailsBinding = FragmentProductDetailsBinding.inflate(inflater,container,false);
        detailsBinding.productImage.setImageResource(product.getImg());
        detailsBinding.productName.setText(product.getName());
        detailsBinding.productDescription.setText(product.getDescription());
        detailsBinding.productPrice.setText(product.getPrice());
        return detailsBinding.getRoot();
    }
}