package src.model;

public abstract class Professor {

    protected String nome;
    protected String matricula;
    protected String titulacao;

    public Professor(String nome, String matricula, String titulacao) {
        this.nome = nome;
        this.matricula = matricula;
        this.titulacao = titulacao;
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
}
