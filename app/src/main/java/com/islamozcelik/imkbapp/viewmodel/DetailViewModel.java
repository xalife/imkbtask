package com.islamozcelik.imkbapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.islamozcelik.imkbapp.DetailRepository;
import com.islamozcelik.imkbapp.models.DetailModel;

import org.json.JSONException;

import java.util.List;

public class DetailViewModel extends AndroidViewModel {

    DetailRepository detailRepository;
    private MutableLiveData<DetailModel> details;

    String id;
    public DetailViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<DetailModel> getDetails() throws JSONException{
        if (details == null){
            details = new MutableLiveData<DetailModel>();
            loadDetails();
            System.out.println("detail element: "+detailRepository.getDetail().getSymbol());
            details.postValue(detailRepository.getDetail());
        }
        return details;
    }

    public void loadDetails(){
        detailRepository = DetailRepository.getInstance(getApplication().getApplicationContext());
        detailRepository.loadData();
    }


}
