package com.islamozcelik.imkbapp.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.islamozcelik.imkbapp.R;
import com.islamozcelik.imkbapp.adapter.DetailGridviewAdapter;
import com.islamozcelik.imkbapp.models.GraphData;
import com.islamozcelik.imkbapp.viewmodel.DetailViewModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DetailPage extends AppCompatActivity {
    GridView gridView;
    private DetailViewModel detailViewModel;
    DetailGridviewAdapter adapter;
    LineChart chart;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        gridView = findViewById(R.id.detailgridview);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        chart = findViewById(R.id.lineChart);

        try {
            detailViewModel.getDetails().observe(this,details -> {
                if (details.getGraphData() != null){
                    ArrayList<Entry> liste =new ArrayList<Entry>();
                    for (GraphData d : details.getGraphData()){
                        liste.add(new Entry((float)d.getDay(),(float)d.getValue()));
                    }
                    List<GraphData> list = (List<GraphData>) details.getGraphData();
                    LineDataSet set = new LineDataSet(liste,"son değişimler");

                    LineData data = new LineData(set);
                    chart.setData(data);
                    chart.getDescription().setText("Değişimler var");
                    chart.animate();
                }
                System.out.println("veri seti obserbe ediliyor");
                adapter = new DetailGridviewAdapter(DetailPage.this,details);
                gridView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                System.out.println("adpter haber edildi");



            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public void goagain(View view) {
        startActivity(new Intent(this,DetailPage.class));
    }
}
