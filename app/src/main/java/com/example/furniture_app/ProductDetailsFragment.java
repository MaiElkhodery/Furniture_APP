package com.example.furniture_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.furniture_app.Database.Product;
import com.example.furniture_app.Database.ViewModel;
import com.example.furniture_app.databinding.FragmentProductDetailsBinding;

public class ProductDetailsFragment extends Fragment {

    Product product;
    ViewModel viewModel;
    FragmentProductDetailsBinding detailsBinding;
    private int quantity=0;
    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        product=ProductDetailsFragmentArgs.fromBundle(getArguments()).getProduct();
        detailsBinding = FragmentProductDetailsBinding.inflate(inflater,container,false);
        detailsBinding.productImage.setImageResource(product.getImg());
        detailsBinding.productName.setText(product.getName());
        detailsBinding.productDescription.setText(product.getDescription());
        detailsBinding.productPrice.setText(String.valueOf(product.getPrice()));
        return detailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModel(getActivity().getApplication());
        navController= Navigation.findNavController(view);
        onClickTextDescription();
        addButton();
        subButton();
        addToCartButton();
        backToHome();
    }
    private void onClickTextDescription(){
        detailsBinding.productDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = detailsBinding.productDescription;
                if (textView.getMaxLines() == 3) {
                    textView.setMaxLines(Integer.MAX_VALUE);
                    textView.setEllipsize(null);
                } else {
                    textView.setMaxLines(3);
                    textView.setEllipsize(TextUtils.TruncateAt.END);
                }
            }
        });
    }
    private void addButton(){
        detailsBinding.addButton.setOnClickListener(view -> {
            quantity++;
            detailsBinding.productQuantityTextView.setText(String.valueOf(quantity));
        });
    }
    private void subButton(){
        detailsBinding.subButton.setOnClickListener(view -> {
            if(quantity>0){quantity--;}
            detailsBinding.productQuantityTextView.setText(String.valueOf(quantity));
        });
    }
    private void addToCartButton(){
        detailsBinding.AddToCartButton.setOnClickListener(view -> {
            if(quantity == 0){
                Toast.makeText(getContext(),"Quantity is Zero",Toast.LENGTH_SHORT);
            }else{
                product.setIsProductInCart(true);
                product.setQuantity(quantity);
                viewModel.updateProduct(product);
            }
        });
    }
    private void backToHome(){
        detailsBinding.backIcon.setOnClickListener(view -> {
            navController.popBackStack();
        });
    }
}