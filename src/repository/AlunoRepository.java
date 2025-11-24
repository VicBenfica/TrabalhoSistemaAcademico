package src.repository;

import java.util.ArrayList;
import java.util.List;
import src.model.Aluno;
import src.model.Professor;

public class AlunoRepository {
     // Lista que armazena os alunos
    private List<Aluno> alunos = new ArrayList<>();

    // Metodo save() — cadastrar aluno
    public void save(Aluno aluno) {
        alunos.add(aluno);
    }

    // Metodo findAll() — listar todos
    public List<Aluno> findAll() {
        return alunos;
    }



    //Metodo findByMatricula() — buscar aluno  específico para remover
    public Aluno findByMatricula(String matricula) {
        for (Aluno p : alunos) {
            if (p.getMatricula().equals(matricula)) {
                return p;
            }
        }
        return null;
    }

}
