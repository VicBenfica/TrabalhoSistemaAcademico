package src.view;

import java.util.List;
import java.util.Scanner;

import src.controller.DisciplinaController;
import src.controller.ProfessorController;
import src.model.Disciplina;
import src.model.Professor;
import src.model.ProfessorSubstituto;
import src.model.ProfessorVitalicio;

// ler dados (Scanner)
// mostrar informacoes na tela (System.out.println)

public class ProfessorView {

    private Scanner scanner = new Scanner(System.in);
    private ProfessorController controller;
    private DisciplinaController disciplinaController;

    public ProfessorView(ProfessorController controller, DisciplinaController disciplinaController) {
        this.controller = controller;
        this.disciplinaController = disciplinaController;
    }

    // MENU PRINCIPAL
    public void menu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU PROFESSORES ===");
            System.out.println("1 - Cadastrar Professor");
            System.out.println("2 - Editar Professor");
            System.out.println("3 - Calcular Salário");
            System.out.println("4 - Listar Professores");
            System.out.println("5 - Remover Professor");
            System.out.println("6 - Gerar Relatorio");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

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
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // CADASTRAR PROFESSOR
    private void cadastrarProfessor() {
        System.out.println("\n=== Cadastro de Professor ===");
        System.out.println("1 - Professor Vitalício");
        System.out.println("2 - Professor Substituto");
        System.out.print("Tipo: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        System.out.print("Titulação: ");
        String titulacao = scanner.nextLine();

        if (tipo == 1) {
            System.out.print("Salário base: ");
            double salarioBase = Double.parseDouble(scanner.nextLine());

            System.out.print("Possui doutorado? (1-Sim / 2-Não): ");
            int doutorado = Integer.parseInt(scanner.nextLine());

            ProfessorVitalicio p = new ProfessorVitalicio(nome, matricula, titulacao, salarioBase, doutorado);
            controller.cadastrarProfessor(p);

        } else {
            System.out.print("Horas aula: ");
            int horas = Integer.parseInt(scanner.nextLine());

            System.out.print("Salário base: ");
            double salarioBase = Double.parseDouble(scanner.nextLine());

            ProfessorSubstituto p = new ProfessorSubstituto(nome, matricula, titulacao, horas, salarioBase);
            controller.cadastrarProfessor(p);
        }

        System.out.println("Professor cadastrado com sucesso!");
    }

    // EDITAR PROFESSOR

    private void editarProfessor() {
        System.out.print("\nDigite a matrícula: ");
        String matricula = scanner.nextLine();

        Professor p = controller.buscarPorMatricula(matricula);

        if (p == null) {
            System.out.println("Professor não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Nova titulação: ");
        String titulacao = scanner.nextLine();

        Double novoSalario = null;
        Integer novasHoras = null;

        if (p instanceof ProfessorVitalicio) {
            System.out.print("Novo salário base: ");
            novoSalario = Double.parseDouble(scanner.nextLine());
        } else {
            System.out.print("Novas horas aula: ");
            novasHoras = Integer.parseInt(scanner.nextLine());
        }

        boolean ok = controller.editarProfessor(matricula, nome, titulacao, novoSalario, novasHoras);

        System.out.println(ok ? "Atualizado!" : "Erro ao atualizar.");
    }

    // CALCULAR SALaRIO
    private void calcularSalario() {

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        Double salario = controller.calcularSalario(matricula);

        if (salario == null) {
            System.out.println("Professor não encontrado.");
        } else {
            System.out.println("Salário: R$ " + salario);
        }
    }

    // LISTAR PROFESSORES
    private void listarProfessores() {
        List<Professor> lista = controller.listarProfessores();

        System.out.println("\n=== LISTA DE PROFESSORES ===");

        for (Professor p : lista) {
            System.out.println(
                    p.getNome() + " | " +
                            p.getMatricula() + " | " +
                            p.getTitulacao());
        }
    }

    // REMOVER PROFESSOR
    private void removerProfessor() {
        System.out.print("\nDigite a matrícula para remover: ");
        String matricula = scanner.nextLine();

        boolean ok = controller.removerProfessor(matricula);

        if (ok)
            System.out.println("Removido com sucesso!");
        else
            System.out.println("Professor não encontrado.");
    }

    // GERAR RELAToRIO PROFESSOR
    public void relatorioProfessor() {
        for (Professor professor : controller.listarProfessores()) {
            int qtdDisciplina = 0;
            System.out.println("Professor: " + professor.getNome());
            System.out.println("Disciplinas ministradas pelo Professor: ");

            for (Disciplina disciplina : disciplinaController.listarDisciplinas()) {
                if (disciplina.getProfessorResponsavel().getMatricula().equals(professor.getMatricula())) {
                    qtdDisciplina++;
                    System.out.println("- " + disciplina.getNome());
                }
            }

            System.out.println("Quantidade de Disciplinas ministradas: " + qtdDisciplina);
            System.out.println("Salário do professor: " + professor.calcularSalario());
        }
    }

}