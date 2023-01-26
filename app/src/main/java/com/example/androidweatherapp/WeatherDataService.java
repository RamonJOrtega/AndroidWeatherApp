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
import java.util.List;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_ID = "https://geocoding-api.open-meteo.com/v1/search?name=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID_A = "https://api.open-meteo.com/v1/forecast?latitude=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID_B = "&longitude=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID_C = "&hourly=temperature_2m";

    Context context;
    String cityID;
    String latitude;
    String longitude;


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
                latitude = "";
                longitude = "";
                try {
                    JSONArray cityInfoArr = response.getJSONArray("results");
                    JSONObject cityInfoObj = cityInfoArr.getJSONObject(0);
                    cityID = cityInfoObj.getString("id");
                    latitude = cityInfoObj.getString("latitude");
                    longitude = cityInfoObj.getString("longitude");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(context, " City ID = " + cityID + " Latitude = " + latitude + " Longitude = " + longitude, Toast.LENGTH_SHORT).show();
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

    public void getCityForecastByID(String cityID, String latitude, String longitude) {
        List<WeatherReportModel> report = new ArrayList<>();
        String url = QUERY_FOR_CITY_WEATHER_BY_ID_A + latitude + QUERY_FOR_CITY_WEATHER_BY_ID_B + longitude + QUERY_FOR_CITY_WEATHER_BY_ID_C;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
        //get the json object
        // get each item in the json object and assign to new WeatherReportModel object
    }
//
//    public List<WeatherReportModel> getCityForecastByName(String cityName) {
//
//    }
}
