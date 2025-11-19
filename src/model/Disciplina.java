package src.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Disciplina {

    private String nome;
    private String codigo;
    private int cargaHoraria;
    private Professor professorResponsavel;
    private List<Aluno> alunosMatriculados;

    public Disciplina(String nome, String codigo, int cargaHoraria, Professor professorResponsavel) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.professorResponsavel = professorResponsavel;
        this.alunosMatriculados = new ArrayList<>(); 
    }

    // GETTERS
    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public Professor getProfessorResponsavel() {
        return professorResponsavel;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    // SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setProfessorResponsavel(Professor professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
    }

    // MÉTODOS PARA GERENCIAR ALUNOS
    public void adicionarAluno(Aluno aluno) {
        alunosMatriculados.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        alunosMatriculados.remove(aluno);
    }
}
