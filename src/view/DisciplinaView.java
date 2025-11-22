package src.view;

import java.util.List;
import java.util.Scanner;

import src.controller.DisciplinaController;
import src.controller.AlunoController;
import src.model.Aluno;
import src.model.Disciplina;
import src.model.DisciplinaEletiva;
import src.model.DisciplinaObrigatoria;
import src.model.Professor;

public class DisciplinaView {

    private Scanner scanner = new Scanner(System.in);
    private DisciplinaController controller;
    private AlunoController alunoController; // ✔ agora existe!

    public DisciplinaView(DisciplinaController controller, AlunoController alunoController) {
        this.controller = controller;
        this.alunoController = alunoController; // ✔ inicializa
    }

    public void menu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU DISCIPLINAS ===");
            System.out.println("1 - Cadastrar Disciplina");
            System.out.println("2 - Listar Disciplinas");
            System.out.println("3 - Editar Disciplina");
            System.out.println("4 - Remover Disciplina");
            System.out.println("5 - Visualizar Alunos Matriculados");
            System.out.println("6 - Gerar Relatorio Disciplinas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarDisciplina();
                    break;
                case 2:
                    listarDisciplinas();
                    break;
                case 3:
                    editarDisciplina();
                    break;
                case 4:
                    removerDisciplina();
                    break;
                case 5:
                    visualizarAlunos();
                    break;
                case 6:
                    relatorioDisciplina();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarDisciplina() {

        System.out.println("\n=== Cadastro de Disciplina ===");
        System.out.println("1 - Obrigatória");
        System.out.println("2 - Eletiva");
        System.out.print("Tipo: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Carga horária: ");
        int cargaHoraria = Integer.parseInt(scanner.nextLine());

        System.out.print("Matrícula do professor responsável: ");
        String matriculaProfessor = scanner.nextLine();

        // professor ainda é null até você ligar com ProfessorController
        Professor responsavel = null;

        if (tipo == 1) {
            DisciplinaObrigatoria d = new DisciplinaObrigatoria(nome, codigo, cargaHoraria, responsavel);
            controller.cadastrarDisciplina(d);
        } else {
            System.out.print("Registrar interesse? (1 - Sim / 2 - Não): ");
            int opc = Integer.parseInt(scanner.nextLine());
            String interesse = (opc == 1 ? "Interesse registrado" : "Nenhum interesse");

            DisciplinaEletiva d = new DisciplinaEletiva(nome, codigo, cargaHoraria, responsavel, interesse);
            controller.cadastrarDisciplina(d);
        }

        System.out.println("Disciplina cadastrada!");
    }

    private void listarDisciplinas() {
        List<Disciplina> lista = controller.listarDisciplinas();

        System.out.println("\n=== LISTA DE DISCIPLINAS ===");
        for (Disciplina d : lista) {
            System.out.println(d.getNome() + " | " + d.getCodigo() + " | " + d.getCargaHoraria());
        }
    }

    private void removerDisciplina() {
        System.out.print("\nCódigo da disciplina para remover: ");
        String codigo = scanner.nextLine();

        boolean ok = controller.removerDisciplina(codigo);

        System.out.println(ok ? "Removida com sucesso!" : "Disciplina não encontrada.");
    }

    private void editarDisciplina() {

        System.out.print("\nCódigo da disciplina para editar: ");
        String codigo = scanner.nextLine();

        Disciplina d = controller.buscarPorCodigo(codigo);

        if (d == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        System.out.print("Nova carga horária: ");
        int novaCarga = Integer.parseInt(scanner.nextLine());

        System.out.print("Nova matrícula do professor responsável: ");
        String novaMatricula = scanner.nextLine();

        Professor novoProfessor = null;

        boolean ok = controller.editarDisciplina(codigo, novoNome, novaCarga, novoProfessor);

        System.out.println(ok ? "Disciplina atualizada!" : "Erro ao atualizar.");
    }

    private void visualizarAlunos() {

        System.out.print("\nCódigo da disciplina: ");
        String codigo = scanner.nextLine();

        Disciplina d = controller.buscarPorCodigo(codigo);

        if (d == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        System.out.println("\nAlunos matriculados:");
        for (Aluno a : d.getAlunosMatriculados()) {
            System.out.println(a.getNome() + " - " + a.getMatricula());
        }
    }

    private void relatorioDisciplina() {

        int totalAlunos = alunoController.listarAlunos().size();

        for (Disciplina disciplina : controller.listarDisciplinas()) {

            System.out.println("\n------------------------------");
            System.out.println("Disciplina: " + disciplina.getNome());
            System.out.println("Código: " + disciplina.getCodigo());

            // Verifica o tipo via instanceof tradicional
            if (disciplina instanceof DisciplinaObrigatoria) {

                System.out.println("Tipo: Obrigatória");

            } else if (disciplina instanceof DisciplinaEletiva) {

                DisciplinaEletiva eletiva = (DisciplinaEletiva) disciplina;

                System.out.println("Tipo: Eletiva");
                System.out.println("Interesse: " + eletiva.getRegistroInteresse());

                double popularidade = eletiva.calcularPopularidade(totalAlunos);
                System.out.printf("Popularidade: %.2f%%\n", popularidade);
            }

            // Lista alunos matriculados
            System.out.println("Alunos matriculados:");
            if (disciplina.getAlunosMatriculados().isEmpty()) {
                System.out.println("(Nenhum aluno matriculado)");
            } else {
                for (Aluno aluno : disciplina.getAlunosMatriculados()) {
                    System.out.println("- " + aluno.getNome());
                }
            }
        }
    }

}