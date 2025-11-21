package src.controller;

import java.util.List;

import src.model.Aluno;
import src.model.Disciplina;
import src.repository.AlunoRepository;
import src.repository.DisciplinaRepository;

public class AlunoController {

    private AlunoRepository alunoRepository;
    private DisciplinaRepository disciplinaRepository;

    public AlunoController(AlunoRepository alunoRepo, DisciplinaRepository disciplinaRepo) {
        this.alunoRepository = alunoRepo;
        this.disciplinaRepository = disciplinaRepo;
    }

    // CADASTRAR ALUNO
    public void cadastrarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    // LISTAR ALUNOS
    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    // BUSCAR ALUNO POR MATRICULA
    public Aluno buscarPorMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula);
    }

    // MATRICULAR ALUNO EM DISCIPLINA
    public boolean matricularAluno(String codigoDisciplina, Aluno aluno) {
    Disciplina d = disciplinaRepository.findByCodigo(codigoDisciplina);

    if (d == null || aluno == null)
        return false;

    d.adicionarAluno(aluno); // Model altera a lista
    return true;
}

    // DESMATRICULAR ALUNO DE DISCIPLINA
    public boolean desmatricularAluno(String codigoDisciplina, Aluno aluno) {
    Disciplina d = disciplinaRepository.findByCodigo(codigoDisciplina);

    if (d == null || aluno == null)
        return false;

    d.removerAluno(aluno);
    return true;
}
}
