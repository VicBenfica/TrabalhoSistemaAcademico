package src.model;

import java.util.ArrayList;
import java.util.List;
import src.model.Disciplina;

public abstract class Professor {

    protected String nome;
    protected String matricula;
    protected String titulacao;
    protected double salarioBase;
    private List<Disciplina> disciplinas = new ArrayList<>();

    public Professor(String nome, String matricula, String titulacao, double salarioBase) {
        this.nome = nome;
        this.matricula = matricula;
        this.titulacao = titulacao;
        this.salarioBase = salarioBase;
    }

    // Metodo abstrato, cada tipo calcula diferente
    public abstract double calcularSalario();

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }

    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }



  

}
