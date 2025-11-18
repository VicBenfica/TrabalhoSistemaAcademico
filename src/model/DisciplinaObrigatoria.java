package src.model;

public class DisciplinaObrigatoria extends Disciplina {
    public DisciplinaObrigatoria(String nome, String codigo, int cargaHoraria, Professor professorResponsavel, String alunosMatriculados, String registroInteresse) {
        super(nome, codigo, cargaHoraria, professorResponsavel, alunosMatriculados);
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
    @Override
    public void exibirAlunos() {
        // TODO Auto-generated method stub
        super.exibirAlunos();
    }
}
