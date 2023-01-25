package com.example.androidweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btn_cityID, btn_getWeatherByID, btn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_weatherReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign some values to all the controls on layout
        btn_cityID = findViewById(R.id.btn_getCityID);
        btn_getWeatherByID = findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);

        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReport = findViewById(R.id.lv_weatherReports);

        //these are click listeners for the buttons
        btn_cityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + et_dataInput.getText().toString();

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String cityID = "";
                        try {
                            JSONArray cityInfoArr = response.getJSONArray("results");
                            JSONObject cityInfoObj = cityInfoArr.getJSONObject(0);
                            cityID = cityInfoObj.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, "City ID = " + cityID, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }
                });

                MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
            }
        });

        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You clicked me 2", Toast.LENGTH_SHORT).show();
            }
        });
        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You typed " + et_dataInput.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}