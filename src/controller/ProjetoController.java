package src.controller;

import java.util.List;

import src.model.Projeto;
import src.repository.ProjetoRepository;

public class ProjetoController {
    private ProjetoRepository projetoRepository;

    public ProjetoController(ProjetoRepository repository) {
        this.projetoRepository = repository;
    }

    // 1) CADASTRAR PROJETO
    public void cadastrarProjeto(Projeto projeto) {
        projetoRepository.save(projeto);
    }

    // 2) Listar projeto
    public List<Projeto> listarProjeto() {
        return projetoRepository.findAll();
    }
}
