package com.example.farhan.myapplication.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hobby {

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("praticanti")
    @Expose
    private List<String> praticanti = null;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getPraticanti() {
        return praticanti;
    }

    public void setPraticanti(List<String> praticanti) {
        this.praticanti = praticanti;
    }

}
