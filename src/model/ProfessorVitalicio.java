package src.model;

import java.util.ArrayList;
import java.util.List;

public class ProfessorVitalicio extends Professor {

    private List<Projeto> projetos = new ArrayList<>();
    private int doutorado;

    public ProfessorVitalicio(String nome, String matricula, String titulacao, double salarioBase, int doutorado) {
        super(nome, matricula, titulacao, salarioBase);
        this.doutorado = doutorado;

    }

    public double getBonus() {
        return doutorado == 1 ? getSalarioBase() * 0.20 : 0.0;
    }

    // PROJETOS
    public void adicionarProjeto(Projeto projeto) {
        projetos.add(projeto);
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    // CALCULO DE SALARIO
    @Override
    public double calcularSalario() {
        return getSalarioBase() + getBonus();
    }

}
