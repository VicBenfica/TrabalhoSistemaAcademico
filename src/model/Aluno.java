package src.model;

public class Aluno {
    private String nome;
    private String matricula;
    private String disciplina;

    public Aluno(String nome, String matricula, String disciplina) {
        this.disciplina = disciplina;
        this.matricula = matricula;
        this.nome = nome;
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

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void cadastrarAluno(){

    }
    public void listarProjeto(){

    }
    public void matricularAluno(){

    }
    public void desmatricularAluno(){
        
    }
}