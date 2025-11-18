package src.model;

public abstract class Disciplina {
    private String nome;
    private String codigo;
    private int cargaHoraria;
    private Professor professorResponsavel;
    private String alunosMatriculados;

    public Disciplina(String nome, String codigo, int cargaHoraria, Professor professorResponsavel,
            String alunosMatriculados) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.professorResponsavel = professorResponsavel;
        this.alunosMatriculados = alunosMatriculados;
    }

    public String getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Professor getProfessorResponsavel() {
        return professorResponsavel;
    }
    public void cadastrarDisciplina(){

    }
    public void editarDisciplina(){

    }
    public void listarDisciplina(){

    }
    public void exibirAlunos(){

    }
    public void removerDisciplina(){

    }
    public void removerProfessor(){

    }
    public void adicionarProfessor(){

    }
    public void gerarRelatorio(){

    }
}
