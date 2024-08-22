package com.ti2cc;
import java.util.Scanner;


public class Principal {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		DAO dao = new DAO();
		dao.conectar();
		int opcao;


		
		do {
            System.out.println("\n===== Menu dos Reinos Medievais =====");
            System.out.println("1. Listar Reinos");
            System.out.println("2. Inserir Reino");
            System.out.println("3. Excluir Reino");
            System.out.println("4. Atualizar Reino");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  

            
            switch (opcao) {
                case 1:  // Listar reinos
                    Reinos[] reinos = dao.getReinos();
                    System.out.println("==== Lista de Reinos Medievais ====");
                    if (reinos != null && reinos.length > 0) {
                        for (Reinos reino : reinos) {
                        	 System.out.println(reino);
                        }
                    } else {
                        System.out.println(" Erro: Nenhum Reino foi  encontrado.");
                    }
                    break;

                case 2:  // Inserir reinos
                    System.out.print("Digite o código do reino: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();  

                    System.out.print("Digite o nome do reino: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o país atual que o reino se encontra: ");
                    String pais = scanner.nextLine();

                    System.out.print("Digite o ano de fundação do reino: ");
                    int ano = scanner.nextInt();

                    Reinos novoReino = new Reinos(codigo, nome, pais, ano);
                    if (dao.inserirReino(novoReino)) {
                        System.out.println("Inserção com sucesso -> " + novoReino.toString());
                    } else {
                        System.out.println("Erro ao inserir reino.");
                    }
                    break;

                case 3:  // Excluir reino
                    System.out.print("Digite o código do reino que sera excluído: ");
                    int codigoExclusao = scanner.nextInt();
                    if (dao.excluirReino(codigoExclusao)) {
                        System.out.println("Reino excluído com sucesso.");
                    } else {
                        System.out.println("Erro ao excluir reino.");
                    }
                    break;

                case 4:  // Atualizar reino
                    System.out.print("Digite o código do reino a ser atualizado: ");
                    int codigoAtualizacao = scanner.nextInt();
                    scanner.nextLine();  

                    System.out.print("Digite o nome atualizado do reino: ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Digite o país atual do reino da forma correta: ");
                    String novoPais = scanner.nextLine();

                    System.out.print("Digite o ano de fundação do reino atualizado : ");
                    int novoAno = scanner.nextInt();

                    Reinos reinoAtualizado = new Reinos(codigoAtualizacao, novoNome, novoPais, novoAno);
                    if (dao.atualizarReino(reinoAtualizado)) {
                        System.out.println("Atualização com sucesso -> " + reinoAtualizado.toString());
                    } else {
                        System.out.println("Erro ao atualizar reino.");
                    }
                    break;

                case 5:  // Sair
                    System.out.println("Fim do Codigo...");
                    break;

                default:
                    System.out.println(" Erro: Só é possivel escolher os numeros de 0 a 5");
                    break;
            }
        } while (opcao != 5);

        scanner.close();
        dao.close();
    }
}