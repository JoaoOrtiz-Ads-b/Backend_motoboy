package com.motoboy.model;

import java.math.BigDecimal;

public class Pedido {
    private int id;
    private int clienteId;
    private String enderecoRetirada;
    private String enderecoEntrega;
    private BigDecimal total;
    private boolean aceito;

    // Construtor, Getters e Setters
    public Pedido(int id, int clienteId, String enderecoRetirada, String enderecoEntrega, BigDecimal total, boolean aceito) {
        this.id = id;
        this.clienteId = clienteId;
        this.enderecoRetirada = enderecoRetirada;
        this.enderecoEntrega = enderecoEntrega;
        this.total = total;
        this.aceito = aceito;
    }

    // Getters e Setters
    // toString() para facilitar o teste
}

