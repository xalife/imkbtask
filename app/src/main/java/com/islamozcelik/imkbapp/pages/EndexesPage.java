package com.islamozcelik.imkbapp.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.islamozcelik.imkbapp.R;
import com.islamozcelik.imkbapp.StockRepository;
import com.islamozcelik.imkbapp.adapter.StockAdapter;
import com.islamozcelik.imkbapp.models.Stocks;
import com.islamozcelik.imkbapp.viewmodel.StocksListingViewModel;

import org.json.JSONException;

import java.util.ArrayList;

public class EndexesPage extends AppCompatActivity {


    private StocksListingViewModel stocksListingViewModel;
    DrawerLayout drawerLayout;
    ImageView drawericon;
    NavigationView navigationView;
    StockRepository myRepo;
    ArrayList<Stocks> stocksArrayList;
    RecyclerView recyclerView;
     StockAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endexes_page);
        drawerLayout = findViewById(R.id.drawerLayoutOfexdex);
        drawericon = findViewById(R.id.drawer_icon);
        navigationView = findViewById(R.id.nagivationViewEndex);
        myRepo = StockRepository.getInstance(getApplicationContext());
        stocksArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview_endex);


        drawericon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getTitle().equals("Hisse ve Endeksler")){
                    myRepo.setPeriod("all");
                    startActivity(new Intent(EndexesPage.this,EndexesPage.class));
                }
                else if (item.getTitle().equals("Yükselenler")){
                    myRepo.setPeriod("increasing");
                    startActivity(new Intent(EndexesPage.this,EndexesPage.class));

                }
                else if (item.getTitle().equals("Düşenler")){
                    myRepo.setPeriod("decreasing");
                    startActivity(new Intent(EndexesPage.this,EndexesPage.class));
                }
                else if(item.getTitle().equals("volume30")){
                    myRepo.setPeriod("decreasing");
                    startActivity(new Intent(EndexesPage.this,EndexesPage.class));
                }
                else if(item.getTitle().equals("volume50")){
                    myRepo.setPeriod("decreasing");
                    startActivity(new Intent(EndexesPage.this,EndexesPage.class));
                }
                else if(item.getTitle().equals("volume100")){
                    myRepo.setPeriod("decreasing");
                    startActivity(new Intent(EndexesPage.this,EndexesPage.class));
                }
                return true;
            }
        });

        stocksListingViewModel = new ViewModelProvider(this).get(StocksListingViewModel.class);
        try {
            stocksListingViewModel.getStocks().observe(this, stocks -> {

                stocksArrayList.addAll(stocks);
                adapter = new StockAdapter(stocksArrayList,EndexesPage.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setHasFixedSize(true);
                adapter.notifyDataSetChanged();


            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
