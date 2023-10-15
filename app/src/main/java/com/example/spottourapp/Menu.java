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
import android.widget.Toast;

import com.example.spottourapp.dao.EventoDAO;
import com.example.spottourapp.dao.LocalDAO;
import com.example.spottourapp.model.Evento;
import com.example.spottourapp.model.Local;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

    private ImageView nImageView1;
    private ImageView nImageView2;
    private ImageView nImageView3;
    private ImageView nImageView4;

    private TextView ImageViewText1;
    private TextView ImageViewText2;
    private TextView ImageViewText3;
    private TextView ImageViewText4;

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


        for (int i = 1; i < 4; i++)
        {
            if(i==1)
            {
                Evento event1 = new EventoDAO().buscaEventos(i);
                if(event1 != null){

                    nm_evento1.setText(event1.getNome().toString());
                    tp_evento1.setText(event1.getTipo().toString());
                    loc_evento1.setText(event1.getLocal().toString());
                    String caminho_Aquivo = event1.getImage().toString();

                    try {
                        InputStream inputStream = getAssets().open(caminho_Aquivo);

                        Bitmap bitmapImage = BitmapFactory.decodeStream(inputStream);
                        img_evento1.setImageBitmap(bitmapImage);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if(i==2)
            {
                Evento event2 = new EventoDAO().buscaEventos(i);
                if(event2 != null){

                    nm_evento2.setText(event2.getNome().toString());
                    tp_evento2.setText(event2.getTipo().toString());
                    loc_evento2.setText(event2.getLocal().toString());
                    String caminho_Aquivo = event2.getImage().toString();


                    try {
                        InputStream inputStream = getAssets().open(caminho_Aquivo);

                        Bitmap bitmapImage = BitmapFactory.decodeStream(inputStream);
                        img_evento2.setImageBitmap(bitmapImage);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if(i==3)
            {
                Evento event3 = new EventoDAO().buscaEventos(i);
                if(event3 != null){

                    nm_evento3.setText(event3.getNome().toString());
                    tp_evento3.setText(event3.getTipo().toString());
                    loc_evento3.setText(event3.getLocal().toString());
                    String caminho_Aquivo = event3.getImage().toString();


                    try {
                        InputStream inputStream = getAssets().open(caminho_Aquivo);

                        Bitmap bitmapImage = BitmapFactory.decodeStream(inputStream);
                        img_evento3.setImageBitmap(bitmapImage);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
        nImageView1 = findViewById(R.id.ImageView1);
        nImageView2 = findViewById(R.id.ImageView2);
        nImageView3 = findViewById(R.id.ImageView3);
        nImageView4 = findViewById(R.id.ImageView4);

        ImageViewText1 = findViewById(R.id.ImageViewText1);
        ImageViewText2 = findViewById(R.id.ImageViewText2);
        ImageViewText3 = findViewById(R.id.ImageViewText3);
        ImageViewText4 = findViewById(R.id.ImageViewText4);

        for (int i = 1; i <5; i++)
        {
            if(i==1)
            {
                Local local1 = new LocalDAO().BuscarLocal(i);
                if(local1 != null){

                    String caminho_Aquivo = local1.getImagem().toString();
                    ImageViewText1.setText(local1.getNome().toString());


                    try {
                        InputStream inputStream = getAssets().open(caminho_Aquivo);

                        Bitmap bitmapImage = BitmapFactory.decodeStream(inputStream);
                        nImageView1.setImageBitmap(bitmapImage);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if(i==2)
            {
                Local local2 = new LocalDAO().BuscarLocal(i);
                if(local2 != null){

                    String caminho_Aquivo = local2.getImagem().toString();
                    ImageViewText2.setText(local2.getNome().toString());


                    try {
                        InputStream inputStream = getAssets().open(caminho_Aquivo);

                        Bitmap bitmapImage = BitmapFactory.decodeStream(inputStream);
                        nImageView2.setImageBitmap(bitmapImage);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if(i==3)
            {
                Local local3 = new LocalDAO().BuscarLocal(i);
                if(local3 != null){

                    String caminho_Aquivo = local3.getImagem().toString();
                    ImageViewText3.setText(local3.getNome().toString());


                    try {
                        InputStream inputStream = getAssets().open(caminho_Aquivo);

                        Bitmap bitmapImage = BitmapFactory.decodeStream(inputStream);
                        nImageView3.setImageBitmap(bitmapImage);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if(i==4)
            {
                Local local4 = new LocalDAO().BuscarLocal(i);
                if(local4 != null){

                    String caminho_Aquivo = local4.getImagem().toString();
                    ImageViewText4.setText(local4.getNome().toString());


                    try {
                        InputStream inputStream = getAssets().open(caminho_Aquivo);

                        Bitmap bitmapImage = BitmapFactory.decodeStream(inputStream);
                        nImageView4.setImageBitmap(bitmapImage);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
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
    public void ConsultaLocal(View v)
    {
        Intent intent = new Intent(getApplicationContext(), CityInfo.class);
        if(v.equals(nImageView1)){
            intent.putExtra("namecity", ImageViewText1.getText().toString());
        }
        else if(v.equals(nImageView2))
        {
            intent.putExtra("namecity", ImageViewText2.getText().toString());
        }
        else if(v.equals(nImageView3))
        {
            intent.putExtra("namecity", ImageViewText3.getText().toString());
        }
        else if(v.equals(nImageView4))
        {
            intent.putExtra("namecity", ImageViewText4.getText().toString());
        }

        startActivity(intent);
    }
}

