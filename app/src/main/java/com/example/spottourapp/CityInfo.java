package com.example.spottourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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

public class CityInfo extends AppCompatActivity {

    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "2e9ad51c6670a4f87b4f45d169be4bf4";
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);

        Intent it = getIntent();
        String city = it.getStringExtra("namecity");

        result = findViewById(R.id.info_metereologia);

        String tempUrl = "http://api.weatherstack.com/current?access_key="+ appid +"&query="+ city;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);

               String output = "";
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("current");

                    double temp = jsonObjectMain.getDouble("temperature");
                    double feelsLike = jsonObjectMain.getDouble("feelslike");
                    int humidity = jsonObjectMain.getInt("humidity");
                    String wind = jsonObjectMain.getString("wind_speed");

                    JSONObject jsonObjectTime = jsonResponse.getJSONObject("location");
                    String time_real = jsonObjectTime.getString("localtime");

                    output += "Horário: " + time_real+
                              "\nTemperatura: "+ temp +" ºC" +
                              "\n Sensação Térmica: " + feelsLike + " ºC" +
                              "\n Humidade: " + humidity + " %" +
                              "\n Vento: " + wind + " Km/h";

                    result.setText(output);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}