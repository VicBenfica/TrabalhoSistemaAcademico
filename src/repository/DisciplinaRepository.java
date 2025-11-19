package src.repository;


import java.util.ArrayList;
import java.util.List;
import src.model.Disciplina;

public class DisciplinaRepository {
    // Lista que armazena os disciplinas
    private List<Disciplina> disciplinas = new ArrayList<>();

    // Método save() — cadastrar disciplina
    public void save(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    // Método findAll() — listar todos
    public List<Disciplina> findAll() {
        return disciplinas;
    }


    //Método findByCodigo() — buscar disciplina específica para remover
    public Disciplina findByCodigo(String codigo) {
        for (Disciplina p : disciplinas) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    // Método delete() — remover 
    public boolean delete(String codigo) {
        Disciplina p = findByCodigo(codigo);
        if (p != null) {
            disciplinas.remove(p);
            return true;
        }
        return false;
    }
}
