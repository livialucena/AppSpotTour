package com.example.spottourapp.model;

import kotlin.jvm.internal.PropertyReference0Impl;

public class Local {

    private  int cod;
    private String imagem;
    private String nome;

    private String descricao;

    private String local2;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal2() {
        return local2;
    }

    public void setLocal2(String local2) {
        this.local2 = local2;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
