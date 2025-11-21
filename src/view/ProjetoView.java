package src.view;

import src.model.Professor;
import src.model.ProfessorSubstituto;
import src.model.ProfessorVitalicio;
import src.model.Projeto;

import java.util.List;
import java.util.Scanner;

import src.controller.*;

public class ProjetoView {
    private Scanner scanner = new Scanner(System.in);
    private ProjetoController controller;

    public ProjetoView(ProjetoController controller) {
        this.controller = controller;
    }

    // MENU PRINCIPAL
    public void menu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU PROJETO ===");
            System.out.println("1 - Cadastrar Projeto");
            System.out.println("2 - Listar Projetos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarProjeto();
                    break;
                case 2:
                    listarProjeto();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // CADASTRAR PROJETO
    private void cadastrarProjeto() {
        System.out.println("\n=== Cadastro de Projeto ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Professor: ");
        String professor = scanner.nextLine();

        Projeto p = new Projeto(nome, professor);
        controller.cadastrarProjeto(p);

        System.out.println("Projeto cadastrado com sucesso!");
    }

    // LISTAR PROEJETOS
    private void listarProjeto() {
        List<Projeto> lista = controller.listarProjeto();

        System.out.println("\n=== LISTA DE PROJETOS ===");

        for (Projeto p : lista) {
            System.out.println(
                    p.getNome() + " | " +
                            p.getNome() + " | " +
                            p.getProfessor());
        }
    }

}
