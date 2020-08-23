package com.islamozcelik.imkbapp.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.islamozcelik.imkbapp.R;
import com.islamozcelik.imkbapp.models.DetailModel;

import java.util.ArrayList;

public class DetailGridviewAdapter extends BaseAdapter {
    private Context ctx;
    DetailModel detailModel;
    ArrayList<String> dataToShow;

    public DetailGridviewAdapter(Context ctx, DetailModel detailModel){
        this.ctx = ctx;
        this.detailModel = detailModel;
        dataToShow = new ArrayList<>();
        dataToShow.add("Symbol: "+detailModel.getSymbol());
        dataToShow.add("Fiyat: "+detailModel.getPrice());
        dataToShow.add("%Fark: "+detailModel.getDifference());
        dataToShow.add("Hacim: "+detailModel.getVolume());
        dataToShow.add("Alış:"+detailModel.getBid());
        dataToShow.add("Satış: "+detailModel.getOffer());
        dataToShow.add("Günlük Düşüş: "+detailModel.getLowest());
        dataToShow.add("Günlük Yükseliş: "+detailModel.getHighest());
        dataToShow.add("Adet: "+detailModel.getCount());
        dataToShow.add("Tavan: "+detailModel.getMaximum());
        dataToShow.add("Taban: "+detailModel.getMinumum());
        if (detailModel.isDown()){
            dataToShow.add("up");
        }
        else {
            dataToShow.add("down");
        }


    }
    @Override
    public int getCount() {
        return dataToShow.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View t;
        if (convertView == null){
            if (dataToShow.get(position).equals("up") || dataToShow.get(position).equals("down")){
                t = new ImageView(ctx);
                int width = 80;
                int height = 80;
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);
                t.setLayoutParams(parms);
                if (dataToShow.get(position).equals("up"))
                    t.setBackgroundResource(R.drawable.ic_up);

                else
                    t.setBackgroundResource(R.drawable.ic_down);
            }
            else {
                t = new TextView(ctx);
                ((TextView) t).setText(dataToShow.get(position));
            }

        }
        else {
            t = convertView;
        }
        return t;
    }
}
