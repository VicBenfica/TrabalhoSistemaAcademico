package src.view;

import src.controller.AlunoController;
import src.controller.DisciplinaController;
import src.model.Aluno;
import src.model.Disciplina;

import java.util.List;
import java.util.Scanner;

public class AlunoView {

    private Scanner scanner = new Scanner(System.in);
    private AlunoController controller;
    private DisciplinaController disciplinaController;

    public AlunoView(DisciplinaController disciplinaController, AlunoController controller) {
        this.controller = controller;
        this.disciplinaController = disciplinaController;
    }

    public void menu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU ALUNOS ===");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Listar Alunos");
            System.out.println("3 - Matricular aluno em disciplina");
            System.out.println("4 - Desmatricular aluno de disciplina");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    matricularAluno();
                    break;
                case 4:
                    desmatricularAluno();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // cadastrar aluno
    private void cadastrarAluno() {

        System.out.println("\n=== Cadastro de Aluno ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        // provisório – você ainda vai ligar aluno → disciplinas depois
        Aluno aluno = new Aluno(nome, matricula);

        controller.cadastrarAluno(aluno);

        System.out.println("Aluno cadastrado!");
    }

    // listar alunos
    private void listarAlunos() {

        List<Aluno> lista = controller.listarAlunos();

        System.out.println("\n=== LISTA DE ALUNOS ===");
        for (Aluno a : lista) {
            System.out.println(a.getNome() + " | " + a.getMatricula());
        }
    }

    // matricular aluno
    private void matricularAluno() {

        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine();

        Disciplina disciplina = disciplinaController.buscarPorCodigo(codigo);

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }

        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        Aluno aluno = controller.buscarPorMatricula(matricula);

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        boolean ok = controller.matricularAluno(codigo, aluno);

        System.out.println(ok ? "Aluno matriculado!" : "Erro ao matricular.");
    }

    // desmatricular aluno
    private void desmatricularAluno() {

        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine();

        Disciplina disciplina = disciplinaController.buscarPorCodigo(codigo);

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }

        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        Aluno aluno = controller.buscarPorMatricula(matricula);

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        boolean ok = controller.desmatricularAluno(codigo, aluno);

        System.out.println(ok ? "Aluno removido da disciplina!" : "Erro ao desmatricular.");
    }

}
