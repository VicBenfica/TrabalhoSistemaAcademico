package src.model;

public class ProfessorSubstituto extends Professor {

    private int horasAula;

    public ProfessorSubstituto(String nome, String matricula, String titulacao, int horasAula, double salarioBase) {
        super(nome, matricula, titulacao, salarioBase);
        this.horasAula = horasAula;
    }

    public int getHorasAula() {
        return horasAula;
    }

    public void setHorasAula(int horasAula) {
        this.horasAula = horasAula;
    }

    @Override
    public double calcularSalario() {
        return horasAula * 80.0;
    }
}
