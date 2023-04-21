package com.example.furniture_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.Product;
import com.example.furniture_app.Database.ViewModel;
import com.example.furniture_app.databinding.FragmentShoppingBinding;

public class ShoppingFragment extends Fragment implements CartAdapter.SetOnChangeCartListener{


    ViewModel viewModel;
    RecyclerView recyclerView;
    CartAdapter adapter;
    FragmentShoppingBinding binding;
    NavController navController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentShoppingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel=new ViewModel(getActivity().getApplication());
        navController= Navigation.findNavController(view);
        initRecyclerView();
        updateCartList();
    }
    public void initRecyclerView(){
        recyclerView = binding.cartContainer ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CartAdapter(this);
        recyclerView.setAdapter(adapter);
    }
    public void updateCartList(){
        viewModel.getProductsInCart().observe(getViewLifecycleOwner(),products -> {
            adapter.setCartList(products);
        });
    }

    @Override
    public void delete(Product product) {
        product.setIsProductInCart(false);
        viewModel.updateProduct(product);
    }

    @Override
    public void updateProductQuantity(Product product) {
        viewModel.updateProduct(product);
    }

    @Override
    public void goToProductDetails(Product product) {
        navController.navigate(ShoppingFragmentDirections.actionShoppingFragmentToProductDetailsFragment(product));
    }
}