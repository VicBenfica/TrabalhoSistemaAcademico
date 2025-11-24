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
        this.alunosMatriculados = new ArrayList<>();
        setProfessorResponsavel(professorResponsavel); // importante: ja cria relacao bidirecional
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

    public void setProfessorResponsavel(Professor novoProfessor) {

        // Se ja tinha professor antes, remover da lista dele
        if (this.professorResponsavel != null) {
            this.professorResponsavel.removerDisciplina(this);
        }

        // Atualiza professor
        this.professorResponsavel = novoProfessor;

        // Adiciona disciplina na lista do novo professor
        if (novoProfessor != null) {
            novoProfessor.adicionarDisciplina(this);
        }
    }

    // METODOS PARA GERENCIAR ALUNOS
    public void adicionarAluno(Aluno aluno) {
        alunosMatriculados.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        alunosMatriculados.remove(aluno);
    }

    // Remover professor da disciplina
    public void removerProfessor() {
        if (this.professorResponsavel != null) {
            this.professorResponsavel.removerDisciplina(this);
        }
        this.professorResponsavel = null;
    }
}
