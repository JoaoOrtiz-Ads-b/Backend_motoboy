package com.motoboy.model;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();

            // Adicionar um cliente
            //Cliente cliente = new Cliente(0, "", "");
            //clienteDAO.adicionarCliente(cliente);
            //System.out.println("Cliente adicionado com sucesso!");

            // Listar todos os clientes
            System.out.println("Lista de Clientes:");
            clienteDAO.listarClientes().forEach(System.out::println);

            // Fechar conexão
            clienteDAO.fecharConexao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

