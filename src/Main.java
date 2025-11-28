package src;

import src.repository.*;
import src.controller.*;
import src.view.*;

public class Main {

    public static void main(String[] args) {

        // ===== REPOSITORIES =====
        ProfessorRepository professorRepo = new ProfessorRepository();
        DisciplinaRepository disciplinaRepo = new DisciplinaRepository();
        AlunoRepository alunoRepo = new AlunoRepository();
        ProjetoRepository projetoRepo = new ProjetoRepository();

        // ===== CONTROLLERS =====
        ProfessorController professorController = new ProfessorController(professorRepo);
        DisciplinaController disciplinaController = new DisciplinaController(disciplinaRepo);
        AlunoController alunoController = new AlunoController(alunoRepo, disciplinaRepo);
        ProjetoController projetoController = new ProjetoController(projetoRepo);

        // ===== VIEWS =====
        ProfessorView professorView = new ProfessorView(professorController, disciplinaController, projetoController);
        DisciplinaView disciplinaView = new DisciplinaView(disciplinaController, alunoController, professorController);
        AlunoView alunoView = new AlunoView(disciplinaController, alunoController);
        ProjetoView projetoView = new ProjetoView(projetoController, professorController);

        // ===== MENU PRINCIPAL =====
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== SISTEMA ACADEMICO ===");
            System.out.println("1 - Menu Alunos");
            System.out.println("2 - Menu Disciplinas");
            System.out.println("3 - Menu Professores");
            System.out.println("4 - Menu Projetos");
            System.out.println("5 - Ajuda / Sobre");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida! Digite apenas numeros.");
                continue;
            }

            switch (opcao) {
                case 1:
                    alunoView.menu();
                    break;
                case 2:
                    disciplinaView.menu();
                    break;
                case 3:
                    professorView.menu();
                    break;
                case 4:
                    projetoView.menu();
                    break;
                case 5:
                    Ajuda();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }

        }
    }

    static void Ajuda() {
        System.out.println("\r\n" + //
                "-- Como usar o sistema pelo Terminal (CLI) --\r\n\n" + //
                "Este sistema funciona atraves de uma Interface de Linha de Comando (CLI), \r\n" + //

                "onde todas as interacoes sao feitas digitando numeros e informacoes solicitadas pelo programa.\r\n"
                +
                "A seguir, voce encontrara instrucoes para utilizar todas as funcionalidades.\r\n\n" + //
                "\r\n" + //
                "-----------------------------------------------------------------------------------------\r\n" + //
                "\r\n" + //
                "--  Iniciando o sistema --\r\n" + //
                "Para comecar, basta executar o programa principal no terminal:\r\n" + //
                "  java -cp . src.Main  \r\n" + //
                "Assim que o programa iniciar, o menu principal sera exibido na tela.\r\n" + //
                "\r\n" + //
                "-----------------------------------------------------------------------------------------\r\n" + //
                "\r\n" + //
                "-- Como navegar pelos menus --\r\n" + //
                "A navegacao e simples:\r\n" + //
                "1.\tO sistema mostra uma lista de opcoes numeradas.\r\n" + //
                "2.\tVoce digita o numero da opcao desejada.\r\n" + //
                "3.\tPressiona Enter.\r\n" + //
                "\r\n" + //
                "-----------------------------------------------------------------------------------------\r\n" + //
                "\r\n" + //
                "-- Cadastrando informacoes --\r\n" + //
                "Ao escolher uma opcao como Cadastrar Aluno, Cadastrar Professor ou Cadastrar Disciplina, \r\n" + //
                "o sistema ira pedir que voce informe algumas informacoes, complete os campos como foi solicitado \r\n"
                + //
                " e pressione Enter apos cada informacao.\r\n"
                + "\r\n" + //
                "-----------------------------------------------------------------------------------------\r\n" + //
                "\r\n" + //
                "-- Regras importantes durante o uso --\r\n" + //
                "\r\n" + //
                "Algumas acoes possuem regras especificas que o sistema verifica automaticamente. Por exemplo:\r\n" + //
                "- Disciplinas Obrigatorias devem ter pelo menos 60 horas.\r\n" + //
                "- Professores substitutos podem lecionar no maximo 2 disciplinas.\r\n" + //
                "- Professores vitalicios podem lecionar ate 3 disciplinas.\r\n" + //
                "- So e possivel atribuir um professor existente a uma disciplina.\r\n" + //
                "Se alguma regra nao for atendida, o sistema exibira uma mensagem explicando o erro para que voce possa corrigir.\r\n"
                + "\r\n" + //
                "-----------------------------------------------------------------------------------------\r\n"
                + "\r\n" + //
                "-- Visualizando informacoes --\r\n" + //
                "O sistema permite visualizar:\r\n" + //
                "- Todas as disciplinas cadastradas\r\n" + //
                "- Professores responsaveis\r\n" + //
                "- Alunos matriculados\r\n" + //
                "- Popularidade de disciplinas eletivas\r\n" + //
                "- Relatorios organizados\r\n" + //
                "Basta escolher a opcao correspondente no menu.\r\n"
                + "\r\n" + //
                "-----------------------------------------------------------------------------------------\r\n" //
                + "\r\n" + //
                "-- Saindo do menu --\r\n" + //
                "Em qualquer tela, se quiser voltar ou sair:\r\n" + //
                "- Digite 0 para retornar ao menu anterior.\r\n" + //
                "- No menu principal, digite 0 para encerrar o sistema.\r\n" //
                + "\r\n" + //
                "-----------------------------------------------------------------------------------------\r\n" + //
                "\r\n" + //
                "Informacoes sobre o Autor e o Repositorio\r\n" + "\r\n" + //

                "Este projeto foi desenvolvido pelos estudantes Victoria Benfica e Pedro Pacheco, como parte das atividades academicas da disciplina de Programacao Orientada a Objetos.\r\n"
                + //
                "O sistema foi implementado inteiramente em Java, utilizando o padrao MVC (Model-View-Controller) e executado por meio de uma interface de linha de comando (CLI).\r\n");
    }

}
