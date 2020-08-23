package com.islamozcelik.imkbapp;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.islamozcelik.imkbapp.entities.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HandShakeWork {
    private static HandShakeWork instance = null;
    private String key;
    private String iv;
    private String auth;
    Context ctx;

    private HandShakeWork(Context ctx) {

        this.ctx = ctx;
        try {
            init(getDeviceId(ctx));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static HandShakeWork getInstance(Context ctx){
        if (instance == null){
            instance = new HandShakeWork(ctx);

        }
        return instance;
    }





    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
    public static String getDeviceId(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }
    public void init(String deviceID) throws JSONException{
        JSONObject datas = new JSONObject();
        datas.put("deviceID", deviceID);
        datas.put("systemVersion", Build.VERSION.SDK_INT);
        datas.put("platformName","Android");
        datas.put("deviceModel",Build.MODEL);
        datas.put("manifacturer",Build.MANUFACTURER);

        RequestQueue queue = Volley.newRequestQueue(ctx.getApplicationContext());
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Constants.HANDSHAKE_URL, datas, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    key = response.getString("aesKey");
                    iv = response.getString("aesIV");
                    auth = response.getString("authorization");

                } catch (Exception e) {

                    e.printStackTrace();
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

                return params;
            }
        };



        queue.getCache().clear();
        queue.add(req);






    }
}
