package service;

import model.OrdemServico;
import model.Veiculo;
import repository.OrdemServicoRepository;

import java.sql.SQLException;
import java.util.List;

public class OrdemServicoService {

    private final OrdemServicoRepository repository;

    public OrdemServicoService() {
        this.repository = new OrdemServicoRepository();
    }

    public OrdemServico abrirOrdemServico(OrdemServico ordem) throws SQLException {

        if (ordem.getVeiculo() == null) {
            throw new IllegalArgumentException("Veículo deve estar cadastrado.");
        }

        if (ordem.getDescricao() == null || ordem.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Descrição obrigatória.");
        }

        if (ordem.getValor() < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo.");
        }

        if (ordem.getStatus() == null || ordem.getStatus().isBlank()) {
            throw new IllegalArgumentException("Status obrigatório.");
        }

        return repository.salvar(ordem);
    }

    public List<OrdemServico> listarHistorico(Veiculo veiculo) throws SQLException {
        return repository.listarPorVeiculo(veiculo.getId());
    }

}
