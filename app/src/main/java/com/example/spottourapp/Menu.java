package com.example.spottourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spottourapp.dao.EventoDAO;
import com.example.spottourapp.model.Evento;

import java.io.File;

public class Menu extends AppCompatActivity {

    private ImageButton btn_search;
    private EditText nm_city;
    private TextView nm_evento1;
    private TextView nm_evento2;
    private TextView nm_evento3;
    private TextView tp_evento1;
    private TextView tp_evento2;
    private TextView tp_evento3;
    private TextView loc_evento1;
    private TextView loc_evento2;
    private TextView loc_evento3;
    private ImageView img_evento1;
    private ImageView img_evento2;
    private ImageView img_evento3;
    private String caminho_Aquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        nm_evento1 = findViewById(R.id.nm_evento1);
        nm_evento2 = findViewById(R.id.nm_evento2);
        nm_evento3 = findViewById(R.id.nm_evento3);

        tp_evento1 = findViewById(R.id.tipo_evento1);
        tp_evento2 = findViewById(R.id.tipo_evento2);
        tp_evento3 = findViewById(R.id.tipo_evento3);

        loc_evento1 = findViewById(R.id.local_evnto1);
        loc_evento2 = findViewById(R.id.local_evnto2);
        loc_evento3 = findViewById(R.id.local_evnto3);

        img_evento1 = findViewById(R.id.img_evento1);
        img_evento2 = findViewById(R.id.img_evento2);
        img_evento3 = findViewById(R.id.img_evento3);

        btn_search = findViewById(R.id.btn_search);
        nm_city = findViewById(R.id.nm_cidade);


        Evento event = new EventoDAO().buscaEventos();
        if(event != null){

            nm_evento1.setText(event.getNome().toString());
            tp_evento1.setText(event.getTipo().toString());
            loc_evento1.setText(event.getLocal().toString());
            caminho_Aquivo = event.getImage().toString();

            File imgFile = new File(caminho_Aquivo);
            if (imgFile.exists()) {

                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                img_evento1.setImageBitmap(bitmap);
            }
        }

    }

    public void PesquisarLocal(View v)
    {
        if(nm_city.getText().length() == 0)
        {
            return;
        }
        else
        {
            Intent in_city = new Intent(this, CityInfo.class);
            in_city.putExtra("namecity", nm_city.getText().toString());
            startActivity(in_city);
        }
    }

}

