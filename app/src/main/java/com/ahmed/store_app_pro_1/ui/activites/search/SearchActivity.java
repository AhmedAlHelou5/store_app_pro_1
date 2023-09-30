package com.ahmed.store_app_pro_1.ui.activites.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Window;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivitySearchBinding;
import com.ahmed.store_app_pro_1.ui.adapters.SearchRvAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnSearchRvClickListener;
import com.ahmed.store_app_pro_1.ui.models.LastSearchModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements OnSearchRvClickListener {

    ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );

        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });




        setSupportActionBar(binding.toolbar);

        binding.edTextSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {




            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                ArrayList<LastSearchModel> lastSearchModels = Utils.getLastSearchModels(s.toString());
                SearchRvAdapter searchAdapter = new SearchRvAdapter(lastSearchModels, new OnSearchRvClickListener() {
                    @Override
                    public void onItemClick(String text) {
                        Log.d("TAG Activity", "onClick: "+text.toString());
                        binding.edTextSearch.setText(text);



                    }
                }
                );


                binding.rvLastSearch.setAdapter(searchAdapter);
                binding.rvLastSearch.setHasFixedSize(true);
                binding.rvLastSearch.setClipToPadding(false);
                binding.rvLastSearch.setClipChildren(false);

                binding.rvLastSearch.setLayoutManager(new
                        LinearLayoutManager(getBaseContext(),
                        RecyclerView.VERTICAL,false));






            }

            @Override
            public void afterTextChanged(Editable s) {

                ArrayList<LastSearchModel> lastSearchModels = Utils.getLastSearchModels(s.toString());
                SearchRvAdapter searchAdapter = new SearchRvAdapter(lastSearchModels, new OnSearchRvClickListener() {
                    @Override
                    public void onItemClick(String text) {
                        Log.d("TAG Activity", "onClick: "+text.toString());
                        binding.edTextSearch.setText(text);


                    }
                });


                binding.rvLastSearch.setAdapter(searchAdapter);
                binding.rvLastSearch.setHasFixedSize(true);
                binding.rvLastSearch.setClipToPadding(false);
                binding.rvLastSearch.setClipChildren(false);

                binding.rvLastSearch.setLayoutManager(new
                        LinearLayoutManager(getBaseContext(),
                        RecyclerView.VERTICAL,false));
            }
        });





        ArrayList<LastSearchModel> lastSearchModels = Utils.FillLastSearch();
        SearchRvAdapter searchAdapter = new SearchRvAdapter(lastSearchModels, new OnSearchRvClickListener() {
            @Override
            public void onItemClick(String text) {
                Log.d("TAG Activity", "onClick: "+text.toString());
                binding.edTextSearch.setText(text);






            }
        });


        binding.rvLastSearch.setAdapter(searchAdapter);
        binding.rvLastSearch.setHasFixedSize(true);
        binding.rvLastSearch.setClipToPadding(false);
        binding.rvLastSearch.setClipChildren(false);

        binding.rvLastSearch.setLayoutManager(new
                LinearLayoutManager(this,
                RecyclerView.VERTICAL,false));





    }

    @Override
    public void onItemClick(String text) {

        Intent intent = getIntent();
        intent.getStringExtra("text");
        binding.edTextSearch.setText(intent.getStringExtra("text"));

    }
}