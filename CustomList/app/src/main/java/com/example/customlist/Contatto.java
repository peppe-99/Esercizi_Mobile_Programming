package com.example.customlist;

import android.graphics.drawable.Drawable;

public class Contatto {
    private String nome;
    private String numero;
    private Drawable foto;

    public Contatto(String nome, String numero, Drawable foto) {
        this.nome = nome;
        this.numero = numero;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Contatto{" +
                "nome='" + nome + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
