package com.islamozcelik.imkbapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.islamozcelik.imkbapp.DetailRepository;
import com.islamozcelik.imkbapp.R;
import com.islamozcelik.imkbapp.StockRepository;
import com.islamozcelik.imkbapp.cipher.CipherClass;
import com.islamozcelik.imkbapp.models.DetailModel;
import com.islamozcelik.imkbapp.models.Stocks;
import com.islamozcelik.imkbapp.pages.DetailPage;

import java.util.ArrayList;
import java.util.Arrays;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.MyViewModel> {
    private ArrayList<Stocks> stocksArrayList;
    private StockRepository repository;
    private Context ctx;
    DetailRepository detailRepository;
    public StockAdapter(ArrayList<Stocks> stocksArrayList,Context ctx){
        this.ctx = ctx;
        this.stocksArrayList = stocksArrayList;
        detailRepository = DetailRepository.getInstance(ctx);
        repository = StockRepository.getInstance(ctx);
    }
    public StockAdapter(){

    }

    @NonNull
    @Override
    public MyViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_row_item,parent,false);
        return new MyViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewModel holder, int position) {
        /*
        String base64encodedsymbol = stocksArrayList.get(position).getSymbol();
        String decyrptedsymbol = "";
        try {
            System.out.println("KEY: "+repository.getKey());
            System.out.println("IV: "+repository.getIv());

            decyrptedsymbol = new CipherClass().decyrpt(base64encodedsymbol, Base64.decode(repository.getKey(), Base64.DEFAULT),Base64.decode(repository.getIv(), Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }

         */
        holder.symbol.setText(stocksArrayList.get(position).getSymbol());
        holder.price.setText(String.format("%1$,.2f", stocksArrayList.get(position).getPrice()));
        holder.difference.setText(String.format("%1$,.2f",stocksArrayList.get(position).getDifference()));
        holder.volume.setText(String.format("%1$,.2f",stocksArrayList.get(position).getVolume()));
        holder.purchase.setText(String.format("%1$,.2f",stocksArrayList.get(position).getBid()));
        holder.sell.setText(String.format("%1$,.2f",stocksArrayList.get(position).getOffer()));

        holder.imageView.setImageResource((stocksArrayList.get(position).getUp() ? R.drawable.ic_up : R.drawable.ic_down));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailRepository.setId(stocksArrayList.get(position).getId()+"");
                ctx.startActivity(new Intent(ctx, DetailPage.class).putExtra("position",position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return stocksArrayList.size();
    }

    public class MyViewModel extends RecyclerView.ViewHolder {
        TextView symbol,price,difference,volume,purchase,sell;
        ImageView imageView;
        LinearLayout linearLayout;
        public MyViewModel(@NonNull View itemView) {
            super(itemView);
            symbol = itemView.findViewById(R.id.symbolid);
            linearLayout = itemView.findViewById(R.id.stockitem);
            price = itemView.findViewById(R.id.priceid);
            difference = itemView.findViewById(R.id.differenceid);
            volume = itemView.findViewById(R.id.volumeid);
            purchase = itemView.findViewById(R.id.purchaseid);
            sell = itemView.findViewById(R.id.sellid);
            imageView = itemView.findViewById(R.id.changeid);
        }
    }
}
