package src.model;

public class ProfessorVitalicio extends Professor {
    private int bonus;

    public ProfessorVitalicio(int bonus, String nome, String matricula, String titulacao, double salarioBase, String contrato, String disciplina){
        super(nome,matricula,titulacao,salarioBase,contrato,disciplina);
        this.bonus=bonus;
    }

    public int getBonus() {
        return bonus;
    }
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

}
