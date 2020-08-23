package com.islamozcelik.imkbapp;

import android.content.Context;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.islamozcelik.imkbapp.cipher.CipherClass;
import com.islamozcelik.imkbapp.entities.Constants;
import com.islamozcelik.imkbapp.models.DetailModel;
import com.islamozcelik.imkbapp.models.GraphData;
import com.islamozcelik.imkbapp.models.Stocks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailRepository {
    Context context;
    String key;
    String iv;
    String auth;
    String id ="1";
    private DetailModel detailElement;
    private static DetailRepository instance = null;
    HandShakeWork handShakeWork;

    private DetailRepository(Context context){
        this.context = context;
        handShakeWork = HandShakeWork.getInstance(context);
        detailElement =  new DetailModel();
    }
    public static DetailRepository getInstance(Context ctx){
        if (instance == null){
            instance = new DetailRepository(ctx);
        }
        return instance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DetailModel getDetail() {
        return detailElement;
    }
    public void loadData(){
        key = handShakeWork.getKey();
        iv = handShakeWork.getIv();
        auth = handShakeWork.getAuth();
        byte[] aeskey = Base64.decode(key, Base64.DEFAULT);
        byte[] aesiv = Base64.decode(iv, Base64.DEFAULT);
        CipherClass cipherClass = new CipherClass();

        try {
            byte[] encryptedid = cipherClass.encrypt(id,aeskey,aesiv);
            String encodedIDbase64 = Base64.encodeToString(encryptedid,Base64.DEFAULT);
            getdetaildata(Constants.DETAIL_URL,encodedIDbase64,auth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDetails(DetailModel details) {
        this.detailElement = details;
    }

    private void getdetaildata(String url, String id, final String auth) throws JSONException {
        JSONObject datas1 = new JSONObject();
        datas1.put("id", id);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, datas1, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jresponse) {


                try {
                    System.out.println(jresponse.toString());

                        detailElement.setBid(jresponse.getDouble("bid"));
                        detailElement.setDown(jresponse.getBoolean("isDown"));
                        detailElement.setUp(jresponse.getBoolean("isUp"));
                        detailElement.setCount(jresponse.getInt("count"));
                        detailElement.setDifference(jresponse.getDouble("difference"));
                        detailElement.setOffer(jresponse.getDouble("offer"));
                        detailElement.setHighest(jresponse.getDouble("highest"));
                        detailElement.setLowest(jresponse.getDouble("lowest"));
                        detailElement.setMaximum(jresponse.getDouble("maximum"));
                        detailElement.setMinumum(jresponse.getDouble("minimum"));
                        detailElement.setPrice(jresponse.getDouble("price"));
                        detailElement.setSymbol(new CipherClass().decyrpt(jresponse.getString("symbol"), Base64.decode(key,Base64.DEFAULT),Base64.decode(iv,Base64.DEFAULT)));

                        detailElement.setVolume(jresponse.getDouble("volume"));
                        JSONArray innerjsonArray = jresponse.getJSONArray("graphicData");
                        ArrayList<GraphData> list = new ArrayList<>();
                        for (int j=0;j<innerjsonArray.length();j++){
                            JSONObject jsonObject = innerjsonArray.getJSONObject(j);
                            int day = jsonObject.getInt("day");
                            double value = jsonObject.getDouble("value");
                            list.add(new GraphData(day,value));

                        }
                        detailElement.setGraphData(list);


                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.toString());
                    System.out.println("hata aslında buradda");
                }
                System.out.println("Bu detailed element: "+detailElement.getSymbol());
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
