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
        System.out.println("Cliente já existe."); // Mensagem para cliente existente
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
