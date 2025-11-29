package src.view;

import java.util.ArrayList;
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
import src.model.ProfessorVitalicio;

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

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida! Digite apenas numeros.");
                continue;
            }

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
                case 9:
                    registrarInteresse();
                case 0:
                    System.out.println("Encerrando menu de disciplinas...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private void cadastrarDisciplina() {
        System.out.println("\n=== Cadastro de Disciplina ===");

        int tipo = 0;
        while (tipo != 1 && tipo != 2) {
            System.out.println("1 - Obrigatoria");
            System.out.println("2 - Eletiva");
            System.out.println("Tipo: ");
            tipo = Integer.parseInt(scanner.nextLine());
            if (tipo != 1 && tipo != 2) {
                System.out.println("Tipo invalido");
            }
        }

        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            System.out.println("Erro: o nome da disciplina nao pode estar vazio.");
            return;
        }

        System.out.println("Codigo: ");
        String codigo = scanner.nextLine();
        if (codigo.trim().isEmpty()) {
            System.out.println("Erro: o codigo da disciplina nao pode estar vazio.");
            return;
        }

        System.out.println("Carga horaria: ");
        int cargaHoraria = Integer.parseInt(scanner.nextLine());
        if (tipo == 1) {
            while (cargaHoraria < 60) {
                System.out.println("Erro: disciplinas obrigatorias devem ter pelo menos 60 horas");
                System.out.println("Informe novamente: ");
                cargaHoraria = Integer.parseInt(scanner.nextLine());
            }
        }

        Professor responsavel = null;
        String opcao = "";

        System.out.println("Deseja adicionar um professor agora?\n1 - Sim\n2 - Nao");
        while (!opcao.equals("1") && !opcao.equals("2")) {
            opcao = scanner.nextLine();
            if (!opcao.equals("1") && !opcao.equals("2")) {
                System.out.println("Opcao invalida, tente novamente");
            }
        }

        if (opcao.equals("1")) {

            if (profController.listarProfessores().size() > 0) {

                boolean professorValido = false;

                while (!professorValido) {

                    System.out.println("Matricula do professor responsavel: ");
                    String mat = scanner.nextLine();

                    Professor temp = profController.buscarPorMatricula(mat);

                    if (temp == null) {
                        System.out.println("Professor nao encontrado.");
                        continue;
                    }

                    if (tipo == 1) {
                        boolean existeVitalicio = false;
                        for (Professor p : profController.listarProfessores()) {
                            if (p instanceof ProfessorVitalicio) {
                                existeVitalicio = true;
                                break;
                            }
                        }

                        if (existeVitalicio && !(temp instanceof ProfessorVitalicio)) {
                            System.out
                                    .println("Somente professores vitalicios podem assumir disciplinas obrigatorias.");
                            continue;
                        }
                    }

                    responsavel = temp;
                    professorValido = true;
                }

            } else {
                opcao = "";
                System.out.println("Nao existem professores cadastrados. Continuar sem professor?\n1 - Sim\n2 - Nao");

                while (!opcao.equals("1") && !opcao.equals("2")) {
                    opcao = scanner.nextLine();
                    if (!opcao.equals("1") && !opcao.equals("2")) {
                        System.out.println("Opcao invalida.");
                    }
                }

                if (opcao.equals("2")) {
                    System.out.println("Cancelando cadastro.");
                    return;
                }
            }
        }

        if (tipo == 1) {
            DisciplinaObrigatoria d = new DisciplinaObrigatoria(nome, codigo, cargaHoraria, responsavel);

            if (!controller.cadastrarDisciplina(d)) {
                System.out.println("Erro ao cadastrar disciplina obrigatoria.");
                return;
            }

            if (responsavel != null) {
                boolean atribuido = controller.definirProfessor(codigo, responsavel);

                while (!atribuido) {
                    System.out.println("Erro: professor excedeu o limite de disciplinas.");
                    System.out.println("Informe a matricula de outro professor ou digite 0 para pular:");

                    String mat = scanner.nextLine();
                    if (mat.equals("0"))
                        break;

                    Professor novo = profController.buscarPorMatricula(mat);

                    if (novo == null) {
                        System.out.println("Professor nao encontrado.");
                        continue;
                    }

                    if (!(novo instanceof ProfessorVitalicio)) {
                        System.out.println("Somente vitalicios podem assumir obrigatorias.");
                        continue;
                    }

                    atribuido = controller.definirProfessor(codigo, novo);

                    if (atribuido) {
                        System.out.println("Professor atribuido!");
                    }
                }
            }

            System.out.println("Disciplina obrigatoria cadastrada!");

        } else {
            System.out.println("Registrar interesse? (1 - Sim / 2 - Nao)");
            int op = Integer.parseInt(scanner.nextLine());
            String interesse = (op == 1 ? "Interesse registrado" : "Nenhum interesse");

            DisciplinaEletiva d = new DisciplinaEletiva(nome, codigo, cargaHoraria, responsavel, interesse);
            controller.cadastrarDisciplina(d);

            if (responsavel != null) {
                controller.definirProfessor(codigo, responsavel);
            }

            System.out.println("Disciplina eletiva cadastrada!");
        }
    }

    private void listarDisciplinas() {
        List<Disciplina> lista = controller.listarDisciplinas();

        System.out.println("\n=== LISTA DE DISCIPLINAS ===");
        for (Disciplina d : lista) {
            System.out.println("Nome: " + d.getNome() + " | " + "Codigo: " + d.getCodigo() + " | " + "Carga Horaria: "
                    + d.getCargaHoraria());
        }
    }

    private void removerDisciplina() {
        boolean ok = false;
        boolean certo = false;
        String opcao = "0";

        System.out.print("\nCodigo da disciplina para remover: ");
        String codigo = scanner.nextLine();

        if (codigo.trim().isEmpty()) {
            System.out.println("Erro: o codigo da disciplina nao pode estar vazio.");
            return;
        }

        for (Disciplina disciplinaTemp : controller.listarDisciplinas()) {

            if (!disciplinaTemp.getCodigo().equals(codigo)) {
                continue;
            }

            while (!certo) {

                // 1 — Verifica professor
                if (disciplinaTemp.getProfessorResponsavel() != null) {
                    System.out.println("A disciplina ainda tem um professor responsavel.");
                    System.out.println("Deseja remove-lo agora?\n1 - Sim\n2 - Nao");

                    opcao = scanner.nextLine();
                    while (!opcao.equals("1") && !opcao.equals("2")) {
                        System.out.println("Opcao invalida, tente novamente:");
                        opcao = scanner.nextLine();
                    }

                    if (opcao.equals("1")) {
                        disciplinaTemp.removerProfessor();
                    } else {
                        System.out.println("Operacao cancelada.");
                        return;
                    }
                }

                // 2 — Verifica alunos
                if (!disciplinaTemp.getAlunosMatriculados().isEmpty()) {
                    System.out.println("A disciplina ainda tem alunos matriculados.");
                    System.out.println("Deseja remove-los agora?\n1 - Sim\n2 - Nao");

                    opcao = scanner.nextLine();
                    while (!opcao.equals("1") && !opcao.equals("2")) {
                        System.out.println("Opcao invalida, tente novamente:");
                        opcao = scanner.nextLine();
                    }

                    if (opcao.equals("1")) {
                        List<Aluno> copia = new ArrayList<>(disciplinaTemp.getAlunosMatriculados());
                        for (Aluno a : copia) {
                            disciplinaTemp.removerAluno(a);
                        }
                    } else {
                        System.out.println("Operacao cancelada.");
                        return;
                    }
                }
                certo = true;
            }
        }
        if (certo) {
            ok = controller.removerDisciplina(codigo);
        }

        System.out.println(ok ? "Disciplina removida!" : "Disciplina nao encontrada.");
    }

    private void editarDisciplina() {

        System.out.print("\nCodigo da disciplina para editar: ");
        String codigo = scanner.nextLine();
        if (codigo.trim().isEmpty()) {
            System.out.println("Erro: o codigo da disciplina nao pode estar vazio.");
            return;
        }
        Disciplina d = controller.buscarPorCodigo(codigo);

        if (d == null) {
            System.out.println("Disciplina nao encontrada.");
            return;
        }

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        if (novoNome.trim().isEmpty()) {
            System.out.println("Erro: o nome da disciplina nao pode estar vazia.");
            return;
        }

        System.out.print("Nova carga horaria: ");
        int novaCarga = Integer.parseInt(scanner.nextLine());

        if (d instanceof DisciplinaObrigatoria) {
            if (novaCarga < 60) {
                System.out.println("Erro: disciplinas obrigatorias devem ter no minimo 60 horas.");
                return;
            }
        }

        System.out.print("Nova matricula do professor responsavel: ");
        String novaMatricula = scanner.nextLine();

        if (novaMatricula.trim().isEmpty()) {
            System.out.println("Erro: a nova matricula da disciplina nao pode estar vazia.");
            return;
        }
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

        if (codigo.trim().isEmpty()) {
            System.out.println("Erro: o codigo da disciplina nao pode estar vazio.");
            return;
        }
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
        System.out.println("------RELATORIO DISCIPLINAS------");
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
        System.out.print("Digite a matricula do professor que deseja remover da disciplina: ");
        String matricula = scanner.nextLine();

        if (matricula.trim().isEmpty()) {
            System.out.println("Erro: a matricula do professor nao pode estar vazia.");
            return;
        }

        System.out.print("Digite o codigo da disciplina: ");
        String codigo = scanner.nextLine();

        if (codigo.trim().isEmpty()) {
            System.out.println("Erro: o codigo da disciplina nao pode estar vazia.");
            return;
        }

        for (Disciplina disciplinaTemp : controller.listarDisciplinas()) {
            if (disciplinaTemp.getCodigo().equals(codigo) &&
                    disciplinaTemp.getProfessorResponsavel() != null &&
                    disciplinaTemp.getProfessorResponsavel().getMatricula().equals(matricula)) {

                disciplinaTemp.removerProfessor();
                System.out.println("Professor removido da disciplina!");
                return;
            }
        }

        System.out.println("Professor nao encontrado na disciplina.");
    }

    private void adicionarProfessor() {
        boolean ok = false;
        int confirmar = 0;
        boolean disp = false;
        System.out.print("Digite a matricula do professor: ");
        String matricula = scanner.nextLine();

        if (matricula.trim().isEmpty()) {
            System.out.println("Erro: a matricula do professor nao pode estar vazio.");
            return;
        }

        System.out.print("Digite o codigo da disciplina: ");
        String codigo = scanner.nextLine();

        if (codigo.trim().isEmpty()) {
            System.out.println("Erro: o codigo da disciplina nao pode estar vazio.");
            return;
        }

        Professor prof = profController.buscarPorMatricula(matricula);
        Disciplina d = controller.buscarPorCodigo(codigo);

        if (prof == null || d == null) {
            System.out.println("Professor ou disciplina nao encontrada!");
            return;
        }

        if (d instanceof DisciplinaObrigatoria) {
            if (prof instanceof ProfessorVitalicio) {
                if (prof.getDisciplinas().size() < 3) {
                    ok = controller.definirProfessor(codigo, prof);
                } else {
                    System.out.println("O professor " + prof.getNome()
                            + " ja esta ministrando o maximo de disciplina possivel para seu cargo");
                }
            } else {
                for (Professor proTemp : profController.listarProfessores()) {
                    if (proTemp instanceof ProfessorVitalicio) {
                        if (proTemp.getDisciplinas().size() < 3) {
                            disp = true;
                            System.out.println("Ha professor vitalicio disponivel: " + proTemp.getNome()
                                    + " Matricula: " + proTemp.getMatricula());
                        }
                    }
                }
                if (disp == false) {
                    while (confirmar != 1 && confirmar != 2) {
                        System.out.println(
                                "Voce esta atribuindo uma disciplina obrigatoria para um professor substituto, recomenda-se remanejar os professores vitalicios. Deseja continuar? 1 - Sim ou 2 - Nao");
                        confirmar = scanner.nextInt();
                        scanner.nextLine();
                        if (confirmar != 1 && confirmar != 2) {
                            System.out.println("Opcao invalida, tente novamente");
                        }
                    }
                    if (confirmar == 1) {
                        ok = controller.definirProfessor(codigo, prof);
                    } else {
                        System.out.println("Operacao Cancelada");
                    }
                }
            }
        } else {
            ok = controller.definirProfessor(codigo, prof);
        }

        if (!ok) {
            System.out.println("Erro: professor excedeu o limite permitido!");
            return;
        }

        System.out.println("Professor atribuido com sucesso!");
    }

    private void registrarInteresse() {
        boolean valido = false;
        String matricula = null;
        while (valido == false) {
            System.out.println("Se identifique informando sua matricula:");
            matricula = scanner.nextLine();
            for (Aluno alunoTemp : alunoController.listarAlunos()) {
                if (matricula.equals(alunoTemp.getMatricula())) {
                    valido = true;
                    break;
                }
            }
            if (valido == false) {
                System.out.println(
                        "A matricula informada nao condiz com nenhuma cadastrada no sistema, verifique e tente novamente");
            }
        }
        System.out.println("Informe a disciplina que gostaria de registrar interesse: ");
        String codigo = scanner.nextLine();
        if (controller.buscarPorCodigo(codigo) == null) {
            System.out.println("O codigo informado não corresponde a nenhuma disciplina cadastrada");
        } else if (controller.buscarPorCodigo(codigo) instanceof DisciplinaObrigatoria) {
            System.out.println(
                    "O codigo informado corresponde a uma disciplina obrigatoria. informe um codigo de uma disciplina Eletiva");
            System.out.println("Disciplinas Eletivas disponiveis para registrar interesse:");
            for (Disciplina disciplinaTemp : controller.listarDisciplinas()) {
                if (disciplinaTemp instanceof DisciplinaEletiva) {
                    System.out.println("Nome da disciplina: " + disciplinaTemp.getNome());
                    System.out.println("Codigo da disciplina: " + disciplinaTemp.getCodigo());
                }
            }
        } else {
            for (Disciplina disciplinaTemp : controller.listarDisciplinas()) {
                if (disciplinaTemp instanceof DisciplinaEletiva && disciplinaTemp.getCodigo().equals(codigo)) {
                    ((DisciplinaEletiva) disciplinaTemp).setRegistroInteresse("Sim");
                    for (Aluno alunoTemp : alunoController.listarAlunos()) {
                        if (alunoTemp.getMatricula().equals(matricula)) {
                            alunoTemp.adicionarInteresse(((DisciplinaEletiva) disciplinaTemp));
                        }
                    }
                }
            }
        }
    }
}
