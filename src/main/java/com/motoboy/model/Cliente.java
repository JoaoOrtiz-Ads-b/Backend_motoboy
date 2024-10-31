package com.motoboy.model;

public class Cliente {
    private int id;
    private String nome;
    private String endereco;

    // Construtor completo com todos os atributos
    public Cliente(int id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente id=" + id + ", nome='" + nome + "', endereco='" + endereco + "'";
    }

    // Getter para 'id'
    public int getId() {
        return id;
    }

    // Setter para 'id'
    public void setId(int id) {
        this.id = id;
    }

    // Getter para 'nome'
    public String getNome() {
        return nome;
    }

    // Setter para 'nome'
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para 'endereco'
    public String getEndereco() {
        return endereco;
    }

    // Setter para 'endereco'
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
