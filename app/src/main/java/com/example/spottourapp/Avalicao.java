package com.example.spottourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.spottourapp.adapter.ListaAvaliaAdapter;
import com.example.spottourapp.dao.AvalicaoDAO;
import com.example.spottourapp.model.Avalia;

import java.util.List;

public class Avalicao extends AppCompatActivity {

    private ListView listView;
    private ListaAvaliaAdapter adapter;
    private List<Avalia> lista;

    private int codLocal;
    private String slocal;
    private String usuario;

    private EditText comentario;
    private EditText nota;

    private Button btnSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avalicao);

        Intent it = getIntent();
        codLocal= it.getIntExtra("codigoLocal", 0);
        slocal= it.getStringExtra("nmLocal");
        usuario= it.getStringExtra("usuario");

        comentario = findViewById(R.id.txtComentario);
        nota = findViewById(R.id.txtNota);
        btnSalvar = findViewById(R.id.btnSalvar);

        listView = findViewById(R.id.listaAvalicao);
        Preenche(codLocal);
    }

    private  void Preenche(int codLocal)
    {
        AvalicaoDAO dao = new AvalicaoDAO();
        lista = dao.BuscaAvalicao(codLocal);

        adapter = new ListaAvaliaAdapter(lista, this);
        listView.setAdapter(adapter);

    }
    public void Voltar (View v)
    {
        Intent intent = new Intent(getApplicationContext(), CityInfo.class);
        intent.putExtra("namecity", slocal);
        intent.putExtra("usuario", usuario);
        startActivity(intent);

        finish();
    }
    public void Salvar(View v)
    {
        Avalia av = new Avalia();
        av.setComentario(comentario.getText().toString());
        av.setnAvalicao(nota.getText().length());
        av.setLoc(codLocal);
        av.setUsuario(usuario);

        AvalicaoDAO dao = new AvalicaoDAO();
        dao.Cadastrar(av);

        Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        comentario.setText("");
        nota.setText("");

        Preenche(codLocal);
    }

}