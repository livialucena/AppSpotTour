package com.example.spottourapp.dao;

import com.example.spottourapp.ConnectionHelper;
import com.example.spottourapp.model.usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

    public usuario selecionarUsuario(String usuario, String senha){

        try {
            Connection conn = ConnectionHelper.conectar();
            if(conn != null){
                String sql = "select * from tbl_usr where tbl_usr = '"+usuario+"' and pass_usr = '"+ senha + "'";
                Statement st = null;
                st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next())
                {
                    usuario usu = new usuario();
                    usu.setUsuario(rs.getString(1));;
                    usu.setSenha((rs.getString(2)));

                    conn.close();
                    return usu;
                }

            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  null;
    }

}
