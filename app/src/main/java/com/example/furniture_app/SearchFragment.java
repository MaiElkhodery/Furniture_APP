package com.example.furniture_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.Product;
import com.example.furniture_app.Database.ViewModel;
import com.example.furniture_app.databinding.FragmentSearchBinding;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements FavoriteListAdapter.SetOnClickProductListener {

    ViewModel viewModel;
    RecyclerView recyclerView;
    FavoriteListAdapter adapter;
    FragmentSearchBinding binding;
    NavController navController;
    private final String key = "name";
    ArrayList<Product> resultOfSearch=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSearchBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        viewModel=new ViewModel(getActivity().getApplication());
        viewModel.getProduct(getArguments().getString(key)).observe(getViewLifecycleOwner(),products ->{
            resultOfSearch.addAll(products);
            if(resultOfSearch.size()!=0){
                initRecyclerView();
                updateSearchList();
            }else{
                Toast.makeText(getContext(), "NotFound!", Toast.LENGTH_SHORT).show();
            }
        });
        onClickBack();
    }

    private void initRecyclerView(){
        recyclerView = binding.searchFragmentContainer ;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new FavoriteListAdapter(this);
        recyclerView.setAdapter(adapter);
    }
    private void updateSearchList(){
        viewModel.getProduct(getArguments().getString(key)).observe(getViewLifecycleOwner(),products -> adapter.setFavProductsList(products));
    }
    private void onClickBack(){
        binding.backIconInSearch.setOnClickListener(view -> {
            navController.navigate(SearchFragmentDirections.actionSearchFragmentToHomeFragment());
        });
    }

    @Override
    public void onClick(Product product) {
        navController.navigate(SearchFragmentDirections.actionSearchFragmentToProductDetailsFragment(product));
    }
}