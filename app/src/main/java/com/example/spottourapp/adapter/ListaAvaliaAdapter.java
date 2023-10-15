package com.example.spottourapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.spottourapp.R;
import com.example.spottourapp.model.Avalia;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

public class ListaAvaliaAdapter extends BaseAdapter implements Serializable {
    private  static final long serialVersionUID = 555555555;
    private List<Avalia> listaAvalia;
    private Context context;
    private LayoutInflater layout;
    public ListaAvaliaAdapter(List<Avalia> listaAvalia, Context context) {
        this.listaAvalia = listaAvalia;
        this.context = context;
        this.layout = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaAvalia.size();
    }

    @Override
    public Avalia getItem(int i) {
        return listaAvalia.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Avalia avaliacao = listaAvalia.get(i);
        View v = layout.inflate(R.layout.item_avalicao,null);

        TextView comentario = v.findViewById(R.id.txtConsultaAvaliacaoComentario);
        TextView nota = v.findViewById(R.id.txtConsultaAvaliacaoNota);
        TextView usu = v.findViewById(R.id.txtConsultaAvaliacaoUsuario);

        comentario.setText(avaliacao.getComentario());
        nota.setText(avaliacao.getnAvalicao().toString());
        usu.setText(avaliacao.getUsuario());

        return v;
    }
}
