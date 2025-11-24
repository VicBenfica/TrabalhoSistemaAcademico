package src.view;

import java.util.List;
import java.util.Scanner;

import src.controller.DisciplinaController;
import src.controller.ProfessorController;
import src.controller.AlunoController;
import src.model.Aluno;
import src.model.Disciplina;
import src.model.DisciplinaEletiva;
import src.model.DisciplinaObrigatoria;
import src.model.Professor;

public class DisciplinaView {

    private Scanner scanner = new Scanner(System.in);
    private DisciplinaController controller;
    private AlunoController alunoController;
    private ProfessorController profController;

    public DisciplinaView(DisciplinaController controller, AlunoController alunoController,
            ProfessorController profController) {
        this.controller = controller;
        this.alunoController = alunoController;
        this.profController = profController;
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
            System.out.println("7 - Remover Professor da Disciplina");
            System.out.println("8 - Atribuir Professor a Disciplina");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

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
                case 7:
                    removerProfessor();
                    break;
                case 8:
                    adicionarProfessor();
                    break;
                case 0:
                    System.out.println("Encerrando menu de disciplinas...");
                    break;
                default:
                    System.out.println("Opção invalida.");
            }
        }
    }

    private void cadastrarDisciplina() {

        System.out.println("\n=== Cadastro de Disciplina ===");
        System.out.println("1 - Obrigatoria");
        System.out.println("2 - Eletiva");
        System.out.print("Tipo: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo invalido!");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Codigo: ");
        String codigo = scanner.nextLine();

        System.out.print("Carga horaria: ");
        int cargaHoraria = Integer.parseInt(scanner.nextLine());

        System.out.print("Matricula do professor responsavel: ");
        String matriculaProfessor = scanner.nextLine();

        Professor responsavel = profController.buscarPorMatricula(matriculaProfessor);

        if (responsavel == null) {
            System.out.println("Professor nao encontrado!");
            return;
        }

        if (tipo == 1) {
            DisciplinaObrigatoria d = new DisciplinaObrigatoria(nome, codigo, cargaHoraria, responsavel);

            boolean ok = controller.cadastrarDisciplina(d);
            if (!ok) {
                System.out.println("Erro: disciplinas obrigatorias devem ter pelo menos 60 horas!");
                return;
            }

            // atribuir professor pela regra
            if (!controller.definirProfessor(codigo, responsavel)) {
                System.out.println("Erro: professor excedeu o limite de disciplinas!");
            }

        } else {
            System.out.print("Registrar interesse? (1 - Sim / 2 - Nao): ");
            int opc = Integer.parseInt(scanner.nextLine());
            String interesse = (opc == 1 ? "Interesse registrado" : "Nenhum interesse");

            DisciplinaEletiva d = new DisciplinaEletiva(nome, codigo, cargaHoraria, responsavel, interesse);
            controller.cadastrarDisciplina(d);

            controller.definirProfessor(codigo, responsavel);
        }

        System.out.println("Disciplina cadastrada!");
    }

    private void listarDisciplinas() {
        List<Disciplina> lista = controller.listarDisciplinas();

        System.out.println("\n=== LISTA DE DISCIPLINAS ===");
        for (Disciplina d : lista) {
            System.out.println("Nome: "+d.getNome() + " | " + "Codigo: "+ d.getCodigo() + " | " +"Carga Horaria: "+ d.getCargaHoraria());
        }
    }

    private void removerDisciplina() {
        System.out.print("\nCodigo da disciplina para remover: ");
        String codigo = scanner.nextLine();

        boolean ok = controller.removerDisciplina(codigo);

        System.out.println(ok ? "Disciplina removida!" : "Disciplina nao encontrada.");
    }

    private void editarDisciplina() {

        System.out.print("\nCodigo da disciplina para editar: ");
        String codigo = scanner.nextLine();

        Disciplina d = controller.buscarPorCodigo(codigo);

        if (d == null) {
            System.out.println("Disciplina nao encontrada.");
            return;
        }

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        System.out.print("Nova carga horaria: ");
        int novaCarga = Integer.parseInt(scanner.nextLine());

        System.out.print("Nova matricula do professor responsavel: ");
        String novaMatricula = scanner.nextLine();

        Professor novoProfessor = profController.buscarPorMatricula(novaMatricula);

        if (novoProfessor == null) {
            System.out.println("Professor nao encontrado.");
            return;
        }

        boolean ok = controller.editarDisciplina(codigo, novoNome, novaCarga, novoProfessor);

        System.out.println(ok ? "Disciplina atualizada!" : "Erro ao atualizar disciplina.");
    }

    private void visualizarAlunos() {

        System.out.print("\nCodigo da disciplina: ");
        String codigo = scanner.nextLine();

        Disciplina d = controller.buscarPorCodigo(codigo);

        if (d == null) {
            System.out.println("Disciplina nao encontrada.");
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
            System.out.println("Codigo: " + disciplina.getCodigo());

            if (disciplina instanceof DisciplinaObrigatoria) {

                System.out.println("Tipo: Obrigatoria");

            } else if (disciplina instanceof DisciplinaEletiva) {

                DisciplinaEletiva eletiva = (DisciplinaEletiva) disciplina;

                System.out.println("Tipo: Eletiva");
                System.out.println("Interesse: " + eletiva.getRegistroInteresse());

                double popularidade = eletiva.calcularPopularidade(totalAlunos);
                System.out.printf("Popularidade: %.2f%%\n", popularidade);
            }

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

    private void removerProfessor() {
        System.out.print("Digite a matricula do professor: ");
        String matricula = scanner.nextLine();

        System.out.print("Digite o codigo da disciplina: ");
        String codigo = scanner.nextLine();

        Disciplina d = controller.buscarPorCodigo(codigo);

        if (d == null) {
            System.out.println("Disciplina nao encontrada!");
            return;
        }

        Professor prof = d.getProfessorResponsavel();

        if (prof == null || !prof.getMatricula().equals(matricula)) {
            System.out.println("Esse professor nao eh responsavel por essa disciplina!");
            return;
        }

        controller.definirProfessor(codigo, null);
        System.out.println("Professor removido da disciplina!");
    }

    private void adicionarProfessor() {
        System.out.print("Digite a matricula do professor: ");
        String matricula = scanner.nextLine();

        System.out.print("Digite o codigo da disciplina: ");
        String codigo = scanner.nextLine();

        Professor prof = profController.buscarPorMatricula(matricula);
        Disciplina d = controller.buscarPorCodigo(codigo);

        if (prof == null || d == null) {
            System.out.println("Professor ou disciplina nao encontrada!");
            return;
        }

        boolean ok = controller.definirProfessor(codigo, prof);

        if (!ok) {
            System.out.println("Erro: professor excedeu o limite permitido!");
            return;
        }

        System.out.println("Professor atribuido com sucesso!");
    }
}
