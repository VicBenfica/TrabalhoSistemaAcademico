package src.model;

public class DisciplinaEletiva extends Disciplina {

    private String registroInteresse;

    public DisciplinaEletiva(String nome, String codigo, int cargaHoraria,
            Professor professorResponsavel,
            String registroInteresse) {

        // Chamada correta ao construtor da classe mãe
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

    // Método para registrar interesse 
    public void registrarInteresse(String interesse) {
        this.registroInteresse = interesse;
    }

}
