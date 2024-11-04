package com.motoboy.model;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean clienteIdValido = false;

        System.out.println("Bem-vindo ao sistema de pedidos!");

        while (!clienteIdValido) {
            // Solicita o ID do cliente
            System.out.print("Digite o ID do cliente: ");
            int clienteId = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            try {
                // Verifica se o clienteId é válido chamando o método clienteExiste
                if (PedidoService.clienteExiste(clienteId)) {
                    clienteIdValido = true;

                    // Solicita os detalhes do pedido
                    System.out.print("Digite o prato principal: ");
                    String pratoPrincipal = scanner.nextLine();

                    System.out.print("Digite a bebida: ");
                    String bebidas = scanner.nextLine();

                    System.out.print("Digite a sobremesa: ");
                    String sobremesas = scanner.nextLine();

                    // Adiciona o pedido usando o PedidoService
                    PedidoService.adicionarItemAoPedido(clienteId, pratoPrincipal, bebidas, sobremesas);

                } else {
                    System.out.println("ID do cliente inválido. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro ao verificar o cliente. Tente novamente.");
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}


