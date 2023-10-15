package com.example.spottourapp.dao;

import com.example.spottourapp.Avalicao;
import com.example.spottourapp.ConnectionHelper;
import com.example.spottourapp.model.Avalia;
import com.example.spottourapp.model.usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AvalicaoDAO {

    private  Connection conn = null;
    public List<Avalia> BuscaAvalicao(int loc){

        List<Avalia> lista = new ArrayList<Avalia>();
        try {
            conn = ConnectionHelper.conectar();
            if(conn != null){
                String sql = "SELECT ID_AVALIA, COMENTARIO, AVALICAO, TBL_LOCAL.ID_LOCAL, ID_USR" +
                        "  FROM TBL_AVALICAO, TBL_LOCAL" +
                        "  WHERE TBL_AVALICAO.ID_LOCAL = TBL_LOCAL.ID_LOCAL" +
                        "  AND  TBL_AVALICAO.ID_LOCAL = " + loc ;

                Statement st = null;
                st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next())
                {
                    Avalia av = new Avalia();
                    av.setCod(rs.getInt(1));
                    av.setComentario(rs.getString(2));
                    av.setnAvalicao(rs.getInt(3));
                    av.setLoc(rs.getInt(4));
                    av.setUsuario(rs.getString(5));

                    lista.add(av);

                }
                conn.close();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  lista;
    }
    public void Cadastrar(Avalia av)
    {
        try {
            executeSql("INSERT INTO TBL_AVALICAO (ID_AVALIA, COMENTARIO, AVALICAO, ID_LOCAL, ID_USR) VALUES((SELECT COUNT(*)+1 FROM TBL_AVALICAO), '"+ av.getComentario()+"', "+ av.getnAvalicao().toString() +","+ av.getLoc().toString()+", '" + av.getUsuario()+ "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void executeSql(String sql) throws SQLException, ClassNotFoundException {
            conn = ConnectionHelper.conectar();
            if(conn != null)
            {

                Statement st = conn.createStatement();
                st.executeQuery(sql);
                conn.close();
            }

    }


}
