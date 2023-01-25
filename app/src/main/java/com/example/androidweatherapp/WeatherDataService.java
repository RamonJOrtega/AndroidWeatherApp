package com.example.androidweatherapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_ID = "https://geocoding-api.open-meteo.com/v1/search?name=";
    Context context;
    String cityID;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(String cityID);
    }

    public void getCityID(String cityName, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_CITY_ID + cityName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                cityID = "";
                try {
                    JSONArray cityInfoArr = response.getJSONArray("results");
                    JSONObject cityInfoObj = cityInfoArr.getJSONObject(0);
                    cityID = cityInfoObj.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(context, "City ID = " + cityID, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(cityID);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something is very wrong");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
        //return cityID;
    }

    public  getCityForecastByID(String cityID) {
        List<WeatherReportModel> report = new ArrayList<>()
    }
//
//    public List<WeatherReportModel> getCityForecastByName(String cityName) {
//
//    }
}
