package com.example.spottourapp.model;

public class Avalia {
    public Integer cod;
    public String  comentario;
    public Integer nAvalicao;
    public Integer loc;
    public String usuario;

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getnAvalicao() {
        return nAvalicao;
    }

    public void setnAvalicao(Integer nAvalicao) {
        this.nAvalicao = nAvalicao;
    }

    public Integer getLoc() {
        return loc;
    }

    public void setLoc(Integer loc) {
        this.loc = loc;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
