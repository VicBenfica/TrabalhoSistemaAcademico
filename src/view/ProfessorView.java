package src.view;

import java.util.List;
import java.util.Scanner;

import src.controller.DisciplinaController;
import src.controller.ProfessorController;
import src.model.Disciplina;
import src.model.Professor;
import src.model.ProfessorSubstituto;
import src.model.ProfessorVitalicio;
import src.model.Projeto;
import src.controller.ProjetoController;

// ler dados (Scanner)
// mostrar informacoes na tela (System.out.println)

public class ProfessorView {

    private Scanner scanner = new Scanner(System.in);
    private ProfessorController controller;
    private DisciplinaController disciplinaController;
    private ProjetoController projetoController;

    public ProfessorView(ProfessorController controller,
            DisciplinaController disciplinaController,
            ProjetoController projetoController) {
        this.controller = controller;
        this.disciplinaController = disciplinaController;
        this.projetoController = projetoController;
    }

    // MENU PRINCIPAL
    public void menu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU PROFESSORES ===");
            System.out.println("1 - Cadastrar Professor");
            System.out.println("2 - Editar Professor");
            System.out.println("3 - Calcular Salario");
            System.out.println("4 - Listar Professores");
            System.out.println("5 - Remover Professor");
            System.out.println("6 - Gerar Relatorio");
            System.out.println("7 - Cadastrar Projeto (Professor Vitalicio)");
            System.out.println("8 - Listar Projetos de um Professor");
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
                    cadastrarProfessor();
                    break;
                case 2:
                    editarProfessor();
                    break;
                case 3:
                    calcularSalario();
                    break;
                case 4:
                    listarProfessores();
                    break;
                case 5:
                    removerProfessor();
                    break;
                case 6:
                    relatorioProfessor();
                    break;
                case 7:
                    cadastrarProjeto();
                    break;
                case 8:
                    listarProjetosProfessor();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private void cadastrarProfessor() {
        System.out.println("\n=== Cadastro de Professor ===");
        System.out.println("1 - Professor Vitalicio");
        System.out.println("2 - Professor Substituto");

        System.out.print("Tipo: ");
        int tipo = scanner.nextInt();

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo invalido!");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        if (nome.trim().isEmpty()) {
            System.out.println("Erro: o nome do professor nao pode ser vazio.");
            return;
        }

        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();

        if (matricula.trim().isEmpty()) {
            System.out.println("Erro: a matricula do professor nao pode ser vazia.");
            return;
        }

        System.out.print("Titulacao: ");
        String titulacao = scanner.nextLine();

        if (titulacao.trim().isEmpty()) {
            System.out.println("Erro: a titulacao do professor nao pode ser vazia.");
            return;
        }

        // PROFESSOR VITALICIO
        if (tipo == 1) {
            System.out.print("Salario base: ");
            double salarioBase = scanner.nextDouble();

            System.out.print("Possui doutorado? (1-Sim / 2-Nao): ");
            int doutorado = scanner.nextInt();

            ProfessorVitalicio p = new ProfessorVitalicio(nome, matricula, titulacao, salarioBase, doutorado);

            controller.cadastrarProfessor(p);
        }

        // PROFESSOR SUBSTITUTO
        else {
            System.out.print("Horas aula: ");
            int horas = scanner.nextInt();

            System.out.print("Salario base: ");
            double salarioBase = scanner.nextDouble();

            ProfessorSubstituto p = new ProfessorSubstituto(nome, matricula, titulacao, horas, salarioBase);

            controller.cadastrarProfessor(p);
        }

