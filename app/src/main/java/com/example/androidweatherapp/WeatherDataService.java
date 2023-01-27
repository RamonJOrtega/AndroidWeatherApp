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
    public static final String QUERY_FOR_CITY_NAME = "https://geocoding-api.open-meteo.com/v1/get?id=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID_A = "https://api.open-meteo.com/v1/forecast?latitude=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID_B = "&longitude=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID_C = "&temperature_unit=fahrenheit&hourly=temperature_2m&current_weather=true&timezone=auto&daily=temperature_2m_min&daily=temperature_2m_max&daily=weathercode";

    Context context;
    String cityID;
    String cityName;
    String latitude;
    String longitude;
    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(String cityID, String latitude, String longitude);
    }
    public void getCityID(String cityName, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_CITY_ID + cityName;
        System.out.println("did we get our city name by ID query at?: " + url);
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
                volleyResponseListener.onResponse(cityID, latitude, longitude);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something is very wrong");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public interface CityNameByIDResponseListener {
        void onError(String message);
        void onResponse(String cityName, String latitude, String longitude);
    }
    public void getCityName (String cityID, CityNameByIDResponseListener cityNameByIDResponseListener) {
        String url = QUERY_FOR_CITY_NAME + cityID;
        System.out.println("did we get our city name by ID query at?: " + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                cityName = "";
                latitude = "";
                longitude = "";
                try {
                    cityName = response.getString("name");
                    latitude = Long.toString(response.getLong("latitude"));
                    longitude = Long.toString(response.getLong("longitude"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("no response");
                }
                Toast.makeText(context, " City ID = " + cityID + " Latitude = " + latitude + " Longitude = " + longitude, Toast.LENGTH_SHORT).show();
                cityNameByIDResponseListener.onResponse(cityName, latitude, longitude);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show();
                cityNameByIDResponseListener.onError("Something is very wrong");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public interface ForeCastByIDResponse {
        void onError(String message);
        void onResponse(List<WeatherReportModel> weatherReportModels);
    }
    public void getCityForecastByID(String cityID, String latitude, String longitude, ForeCastByIDResponse foreCastByIDResponse) {
        List<WeatherReportModel> weatherReportModels = new ArrayList<>();
        String url = QUERY_FOR_CITY_WEATHER_BY_ID_A + latitude + QUERY_FOR_CITY_WEATHER_BY_ID_B + longitude + QUERY_FOR_CITY_WEATHER_BY_ID_C;
        System.out.println("going in for cityForeCastByID at url " + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // get the first item
                    JSONObject daily = response.getJSONObject("daily");
                    JSONArray  date = daily.getJSONArray("time");
                    JSONArray temperature_2m_min = daily.getJSONArray("temperature_2m_min");
                    JSONArray temperature_2m_max = daily.getJSONArray("temperature_2m_max");
                    JSONArray weathercode = daily.getJSONArray("weathercode");

                    System.out.println("forecast response received for days = " + daily.length());

                    for (int i = 0; i < date.length(); i++) {

                        WeatherReportModel one_day_weather = new WeatherReportModel();
                        one_day_weather.setDay(date.getString(i));
                        one_day_weather.setDayLowTemp(temperature_2m_min.getLong(i));
                        one_day_weather.setDayHighTemp(temperature_2m_max.getLong(i));
                        one_day_weather.setWeathercode(weathercode.getInt(i));

                        one_day_weather.setLatitude(response.getLong("latitude"));
                        one_day_weather.setLongitude(response.getLong("longitude"));
                        one_day_weather.setElevation(response.getLong("elevation"));
                        one_day_weather.setGenerationtime_ms(response.getLong("generationtime_ms"));
                        one_day_weather.setUtc_offset_seconds(response.getInt("utc_offset_seconds"));
                        one_day_weather.setTimezone_abbreviation(response.getString("timezone_abbreviation"));
                        one_day_weather.setDaily_units(response.getJSONObject("daily_units"));
                        one_day_weather.setDaily(response.getJSONObject("daily"));


                        weatherReportModels.add(one_day_weather);
                        foreCastByIDResponse.onResponse(weatherReportModels);
                    }

                } catch (JSONException e) {
                    System.out.println("huh?");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("huh bro?");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
        //get the json object
        // get each item in the json object and assign to new WeatherReportModel object
    }

    public interface GetCityForecastByNameCallback {
        void onError(String message);
        void onResponse(List<WeatherReportModel> weatherReportModels);
    }
    public void getCityForecastByName(String cityName, GetCityForecastByNameCallback getCityForecastByNameCallback) {
        getCityID(cityName, new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }
            @Override
            public void onResponse(String cityID, String latitude, String longitude) {
                getCityForecastByID(cityID, latitude, longitude, new ForeCastByIDResponse() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        getCityForecastByNameCallback.onResponse(weatherReportModels);
                    }
                });
            }
        });

    }
}
