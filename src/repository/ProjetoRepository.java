package src.repository;

import java.util.ArrayList;
import java.util.List;

import src.model.Projeto;

public class ProjetoRepository {
    // Lista que armazena os PROJETOS
    private List<Projeto> projetos = new ArrayList<>();

    // Método save() — cadastrar projeto
    public void save(Projeto projeto) {
        projetos.add(projeto);
    }

    // Método findAll() — listar todos
    public List<Projeto> findAll() {
        return projetos;
    }
}
