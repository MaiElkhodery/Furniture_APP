package com.example.furniture_app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.Product;
import com.example.furniture_app.Database.ViewModel;
import com.example.furniture_app.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements CategoriesAdapter.SetOnClickProductListener{

    FragmentHomeBinding homeBinding;
    ArrayList<Product> product_list=new ArrayList<>();
    ViewModel viewModel;
    CategoriesAdapter adapter;
    NavController navController;
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
        navController= Navigation.findNavController(view);
        insertProducts();
        initRecyclerView();
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = homeBinding.categoriesContainer;
        adapter = new CategoriesAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        viewModel.getAllProducts().observe(getViewLifecycleOwner(), products -> {
            Log.d("Observe", "Done");
            adapter.setProducts(products);
        });

    }
    public void insertProducts(){
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.seater_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.seater_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
        viewModel.insertProduct(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
    }

    @Override
    public void onClick() {
        navController.navigate(R.id.action_homeFragment_to_productDetailsFragment);
    }
}