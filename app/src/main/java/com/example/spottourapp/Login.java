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

            Intent intent = new Intent(getApplicationContext(), Menu.class);
            startActivity(intent);

            finish();

        }
        else {
            Toast.makeText(Login.this, "Usuario e/ou senha inválido" ,Toast.LENGTH_SHORT).show();
            password.setText("");
            id.requestFocus();

        }

    }

    private void WebService(String usr, String password)
    {
        //SOAPAction: "http://spottour/VerificaUsuario"
        //Namespace: http://spottour/
        //Method: VerificaUsuario
        //URL: http://spottour.application/WebServiceSpotTour/Service.asmx?WSDL

        SOAP_ACTION = "http://spottour/VerificaUsuario";
        METHOD_NAME = "VerificaUsuario";
        NAMESPACE = "http://spottour/";
        URL = "http://spottour.application/WebServiceSpotTour/Service.asmx";

        try {


            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("login", usr);
            request.addProperty("password", password);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            (new MarshalHashtable()).register(envelope);

            HttpTransportSE http = new HttpTransportSE(URL);

                    try {

                        //transporte.call(SOAP_ACTION, sobre);
                        Toast.makeText(this, "Deu Certo", Toast.LENGTH_SHORT).show();
                        http.call(SOAP_ACTION, envelope);

                        resultString = (SoapPrimitive) envelope.getResponse();

                        //SoapPrimitive resultado = (SoapPrimitive)sobre.getResponse();

                        //result = resultado.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            if (resultString.toString().equals("Erro")) {
                Toast.makeText(this, "Deu Ruim", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
                if (!resultString.equals(null)) {
                    Toast.makeText(this, "Deu Certo", Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch(Exception e){
                    e.printStackTrace();
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
