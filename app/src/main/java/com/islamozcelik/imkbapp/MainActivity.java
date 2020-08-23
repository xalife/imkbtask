package com.islamozcelik.imkbapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.islamozcelik.imkbapp.pages.EndexesPage;

import org.json.JSONException;


public class MainActivity extends AppCompatActivity {

    HandShakeWork work;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        work = HandShakeWork.getInstance(this);

    }
    public void gotoendex(View v){
        startActivity(new Intent(this, EndexesPage.class));
    }


}
