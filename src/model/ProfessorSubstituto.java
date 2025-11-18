package src.model;

public class ProfessorSubstituto extends Professor {

    private int horasAula;

    public ProfessorSubstituto(String nome, String matricula, String titulacao, int horasAula) {
        super(nome, matricula, titulacao);
        this.horasAula = horasAula;
    }

    public int getHorasAula() { return horasAula; }
    public void setHorasAula(int horasAula) { this.horasAula = horasAula; }

    @Override
    public double calcularSalario() {
        return horasAula * 80.0; // regra do PDF
    }
}
