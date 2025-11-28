package src.controller;

import java.util.List;
import src.model.Aluno;
import src.model.Disciplina;
import src.model.DisciplinaObrigatoria;
import src.model.Professor;
import src.model.ProfessorSubstituto;
import src.model.ProfessorVitalicio;
import src.repository.DisciplinaRepository;

public class DisciplinaController {

    private DisciplinaRepository disciplinaRepository;

    public DisciplinaController(DisciplinaRepository repository) {
        this.disciplinaRepository = repository;
    }

    // cadastrar disciplina normalmente
    public boolean cadastrarDisciplina(Disciplina disciplina) {
        // Regra: obrigatoria deve ter pelo menos 60 horas
        if (disciplina instanceof DisciplinaObrigatoria) {
            if (disciplina.getCargaHoraria() < 60) {
                return false; // invAlido
            }
        }

        disciplinaRepository.save(disciplina);
        return true;
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Disciplina buscarPorCodigo(String codigo) {
        return disciplinaRepository.findByCodigo(codigo);
    }

    // Regras de negocio
    public boolean definirProfessor(String codigo, Professor professor) {

        Disciplina d = disciplinaRepository.findByCodigo(codigo);

        if (d == null)
            return false;

        if (professor == null) {
            d.setProfessorResponsavel(null);
            return true;
        }

        // Valida limite
        if (professor instanceof ProfessorSubstituto &&
                professor.getDisciplinas().size() >= 2) {
            return false;
        }

        if (professor instanceof ProfessorVitalicio &&
                professor.getDisciplinas().size() >= 3) {
            return false;
        }

        d.setProfessorResponsavel(professor);
        return true;
    }

    // editar disciplina
    public boolean editarDisciplina(String codigo,
            String novoNome,
            Integer novaCargaHoraria,
            Professor novoProfessorResponsavel) {

        Disciplina d = disciplinaRepository.findByCodigo(codigo);

        if (d == null)
            return false;

        if (d instanceof DisciplinaObrigatoria && novaCargaHoraria < 60) {
            return false;
        }
        
        if (novoNome != null)
            d.setNome(novoNome);

        if (novaCargaHoraria != null)
            d.setCargaHoraria(novaCargaHoraria);

        if (novoProfessorResponsavel != null)
            return definirProfessor(codigo, novoProfessorResponsavel);

        return true;
    }

    public boolean removerDisciplina(String codigo) {
        return disciplinaRepository.delete(codigo);
    }

}
