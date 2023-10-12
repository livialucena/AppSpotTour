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

        String ip = "192.168.1.6:1433";
        String db = "spottour2023";
        String user = "liviasz";
        String senha = "020916";


        String connString = "jdbc:jtds:sqlserver://" + ip+";databaseName="+db+";user="+user+";password="+senha+";";
        conn = DriverManager.getConnection(connString);
        return  conn;
    }

    @SuppressLint("NewApi")
    public  Connection connectionclass()
    {
        ip = "192.168.1.6";
        //ip = "spottour.database.windows.net";
        database = "spottour2023";
        usr = "liviasz";
        pass = "020916";
        port= "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + database + ";user=" +usr+";password="+pass+";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch(Exception ex){
            Log.e("Error ", ex.getMessage());
        }


        return  connection;
    }

}
