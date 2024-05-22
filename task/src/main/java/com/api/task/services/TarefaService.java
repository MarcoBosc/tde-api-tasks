package com.api.task.services;

import com.api.task.entites.Tarefa;
import com.api.task.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository repository;

    public List<Tarefa> findAll() {
        List<Tarefa> result = repository.findAll();
        return result;
    }

    public Tarefa findById(Long id) {
        Optional<Tarefa> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }

        throw new RuntimeException();
    }

    public Tarefa create(Tarefa tarefa) {
        Tarefa result = repository.save(tarefa);
        return result;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Tarefa update(Long id, Tarefa newTarefa) {
        Tarefa currentTarefa = findById(id);
        currentTarefa.setDescricao(newTarefa.getDescricao());
        currentTarefa.setDataCriacao(newTarefa.getDataCriacao());
        currentTarefa.setDataLimite(newTarefa.getDataLimite());
        currentTarefa.setFinalizada(newTarefa.getFinalizada());
        Tarefa tarefa = repository.save(currentTarefa);
        return tarefa;
    }

    public Tarefa updateFinalizada(Long id) {
        Tarefa currentTarefa = findById(id);
        currentTarefa.setFinalizada(true);
        Tarefa tarefa = repository.save(currentTarefa);
        return tarefa;
    }
}
