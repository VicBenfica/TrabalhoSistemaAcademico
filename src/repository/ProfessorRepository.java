package src.repository;

import java.util.ArrayList;
import java.util.List;
import src.model.Professor;

public class ProfessorRepository {
    // Lista que armazena os professores
    private List<Professor> professores = new ArrayList<>();

    // Metodo save(), cadastrar professor
    public void save(Professor professor) {
        professores.add(professor);
    }

    // Metodo findAll(), listar todos
    public List<Professor> findAll() {
        return professores;
    }

    // Metodo findByMatricula() — buscar professor especifico para remover
    public Professor findByMatricula(String matricula) {
        for (Professor p : professores) {
            if (p.getMatricula().equals(matricula)) {
                return p;
            }
        }
        return null;
    }

    // Metodo delete(), remover professor
    public boolean delete(String matricula) {
        Professor p = findByMatricula(matricula);
        if (p != null) {
            professores.remove(p);
            return true;
        }
        return false;
    }
}
