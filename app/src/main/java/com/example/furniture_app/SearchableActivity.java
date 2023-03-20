package com.example.furniture_app;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SearchableActivity extends AppCompatActivity {
    String search_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        //getting query
        Intent search_intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(search_intent.getAction())){
            search_query = search_intent.getStringExtra(SearchManager.QUERY);
        }
        //then we should do searching..
        //then representing these data
    }
}