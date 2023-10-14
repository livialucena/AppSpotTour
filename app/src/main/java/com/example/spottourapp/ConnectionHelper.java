package com.example.spottourapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    Connection con;
    String usr, pass, ip, port, database;


    public static Connection conectar() throws ClassNotFoundException, SQLException {

        Connection conn = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Class.forName("net.sourceforge.jtds.jdbc.Driver");

        String ip = "192.168.1.4:1433";
        String db = "spottour2023";
        String user = "liviasz";
        String senha = "020916";


        String connString = "jdbc:jtds:sqlserver://" + ip+";databaseName="+db+";user="+user+";password="+senha+";";
        conn = DriverManager.getConnection(connString);
        return  conn;
    }

}
