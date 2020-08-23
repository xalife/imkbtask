package com.islamozcelik.imkbapp;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Base64;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.islamozcelik.imkbapp.cipher.CipherClass;
import com.islamozcelik.imkbapp.entities.Constants;
import com.islamozcelik.imkbapp.models.Stocks;
import com.islamozcelik.imkbapp.pages.EndexesPage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class StockRepository {

    private static StockRepository instance = null;
    Context context;
    String key;
    String iv;
    byte[] encryptedPeriod;
    String encryptedPostData;
    String period = "all";
    CipherClass cipherClass;
    HandShakeWork handShakeWork;

    private ArrayList<Stocks> stocksArrayList;



    public void loadData(){
        try {
            key = handShakeWork.getKey();
            iv = handShakeWork.getIv();
            byte[] aeskey = Base64.decode(key, Base64.DEFAULT);
            byte[] aesiv = Base64.decode(iv, Base64.DEFAULT);
            cipherClass = new CipherClass();
            encryptedPeriod = cipherClass.encrypt(period,aeskey,aesiv);
            encryptedPostData = Base64.encodeToString(encryptedPeriod,Base64.DEFAULT);
            getStockData(Constants.STOCK_URL,encryptedPostData,handShakeWork.getAuth());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setPeriod(String period){
        this.period = period;
    }
    public String getPeriod(){
        return period;
    }

    private StockRepository(Context ctx){
        this.context =ctx;
        stocksArrayList = new ArrayList<>();

        handShakeWork = HandShakeWork.getInstance(ctx);
    }
    public static StockRepository getInstance(Context context){
        if (instance == null){
            instance = new StockRepository(context);

        }
        return instance;
    }
    public ArrayList<Stocks> getStocksArrayList(){
        return stocksArrayList;
    }

    public void getStockData(String url, String period, final String auth) throws JSONException {
        JSONObject datas1 = new JSONObject();
        datas1.put("period", period);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, datas1, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    System.out.println(response.toString());
                    //System.out.println(response.get("stocks"));
                    JSONArray jsonArray = response.getJSONArray("stocks");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jresponse = jsonArray.getJSONObject(i);
                        Stocks stocksElement = new Stocks();
                        stocksElement.setId(jresponse.getInt("id"));
                        stocksElement.setDown(jresponse.getBoolean("isDown"));
                        stocksElement.setUp(jresponse.getBoolean("isUp"));
                        stocksElement.setBid(jresponse.getDouble("bid"));
                        stocksElement.setDifference(jresponse.getDouble("difference"));
                        stocksElement.setOffer(jresponse.getDouble("offer"));
                        stocksElement.setPrice(jresponse.getDouble("price"));
                        stocksElement.setVolume(jresponse.getDouble("volume"));
                        stocksElement.setSymbol(new CipherClass().decyrpt(jresponse.getString("symbol"),Base64.decode(key,Base64.DEFAULT),Base64.decode(iv,Base64.DEFAULT)));

                        stocksArrayList.add(stocksElement);


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.toString());
                    System.out.println("hata aslında buradda");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        }){


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("X-VP-Authorization", auth);
                System.out.println("AUTH DEĞERİ "+ auth);

                return params;
            }
        };



        queue.getCache().clear();
        queue.add(req);
    }


}
