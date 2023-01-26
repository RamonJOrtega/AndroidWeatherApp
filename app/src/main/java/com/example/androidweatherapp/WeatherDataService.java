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
                    latitude = Long.toString(cityInfoObj.getLong("latitude"));
                    longitude = Long.toString(cityInfoObj.getLong("longitude"));
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

    public interface ForeCastByIDResponse {
        void onError(String message);
        void onResponse(WeatherReportModel weatherReportModel);
    }

    public void getCityForecastByID(String cityID, String latitude, String longitude, ForeCastByIDResponse foreCastByIDResponse) {
        List<WeatherReportModel> report = new ArrayList<>();
        String url = QUERY_FOR_CITY_WEATHER_BY_ID_A + latitude + QUERY_FOR_CITY_WEATHER_BY_ID_B + longitude + QUERY_FOR_CITY_WEATHER_BY_ID_C;
        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    // get the first item
                    WeatherReportModel single_day_today = new WeatherReportModel();

                    single_day_today.setLatitude(response.getLong("latitude"));
                    single_day_today.setLongitude(response.getLong("longitude"));
                    single_day_today.setElevation(response.getLong("elevation"));
                    single_day_today.setGenerationtime_ms(response.getLong("generationtime_ms"));
                    single_day_today.setUtc_offset_seconds(response.getInt("utc_offset_seconds"));
                    single_day_today.setTimezone_abbreviation(response.getString("timezone_abbreviation"));
                    single_day_today.setHourly(response.getJSONObject("hourly"));
                    single_day_today.setHourly_units(response.getJSONObject("hourly_units"));
                    single_day_today.setCurrent_weather(response.getJSONObject("current_weather"));

                    foreCastByIDResponse.onResponse(single_day_today);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
