package src.model;

public class ProfessorSubstituto extends Professor {

    public ProfessorSubstituto(String nome, String matricula, String titulacao, double salarioBase, String contrato,
            String disciplina, int horasAula) {
        super(nome, matricula, titulacao, salarioBase, contrato, disciplina, horasAula);
    }

    @Override
    public double calcularSalarioAtual() {
        // TODO Auto-generated method stub
        return super.calcularSalarioAtual();
    }

    @Override
    public void cadastrarProfessor() {
        // TODO Auto-generated method stub
        super.cadastrarProfessor();
    }

    @Override
    public void gerarRelatorio() {
        // TODO Auto-generated method stub
        super.gerarRelatorio();
    }
}
