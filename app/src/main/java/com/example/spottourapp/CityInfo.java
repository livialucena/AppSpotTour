package com.example.spottourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spottourapp.dao.LocalDAO;
import com.example.spottourapp.model.Local;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class CityInfo extends AppCompatActivity {

    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "2e9ad51c6670a4f87b4f45d169be4bf4";
    private TextView result;
    private TextView slocal;
    private TextView local_Comp;
    private TextView descricao;
    private ImageView imagem;

    private  String usuario;
    private  int codLocal;

    private ImageButton btn_voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);

        Intent it = getIntent();
        String city = it.getStringExtra("namecity");
        usuario = it.getStringExtra("usuario");

        result = findViewById(R.id.info_metereologia);
        slocal = findViewById(R.id.textlocal);
        local_Comp = findViewById(R.id.textlocal_comp);
        descricao = findViewById(R.id.text_descricao);
        imagem = findViewById(R.id.imageView);

        Local local = new LocalDAO().BuscaLocal2(city);
        if(local != null){

            String caminho_Aquivo = local.getImagem().toString();

            codLocal = local.getCod();
            slocal.setText(local.getNome().toString());
            local_Comp.setText(local.getLocal2().toString());
            descricao.setText(local.getDescricao().toString());

            try {
                InputStream inputStream = getAssets().open(caminho_Aquivo);

                Bitmap bitmapImage = BitmapFactory.decodeStream(inputStream);
                imagem.setImageBitmap(bitmapImage);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }




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

                    output += "Data/Horário:  " + time_real+
                              "\nTemperatura:  "+ temp +" ºC" +
                              "\nSensação Térmica: " + feelsLike + " ºC" +
                              "\nHumidade: " + humidity + " %" +
                              "\nVento: " + wind + " Km/h";

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
    public void Voltar(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Menu.class);
        startActivity(intent);

        finish();
    }
    public void Avalicao(View v)

    {   Intent intent = new Intent(getApplicationContext(), Avalicao.class);
        intent.putExtra("codigoLocal", codLocal);
        intent.putExtra("nmLocal", slocal.getText().toString());
        intent.putExtra("usuario", usuario);
        startActivity(intent);

    }



}