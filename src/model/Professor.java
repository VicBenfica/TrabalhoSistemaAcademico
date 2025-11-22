package src.model;

public abstract class Professor {

    protected String nome;
    protected String matricula;
    protected String titulacao;
    protected double salarioBase;

    public Professor(String nome, String matricula, String titulacao, double salarioBase) {
        this.nome = nome;
        this.matricula = matricula;
        this.titulacao = titulacao;
        this.salarioBase = salarioBase;
    }

    // Método abstrato — cada tipo calcula diferente
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
}
