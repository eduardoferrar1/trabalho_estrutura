package service;

import model.Cliente;
import model.Veiculo;
import repository.VeiculoRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService() {
        this.repository = new VeiculoRepository();
    }

    public Veiculo salvar(Veiculo veiculo) throws SQLException {

        if (veiculo.getCliente() == null) {
            throw new IllegalArgumentException("Veículo deve possuir um cliente.");
        }

        if (veiculo.getPlaca() == null || veiculo.getPlaca().isBlank()) {
            throw new IllegalArgumentException("Placa obrigatória.");
        }

        if (veiculo.getModelo() == null || veiculo.getModelo().isBlank()) {
            throw new IllegalArgumentException("Modelo obrigatório.");
        }

        if (veiculo.getAno() <= 0) {
            throw new IllegalArgumentException("Ano inválido.");
        }

        return repository.salvar(veiculo);
    }

    public Optional<Veiculo> buscarPorId(Long id) throws SQLException {
        return repository.buscarPorId(id);
    }

    public List<Veiculo> listarPorCliente(Cliente cliente) throws SQLException {
        return repository.listarPorCliente(cliente.getId());
    }

    public List<Veiculo> listarTodos() throws SQLException {
        return repository.listarTodos();
    }

    public void atualizar(Veiculo veiculo) throws SQLException {
        repository.atualizar(veiculo);
    }

    public void deletar(Long id) throws SQLException {
        repository.deletar(id);
    }

}
