package src.model;

public class Projeto {
    private String nome;
    private ProfessorVitalicio professor;

    public Projeto(String nome, ProfessorVitalicio professor) {
        this.nome = nome;
        this.professor = professor;
    }

    public String getNome() {
        return nome;
    }

    public ProfessorVitalicio getProfessor() {
        return professor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProfessor(ProfessorVitalicio professor) {
        this.professor = professor;
    }

    
}
