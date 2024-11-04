package com.motoboy.model;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Exibe um menu para o usuário
        System.out.println("Bem-vindo ao sistema de pedidos!");
        System.out.print("Digite o ID do cliente: ");
        int clienteId = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner

        System.out.print("Digite o prato principal: ");
        String pratoPrincipal = scanner.nextLine();

        System.out.print("Digite a bebida: ");
        String bebidas = scanner.nextLine();

        System.out.print("Digite a sobremesa: ");
        String sobremesas = scanner.nextLine();

        // Adiciona o pedido usando o PedidoService
        PedidoService.adicionarItemAoPedido(clienteId, pratoPrincipal, bebidas, sobremesas);

        scanner.close();
    }
}
