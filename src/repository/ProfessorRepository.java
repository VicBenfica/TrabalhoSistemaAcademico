package src.repository;

import java.util.ArrayList;
import java.util.List;
import src.model.Professor;

public class ProfessorRepository {
    // Lista que armazena os professores
    private List<Professor> professores = new ArrayList<>();

    // Método save() — cadastrar professor
    public void save(Professor professor) {
        professores.add(professor);
    }

    // Método findAll() — listar todos
    public List<Professor> findAll() {
        return professores;
    }
    //Método findByMatricula() — buscar professor específico para remover
    public Professor findByMatricula(String matricula) {
        for (Professor p : professores) {
            if (p.getMatricula().equals(matricula)) {
                return p;
            }
        }
        return null;
    }

    // Método delete() — remover professor
    public boolean delete(String matricula) {
        Professor p = findByMatricula(matricula);
        if (p != null) {
            professores.remove(p);
            return true;
        }
        return false;
    }
}
