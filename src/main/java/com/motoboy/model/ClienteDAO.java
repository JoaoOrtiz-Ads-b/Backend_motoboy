package com.motoboy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private final Connection connection;

    public ClienteDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    // Método para verificar se um cliente já existe no banco de dados
    public boolean clienteExiste(Cliente cliente) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Clientes WHERE nome = ? AND endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se já existir
            }
        }
        return false;
    }

    // Método para adicionar um cliente
    public void adicionarCliente(Cliente cliente) throws SQLException {
        // Validações
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().trim().isEmpty()) {
            System.out.println("Endereço não pode ser vazio.");
            return;
        }

        // Verifica se o cliente já existe
        if (clienteExiste(cliente)) {
            System.out.println("Cliente já existe.");
            return; // Retorna imediatamente se o cliente existir
        }

        // Adiciona o cliente caso não exista
        String sql = "INSERT INTO Clientes (nome, endereco) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.executeUpdate();
            System.out.println("Cliente adicionado com sucesso!"); // Mensagem de sucesso
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar cliente: " + e.getMessage());
        }
    }


    // Método para listar todos os clientes
    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getString("endereco")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    // Método para fechar a conexão com o banco de dados
    public void fecharConexao() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

