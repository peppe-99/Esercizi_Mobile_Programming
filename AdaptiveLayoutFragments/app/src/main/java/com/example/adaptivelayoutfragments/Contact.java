package com.example.adaptivelayoutfragments;

import android.graphics.drawable.Drawable;

public class Contact {
    private String nome;
    private String numero;
    private String email;
    private String indirizzo;
    private Drawable foto;

    public Contact(String nome, String numero, String email, String indirizzo) {
        this.nome = nome;
        this.numero = numero;
        this.email = email;
        this.indirizzo = indirizzo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"{" +
                "nome='" + nome + '\'' +
                ", numero='" + numero + '\'' +
                ", email='" + email + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
