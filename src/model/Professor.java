package src.model;

public abstract class Professor {
    private String nome;
    private String matricula;
    private String titulacao;
    private double salarioBase;
    private String contrato;
    private String disciplina;

    public Professor(String nome, String matricula, String titulacao, double salarioBase, String contrato, String disciplina) {
        this.nome = nome;
        this.matricula = matricula;
        this.titulacao = titulacao;
        this.salarioBase = salarioBase;
        this.contrato = contrato;
        this.disciplina = disciplina;
    }

    public String getContrato() {
        return contrato;
    }
    public String getDisciplina() {
        return disciplina;
    }
    public String getMatricula() {
        return matricula;
    }
    public String getNome() {
        return nome;
    }
    public double getSalarioBase() {
        return salarioBase;
    }
    public String getTitulacao() {
        return titulacao;
    }
    public void setContrato(String contrato) {
        this.contrato = contrato;
    }
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
}
