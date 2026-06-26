package service;

import model.Animal;
import model.Tutor;
import repository.AnimalRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AnimalService {
    private final AnimalRepository repository;

    public AnimalService() {
        this.repository = new AnimalRepository();
    }

    public Animal salvar(Animal animal) throws SQLException {



        if (animal.getNome() == null || animal.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório.");
        }

        return repository.salvar(animal);
    }

    public Optional<Animal> buscarPorId(Long id) throws SQLException {
        return repository.buscarPorId(id);
    }

    public void atualizar(Animal animal) throws SQLException {
        repository.atualizar(animal);
    }

    public void deletar(Long id) throws SQLException {
        repository.deletar(id);
    }
}
