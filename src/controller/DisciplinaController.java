package src.controller;

import java.util.List;
import src.model.Aluno;
import src.model.Disciplina;
import src.model.Professor;
import src.repository.DisciplinaRepository;

public class DisciplinaController {

    private DisciplinaRepository disciplinaRepository;

    public DisciplinaController(DisciplinaRepository repository) {
        this.disciplinaRepository = repository;
    }

    // 1) Cadastrar Disciplina
    public void cadastrarDisciplina(Disciplina disciplina) {
        disciplinaRepository.save(disciplina);
    }

    // 2) Listar Disciplinas
    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    // 3) Buscar disciplina
    public Disciplina buscarPorCodigo(String codigo) {
        return disciplinaRepository.findByCodigo(codigo);
    }

    // 4) Editar Disciplina
    public boolean editarDisciplina(String codigo,
            String novoNome,
            Integer novaCargaHoraria,
            Professor novoProfessorResponsavel) {

        Disciplina d = disciplinaRepository.findByCodigo(codigo);

        if (d == null) {
            return false;
        }

        if (novoNome != null)
            d.setNome(novoNome);
        if (novaCargaHoraria != null)
            d.setCargaHoraria(novaCargaHoraria);
        if (novoProfessorResponsavel != null)
            d.setProfessorResponsavel(novoProfessorResponsavel);

        return true;
    }

    // 5) Remover
    public boolean removerDisciplina(String codigo) {
        return disciplinaRepository.delete(codigo);
    }

}
