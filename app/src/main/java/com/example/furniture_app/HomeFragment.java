package com.example.furniture_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.Product;
import com.example.furniture_app.Database.User;
import com.example.furniture_app.Database.ViewModel;
import com.example.furniture_app.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    FragmentHomeBinding homeBinding;
    ArrayList<User> list=new ArrayList<>();
    ViewModel viewModel;
    CategoriesAdapter adapter;
    public static HomeFragment newInstance() {
        return  new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModel(getActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false);
        return homeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        insertProducts();
        initRecyclerView();
        viewModel.getAllProducts().observe(getViewLifecycleOwner(), products -> {
            adapter.setProducts(products);
        });
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = homeBinding.categoriesContainer;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new CategoriesAdapter(viewModel.getAllProducts());
        recyclerView.setAdapter(adapter);
    }
    public void insertProducts(){
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
    }
}