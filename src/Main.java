package src;

import src.controller.*;
import src.repository.*;
import src.view.*;
import src.model.*;

public class Main {

    public static void main(String[] args) {

        // ===== REPOSITORIES =====
        ProfessorRepository professorRepo = new ProfessorRepository();
        DisciplinaRepository disciplinaRepo = new DisciplinaRepository();
        AlunoRepository alunoRepo = new AlunoRepository();

        // ===== CONTROLLERS =====
        ProfessorController professorController = new ProfessorController(professorRepo);
        DisciplinaController disciplinaController = new DisciplinaController(disciplinaRepo);
        AlunoController alunoController = new AlunoController(alunoRepo, disciplinaRepo);

        // ===== VIEWS =====
        ProfessorView professorView = new ProfessorView(professorController, disciplinaController);
        DisciplinaView disciplinaView = new DisciplinaView(disciplinaController, alunoController);
        AlunoView alunoView = new AlunoView(disciplinaController, alunoController);

        // ===== TESTE PARA RELATÓRIO =====
        // Cadastrar professor para não aparecer vazio
        ProfessorVitalicio p1 = new ProfessorVitalicio("Ana", "P100", "Doutora", 5000);
        professorController.cadastrarProfessor(p1);

        ProfessorSubstituto p2 = new ProfessorSubstituto("João", "P200", "Mestre", 12);
        professorController.cadastrarProfessor(p2);

        Disciplina d1 = new DisciplinaObrigatoria("POO", "D001", 60, p1);
        disciplinaController.cadastrarDisciplina(d1);

        Disciplina d2 = new DisciplinaObrigatoria("BD", "D002", 80, p2);
        disciplinaController.cadastrarDisciplina(d2);

        // ===== IMPRIMIR RELATÓRIO =====
        professorView.relatorioProfessor();
    }
}
