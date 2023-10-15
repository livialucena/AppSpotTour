package com.example.spottourapp.dao;

import com.example.spottourapp.ConnectionHelper;
import com.example.spottourapp.model.Evento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    public Evento buscaEventos(int count) {

        try {
            Connection conn = ConnectionHelper.conectar();
            if(conn != null){

                String sql = "SELECT TBL_EVENTO.ID_EVENTO, TBL_EVENTO.NM_EVENTO, TBL_EVENTO.DESC_EVENTO, TBL_EVENTO.CAM_IMG, TBL_TIPO.NM_TIPO, TBL_ESTADO.NM_ESTADO + ',' + TBL_PAIS.NM_PAIS " +
                            "  FROM TBL_EVENTO, TBL_TIPO, TBL_LOCAL_EVENTO, TBL_ESTADO, TBL_PAIS " +
                            "  WHERE TBL_EVENTO.TIPO_EVENTO = TBL_TIPO.ID_TIPO " +
                            "  AND TBL_LOCAL_EVENTO.ID_EST = TBL_ESTADO.ID_ESTADO " +
                            "  AND TBL_LOCAL_EVENTO.ID_PAIS = TBL_PAIS.ID_PAIS " +
                            "  AND  TBL_LOCAL_EVENTO.ID_EVENTO = TBL_EVENTO.ID_EVENTO" +
                            " AND TBL_EVENTO.ID_EVENTO = " + count;

                Statement st = null;
                st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next())
                {
                    Evento evento = new Evento();
                    evento.setCod(rs.getInt(1));;
                    evento.setNome(rs.getString(2));;
                    evento.setDescricao(rs.getString(3));;
                    evento.setImage(rs.getString(4));
                    evento.setTipo(rs.getString(5));;
                    evento.setLocal(rs.getString(6));;

                    conn.close();
                    return evento;
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
