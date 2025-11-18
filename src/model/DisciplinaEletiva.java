package src.model;

public class DisciplinaEletiva extends Disciplina {
    private String registroInteresse;

    public DisciplinaEletiva(String nome, String codigo, int cargaHoraria, Professor professorResponsavel,String alunosMatriculados, String registroInteresse) {
        super(nome, codigo, cargaHoraria, professorResponsavel, alunosMatriculados);
        this.registroInteresse = registroInteresse;
    }

    public String getRegistroInteresse() {
        return registroInteresse;
    }

    public void setRegistroInteresse(String registroInteresse) {
        this.registroInteresse = registroInteresse;
    }
}
