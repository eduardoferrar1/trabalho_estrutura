package service;

import model.Cliente;
import repository.ClienteRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService() {
        this.repository = new ClienteRepository();
    }

    public Cliente salvar(Cliente cliente) throws SQLException {

        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório.");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().isBlank()) {
            throw new IllegalArgumentException("Telefone obrigatório.");
        }

        return repository.salvar(cliente);
    }

    public Optional<Cliente> buscarPorId(Long id) throws SQLException {
        return repository.buscarPorId(id);
    }

    public List<Cliente> listarTodos() throws SQLException {
        return repository.listarTodos();
    }

    public void atualizar(Cliente cliente) throws SQLException {
        repository.atualizar(cliente);
    }

    public void deletar(Long id) throws SQLException {
        repository.deletar(id);
    }

}