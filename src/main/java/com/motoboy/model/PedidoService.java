package com.motoboy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoService {

    public static boolean clienteExiste(int clienteId) throws SQLException {
        String sql = "SELECT 1 FROM clientes WHERE cliente_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, clienteId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Retorna true se o cliente existe
        }
    }

    public static void adicionarItemAoPedido(int clienteId, String pratoPrincipal, String bebidas, String sobremesas) {
        // Verifica se o cliente existe antes de adicionar o pedido
        try {
            if (!clienteExiste(clienteId)) {
                System.out.println("Cliente com ID " + clienteId + " não existe. Pedido não será adicionado.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        String sql = "INSERT INTO pedidos (cliente_id, prato_principal, bebidas, sobremesas) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, clienteId);
            statement.setString(2, pratoPrincipal);
            statement.setString(3, bebidas);
            statement.setString(4, sobremesas);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Pedido adicionado com sucesso!");

                // Recupera o ID do pedido recém-inserido
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int pedidoId = generatedKeys.getInt(1);


                    exibirDetalhesPedido(pedidoId);
                }
            } else {
                System.out.println("Falha ao adicionar o pedido.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void exibirDetalhesPedido(int pedidoId) {
        String sql = "SELECT * FROM pedidos WHERE pedido_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pedidoId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int clienteId = resultSet.getInt("cliente_id");
                String pratoPrincipal = resultSet.getString("prato_principal");
                String bebidas = resultSet.getString("bebidas");
                String sobremesas = resultSet.getString("sobremesas");

                System.out.println("Detalhes do Pedido:");
                System.out.println("Pedido ID: " + pedidoId);
                System.out.println("Cliente ID: " + clienteId);
                System.out.println("Prato Principal: " + pratoPrincipal);
                System.out.println("Bebidas: " + bebidas);
                System.out.println("Sobremesas: " + sobremesas);
            } else {
                System.out.println("Pedido não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        int clienteId = 6; // ID do cliente que está fazendo o pedido
        String pratoPrincipal = "Pizza de Muçarela";
        String bebidas = "Pepsi";
        String sobremesas = "Pudim";

        adicionarItemAoPedido(clienteId, pratoPrincipal, bebidas, sobremesas);
    }
}
