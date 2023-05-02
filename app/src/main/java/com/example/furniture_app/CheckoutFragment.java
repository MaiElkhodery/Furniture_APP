package com.example.furniture_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.Product;
import com.example.furniture_app.Database.ViewModel;
import com.example.furniture_app.databinding.FragmentCheckoutBinding;

import java.util.ArrayList;

public class CheckoutFragment extends Fragment {

    ViewModel viewModel;
    RecyclerView recyclerView;
    OrdersAdapter adapter;
    NavController navController;
    FragmentCheckoutBinding binding;
    TextView orderPrice;
    TextView orderDelivery;
    TextView orderTotal;
    CheckBox checkBox;
    AppCompatButton confirmButton;
    ArrayList<Product> products;
    int price=0,delivery=30,total=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel=new ViewModel(getActivity().getApplication());
        navController= Navigation.findNavController(view);
        orderPrice=binding.orderPriceValue;
        orderDelivery=binding.orderDeliveryValue;
        orderTotal=binding.orderTotalValue;
        checkBox=binding.checkPaymentMethod;
        confirmButton=binding.confirmOrderButton;
        initRecyclerView();
        updateCartList();
        setTotalPrice();
    }
    private void initRecyclerView(){
        recyclerView = binding.checkoutRecyclerView ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OrdersAdapter();
        recyclerView.setAdapter(adapter);
    }
    private void updateCartList(){
        viewModel.getProductsInCart().observe(getViewLifecycleOwner(),products -> {
            adapter.setOrdersList(products);
            this.products.addAll(products);
        });
    }
    private void setTotalPrice(){
        for (int i=0;i<products.size();i++){
            price+=products.get(i).getPrice();
        }
        total=delivery+price;
        orderPrice.setText(String.valueOf(price));
        orderDelivery.setText(String.valueOf(delivery));
        orderTotal.setText(String.valueOf(total));
    }
    private void onClickConfirm(){
        confirmButton.setOnClickListener(view -> {
            if(checkBox.isChecked()){
                //add to orders
            }else{
                Toast.makeText(getContext(), "Check Payment Method", Toast.LENGTH_SHORT).show();
            }
        });
    }
}