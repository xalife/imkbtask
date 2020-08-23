package com.islamozcelik.imkbapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.islamozcelik.imkbapp.StockRepository;
import com.islamozcelik.imkbapp.adapter.StockAdapter;
import com.islamozcelik.imkbapp.models.Stocks;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class StocksListingViewModel extends AndroidViewModel {

    StockRepository myRepo;
    private MutableLiveData<List<Stocks>> stocks;
    private ArrayList<Stocks> newList;

    public StocksListingViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<List<Stocks>> getStocks() throws JSONException {
        if (stocks == null){
            stocks = new MutableLiveData<List<Stocks>>();
            loadStocks();
            stocks.setValue(myRepo.getStocksArrayList());


        }
        return stocks;
    }
    private void loadStocks() throws JSONException {

          myRepo = StockRepository.getInstance(getApplication().getApplicationContext());
          myRepo.loadData();
    }


}
