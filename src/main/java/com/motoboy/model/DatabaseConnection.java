package com.motoboy.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_motoboy"; // URL do banco de dados
    private static final String USER = "root"; // Usuário do MySQL
    private static final String PASSWORD = "Tesla1856."; // Senha do MySQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
