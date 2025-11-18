package src.controller;


import java.util.List;
import src.model.Professor;
import src.model.ProfessorSubstituto;
import src.model.ProfessorVitalicio;
import src.repository.ProfessorRepository;


// aplicar regras de negócio
// validar ações
// chamar cálculos do Model
// acessar o Repository para salvar/remover/buscar
// devolver respostas para a View

public class ProfessorController {

    private ProfessorRepository professorRepository;

    public ProfessorController(ProfessorRepository repository) {
        this.professorRepository = repository;
    }

    // 1) CADASTRAR PROFESSOR
    public void cadastrarProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    // 2) LISTAR PROFESSORES
    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    // 3) BUSCAR POR MATRÍCULA
    public Professor buscarPorMatricula(String matricula) {
        return professorRepository.findByMatricula(matricula);
    }

    // 4) EDITAR PROFESSOR
    public boolean editarProfessor(
        String matricula,
        String novoNome,
        String novaTitulacao,
        Double novoSalarioBase,
        Integer novasHorasAula
    ) {
        Professor p = professorRepository.findByMatricula(matricula);

        if (p == null) {
            return false; // não encontrado
        }

        p.setNome(novoNome);
        p.setTitulacao(novaTitulacao);

        if (p instanceof ProfessorVitalicio && novoSalarioBase != null) {
            ((ProfessorVitalicio) p).setSalarioBase(novoSalarioBase);
        }

        if (p instanceof ProfessorSubstituto && novasHorasAula != null) {
            ((ProfessorSubstituto) p).setHorasAula(novasHorasAula);
        }

        return true;
    }

    // --------------------------------------
    // 5) CALCULAR SALÁRIO
    // --------------------------------------
    public Double calcularSalario(String matricula) {
        Professor p = professorRepository.findByMatricula(matricula);

        if (p == null) {
            return null;
        }

        return p.calcularSalarioAtual();
    }

    // 6) REMOVER PROFESSOR
    public boolean removerProfessor(String matricula) {
        return professorRepository.delete(matricula);
    }
}