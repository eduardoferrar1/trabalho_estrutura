package service;

import model.Tutor;
import repository.TutorRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TutorService {

    private final TutorRepository repository;

    public TutorService() {
        this.repository = new TutorRepository();
    }

    public Tutor salvar(Tutor tutor) throws SQLException {

        if (tutor.getNome() == null || tutor.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório.");
        }

        if (tutor.getEndereco() == null || tutor.getEndereco().isBlank()) {
            throw new IllegalArgumentException("Endereço obrigatório.");
        }

        if (tutor.getTelefone() == null || tutor.getTelefone().isBlank()) {
            throw new IllegalArgumentException("Telefone obrigatório.");
        }

        return repository.salvar(tutor);
    }

    public Optional<Tutor> buscarPorId(Long id) throws SQLException {
        return repository.buscarPorId(id);
    }

    public void atualizar(Tutor tutor) throws SQLException {
        repository.atualizar(tutor);
    }

    public void deletar(Long id) throws SQLException {
        repository.deletar(id);
    }
}