package src.model;

public class ProfessorVitalicio extends Professor {
    private int bonus;

    public ProfessorVitalicio(int bonus, String nome, String matricula, String titulacao, double salarioBase, String contrato, String disciplina,int horasAula){
        super(nome,matricula,titulacao,salarioBase,contrato,disciplina, horasAula);
        this.bonus=bonus;
    }

    public int getBonus() {
        return bonus;
    }
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
    @Override
    public double calcularSalarioAtual() {
        // TODO Auto-generated method stub
        return super.calcularSalarioAtual();
    }
    public void cadastrarProjeto(){

    }
    @Override
    public void gerarRelatorio() {
        // TODO Auto-generated method stub
        super.gerarRelatorio();
    }
    public void listarProjeto(){

    }
    @Override
    public void cadastrarProfessor() {
        // TODO Auto-generated method stub
        super.cadastrarProfessor();
    }

}
