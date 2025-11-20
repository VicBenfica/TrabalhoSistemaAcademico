package src.model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String matricula;
    private List<Disciplina> disciplinasMatriculadas;

    public Aluno(String nome, String matricula) {
        this.disciplinasMatriculadas = new ArrayList<>();
        this.matricula = matricula;
        this.nome = nome;
    }

    public List<Disciplina> getDisciplina() {
        return disciplinasMatriculadas;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setDisciplinaMatriculadas(List<Disciplina> disciplinas) {
        this.disciplinasMatriculadas = disciplinas;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}