package com.example.spottourapp.dao;

import com.example.spottourapp.ConnectionHelper;
import com.example.spottourapp.model.Evento;
import com.example.spottourapp.model.Local;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocalDAO {

    public Local BuscarLocal(int count) {
        try {

            Connection conn = ConnectionHelper.conectar();
            if(conn != null){

                String sql = "SELECT TBL_LOCAL.ID_LOCAL, TBL_CIDADE.NM_CIDADE,  TBL_LOCAL.ID_IMG "+
                "FROM TBL_LOCAL, TBL_CIDADE, TBL_ESTADO, TBL_PAIS " +
                "WHERE TBL_LOCAL.ID_CID = TBL_CIDADE.ID_CIDADE " +
                "AND TBL_LOCAL.ID_EST = TBL_ESTADO.ID_ESTADO " +
                "AND TBL_LOCAL.ID_PAIS = TBL_PAIS.ID_PAIS " +
                "AND TBL_LOCAL.ID_LOCAL = " + count;

                Statement st = null;
                st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next())
                {
                    Local local = new Local();
                    local.setCod(rs.getInt(1));
                    local.setNome(rs.getString(2));
                    local.setImagem(rs.getString(3));

                    conn.close();
                    return local;
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  null;

    }

    public  Local BuscaLocal2(String cidade)
    {
        try {

            Connection conn = ConnectionHelper.conectar();
            if(conn != null){

                String sql = "SELECT TBL_CIDADE.NM_CIDADE, TBL_ESTADO.NM_ESTADO + ',' + TBL_PAIS.NM_PAIS, TBL_LOCAL.ID_DESC, TBL_LOCAL.ID_IMG" +
                        "                        FROM TBL_LOCAL, TBL_CIDADE, TBL_ESTADO, TBL_PAIS " +
                        "                        WHERE TBL_LOCAL.ID_CID = TBL_CIDADE.ID_CIDADE " +
                        "                        AND TBL_LOCAL.ID_EST = TBL_ESTADO.ID_ESTADO " +
                        "                        AND TBL_LOCAL.ID_PAIS = TBL_PAIS.ID_PAIS " +
                        "                        AND TBL_CIDADE.NM_CIDADE = '" +  cidade + "'";

                Statement st = null;
                st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next())
                {
                    Local local = new Local();
                    local.setNome(rs.getString(1));
                    local.setLocal2(rs.getString(2));
                    local.setDescricao(rs.getString(3));
                    local.setImagem(rs.getString(4));

                    conn.close();
                    return local;
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
