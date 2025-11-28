package src.view;

import src.model.Professor;
import src.model.ProfessorVitalicio;
import src.model.Projeto;

import java.util.List;
import java.util.Scanner;

import src.controller.ProjetoController;
import src.controller.ProfessorController;

public class ProjetoView {

    private Scanner scanner = new Scanner(System.in);
    private ProjetoController projetoController;
    private ProfessorController professorController;

    public ProjetoView(ProjetoController projetoController, ProfessorController professorController) {
        this.projetoController = projetoController;
        this.professorController = professorController;
    }

    // MENU PRINCIPAL
    public void menu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU PROJETOS ===");
            System.out.println("1 - Cadastrar Projeto");
            System.out.println("2 - Listar Projetos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida! Digite apenas numeros.");
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarProjeto();
                    break;
                case 2:
                    listarProjetos();
                    break;
                case 0:
                    System.out.println("Saindo do menu de projetos...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }

        }
    }

    // CADASTRAR PROJETO
    private void cadastrarProjeto() {

        System.out.print("\nDigite a matricula do professor vitalicio: ");
        String matricula = scanner.nextLine();

        if (matricula.trim().isEmpty()) {
            System.out.println("Erro: a matricula do professor não pode ser vazia.");
            return;
        }

        // Busca o professor
        Professor professor = professorController.buscarPorMatricula(matricula);

        if (professor == null) {
            System.out.println("Professor nao encontrado.");
            return;
        }

        // Valida tipo
        if (!(professor instanceof ProfessorVitalicio)) {
            System.out.println("Erro: Somente professores vitalicios podem cadastrar projetos.");
            return;
        }

        ProfessorVitalicio vitalicio = (ProfessorVitalicio) professor;

        System.out.print("Digite o nome do projeto: ");
        String nome = scanner.nextLine();

        if (nome.trim().isEmpty()) {
            System.out.println("Erro: o nome do projeto não pode ser vazio.");
            return;
        }
        // Impede nomes duplicados
        for (Projeto proj : vitalicio.getProjetos()) {
            if (proj.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Erro: esse professor já possui um projeto com esse nome.");
                return;
            }
        }

        // Cria o projeto
        Projeto projeto = new Projeto(nome, vitalicio);

        // Adiciona no professor
        vitalicio.adicionarProjeto(projeto);

        // Salva no repositório
        projetoController.cadastrarProjeto(projeto);

        System.out.println("Projeto cadastrado com sucesso!");
    }

    // LISTAR PROJETOS
    private void listarProjetos() {

        List<Projeto> lista = projetoController.listarProjeto();

        System.out.println("\n=== LISTA DE PROJETOS ===");

        if (lista.isEmpty()) {
            System.out.println("Nenhum projeto cadastrado.");
            return;
        }

        for (Projeto p : lista) {
            System.out.println(
                    "Projeto: " + p.getNome() +
                            " | Professor: " + p.getProfessor().getNome() +
                            " | Matricula: " + p.getProfessor().getMatricula());
        }
    }
}
