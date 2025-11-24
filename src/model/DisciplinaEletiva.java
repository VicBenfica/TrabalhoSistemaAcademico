package src.model;

public class DisciplinaEletiva extends Disciplina {

    private String registroInteresse;

    public DisciplinaEletiva(String nome, String codigo, int cargaHoraria,
            Professor professorResponsavel,
            String registroInteresse) {

        // Chamada ao construtor da classe mae
        super(nome, codigo, cargaHoraria, professorResponsavel);

        this.registroInteresse = registroInteresse;
    }

    // GETTERS e SETTERS
    public String getRegistroInteresse() {
        return registroInteresse;
    }

    public void setRegistroInteresse(String registroInteresse) {
        this.registroInteresse = registroInteresse;
    }

    // Metodo para registrar interesse
    public void registrarInteresse(String interesse) {
        this.registroInteresse = interesse;
    }

    public double calcularPopularidade(int totalAlunos) {
        if (totalAlunos == 0)
            return 0;
        return ((double) getAlunosMatriculados().size() / totalAlunos) * 100;
    }

}