        System.out.println("Professor cadastrado com sucesso!");
    }

    // EDITAR PROFESSOR

    private void editarProfessor() {
        System.out.print("\nDigite a matricula: ");
        String matricula = scanner.nextLine();

        
        Professor p = controller.buscarPorMatricula(matricula);

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Nova titulacao: ");
        String titulacao = scanner.nextLine();

        Double novoSalario = null;
        Integer novasHoras = null;

        if (p instanceof ProfessorVitalicio) {
            System.out.print("Novo salario base: ");
            novoSalario = Double.parseDouble(scanner.nextLine());
        } else {
            System.out.print("Novas horas aula: ");
            novasHoras = Integer.parseInt(scanner.nextLine());
        }

        boolean ok = controller.editarProfessor(matricula, nome, titulacao, novoSalario, novasHoras);

        System.out.println(ok ? "Atualizado!" : "Erro ao atualizar.");
    }

    private void calcularSalario() {

        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();

        if (matricula.trim().isEmpty()) {
            System.out.println("Erro: a matricula do professor nao pode ser vazia.");
            return;
        }

        Double salario = controller.calcularSalario(matricula);

        if (salario == null) {
            System.out.println("Professor nao encontrado.");
            return;
        }

        System.out.println("Salario: R$ " + salario);
    }

    // LISTAR PROFESSORES

    private void listarProfessores() {

        List<Professor> lista = controller.listarProfessores();

        System.out.println("\n=== LISTA DE PROFESSORES ===");

        for (Professor p : lista) {
            System.out.println(p.getNome() + " | " + p.getMatricula() + " | " + p.getTitulacao());
        }
    }

    // REMOVER PROFESSOR
    private void removerProfessor() {

        System.out.print("\nDigite a matricula para remover: ");
        String matricula = scanner.nextLine();

        if (matricula.trim().isEmpty()) {
            System.out.println("Erro: a matricula do professor nao pode ser vazia.");
            return;
        }

        boolean ok = controller.removerProfessor(matricula);

        System.out.println(ok ? "Removido com sucesso!" : "Professor nao encontrado.");
    }

    // RELATORIO PROFESSORES
    public void relatorioProfessor() {

        for (Professor professor : controller.listarProfessores()) {

            int qtd = 0; // O contador de disciplinas DEVE ser inicializado AQUI, para CADA professor

            System.out.println("\n---------------------------------");
            System.out.println("Professor: " + professor.getNome());
            System.out.println("Titulação: " + professor.getTitulacao());
            System.out.println("Salario: R$ " + professor.calcularSalario());

            // Substituição do Type Pattern (Java 16+) para compatibilidade.
            if (professor instanceof ProfessorVitalicio) {
                ProfessorVitalicio vitalicio = (ProfessorVitalicio) professor;

                System.out.println("Cargo: Professor Vitalicio");

                // ---- PROJETOS ----
                System.out.println("Projetos:");
                if (vitalicio.getProjetos().isEmpty()) {
                    System.out.println("(Nenhum projeto)");
                } else {
                    for (Projeto projeto : vitalicio.getProjetos()) {
                        System.out.println("- " + projeto.getNome());
                    }
                }
            } else {
                System.out.println("Cargo: Professor Substituto");
            }

            // ---- DISCIPLINAS ----
            System.out.println("Disciplinas ministradas:");
            if (professor.getDisciplinas().isEmpty()) {
                System.out.println("(Nenhuma disciplina)");
            } else {
                for (Disciplina d : professor.getDisciplinas()) {
                    System.out.println("- " + d.getNome());
                    qtd++; // Conta as disciplinas DESTE professor
                }
            }

            System.out.println("Quantidade de disciplinas ministradas: " + qtd);
        }
    }

    // CADASTRAR PROJETO
    public void cadastrarProjeto() {

        System.out.print("\nDigite a matricula do professor: ");
        String matricula = scanner.nextLine();

        Professor professor = controller.buscarPorMatricula(matricula);

        if (professor == null) {
            System.out.println("Professor nao encontrado.");
            return;
        }

        if (!(professor instanceof ProfessorVitalicio)) {
            System.out.println("Erro: somente professores vitalicios podem ter projetos!");
            return;
        }

        ProfessorVitalicio vitalicio = (ProfessorVitalicio) professor;

        System.out.print("Nome do projeto: ");
        String nomeProjeto = scanner.nextLine();

        Projeto projeto = new Projeto(nomeProjeto, vitalicio);

        vitalicio.adicionarProjeto(projeto);
        projetoController.cadastrarProjeto(projeto);

        System.out.println("Projeto cadastrado com sucesso!");
    }

    // LISTAR PROJETOS
    public void listarProjetosProfessor() {

        System.out.print("\nDigite a matricula do professor: ");
        String matricula = scanner.nextLine();

        Professor professor = controller.buscarPorMatricula(matricula);

        if (!(professor instanceof ProfessorVitalicio)) {
            System.out.println("Professor nao eh vitalicio ou nao existe.");
            return;
        }

        ProfessorVitalicio vitalicio = (ProfessorVitalicio) professor;

        System.out.println("\nProjetos do professor: " + professor.getNome());

        if (vitalicio.getProjetos().isEmpty()) {
            System.out.println("(Nenhum projeto cadastrado)");
            return;
        }

        for (Projeto p : vitalicio.getProjetos()) {
            System.out.println("- " + p.getNome());
        }
    }

}