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
    @Override
    public void gerarRelatorio() {
        // TODO Auto-generated method stub
        super.gerarRelatorio();
    }
    @Override
    public void cadastrarDisciplina() {
        // TODO Auto-generated method stub
        super.cadastrarDisciplina();
    }
    public double calcularPopularidade(){
        return 3.3;//arrumar
    }
    @Override
    public void exibirAlunos() {
        // TODO Auto-generated method stub
        super.exibirAlunos();
    }
    public void registroInteresse(){
        
    }
}
