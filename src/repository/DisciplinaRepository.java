package src.repository;


import java.util.ArrayList;
import java.util.List;
import src.model.Disciplina;

public class DisciplinaRepository {
    // Lista que armazena os disciplinas
    private List<Disciplina> disciplinas = new ArrayList<>();

    // Metodo save() — cadastrar disciplina
    public void save(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    // Metodo findAll() — listar todos
    public List<Disciplina> findAll() {
        return disciplinas;
    }


    //Metodo findByCodigo() — buscar disciplina específica para remover
    public Disciplina findByCodigo(String codigo) {
        for (Disciplina p : disciplinas) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    // Metodo delete() — remover 
    public boolean delete(String codigo) {
        Disciplina p = findByCodigo(codigo);
        if (p != null) {
            disciplinas.remove(p);
            return true;
        }
        return false;
    }
}
