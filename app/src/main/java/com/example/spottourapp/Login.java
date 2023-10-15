package com.example.spottourapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spottourapp.dao.UsuarioDAO;
import com.example.spottourapp.model.usuario;
import com.google.android.gms.common.ConnectionResult;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalHashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import kotlin.jvm.internal.PropertyReference0Impl;

public class Login extends AppCompatActivity {

    private String SOAP_ACTION;
    private String METHOD_NAME;
    private String NAMESPACE;
    private String URL;
    private EditText id;
    private EditText password;
    private Button butEntra;

    private String usuario;
    private SoapPrimitive resultString;

    private Connection connect;
    private String ConnectionResult = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        butEntra = findViewById(R.id.btn_entrar);
        id = findViewById(R.id.txt_Login);
        password = findViewById(R.id.txt_pass);

    }

    public void Login(View v)
    {
        String usuario = id.getText().toString();
        String senha = password.getText().toString();

        if(id.getText().length() == 0 || password.getText().length() == 0)
        {
            MensagemErro();
            return;
        }

        usuario usu = new UsuarioDAO().selecionarUsuario(usuario, senha);
        if(usu != null){

            usuario = usu.getUsuario().toString();
            Intent intent = new Intent(getApplicationContext(), Menu.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);

            finish();

        }
        else {
            Toast.makeText(Login.this, "Usuario e/ou senha inválido" ,Toast.LENGTH_SHORT).show();
            password.setText("");
            id.requestFocus();

        }

    }
    private void MensagemErro()
    {
        ConstraintLayout errorConstraintLayout = findViewById(R.id.errorConstraintLayout);
        View view = LayoutInflater.from(Login.this).inflate(R.layout.error_dialog, errorConstraintLayout);
        Button errorClose = view.findViewById(R.id.errorClose);

        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        errorClose.findViewById(R.id.errorClose).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                alertDialog.dismiss();
                Toast.makeText(Login.this,"Fechado" ,Toast.LENGTH_SHORT).show();
            }

        });
        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

}
