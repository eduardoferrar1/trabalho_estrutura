package repository;

import model.OrdemServico;
import model.Veiculo;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdemServicoRepository {

    public OrdemServico salvar(OrdemServico ordem) throws SQLException {

        String sql = """
                INSERT INTO ordem_servico
                (id_veiculo, descricao, valor, status)
                VALUES (?, ?, ?, ?)
                RETURNING id
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, ordem.getVeiculo().getId());
            stmt.setString(2, ordem.getDescricao());
            stmt.setDouble(3, ordem.getValor());
            stmt.setString(4, ordem.getStatus());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new OrdemServico(
                        rs.getLong("id"),
                        ordem.getVeiculo(),
                        ordem.getDescricao(),
                        ordem.getValor(),
                        ordem.getStatus()
                );

            }

        }

        throw new SQLException("Erro ao salvar ordem de serviço.");

    }

    public Optional<OrdemServico> buscarPorId(Long id) throws SQLException {

        String sql = """
                SELECT *
                FROM ordem_servico
                WHERE id = ?
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapear(rs));
            }

        }

        return Optional.empty();

    }

    public List<OrdemServico> listarTodos() throws SQLException {

        String sql = """
                SELECT *
                FROM ordem_servico
                ORDER BY id DESC
                """;

        List<OrdemServico> ordens = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ordens.add(mapear(rs));
            }

        }

        return ordens;

    }

    public List<OrdemServico> listarPorVeiculo(Long idVeiculo) throws SQLException {

        String sql = """
                SELECT *
                FROM ordem_servico
                WHERE id_veiculo = ?
                ORDER BY id DESC
                """;

        List<OrdemServico> ordens = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idVeiculo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ordens.add(mapear(rs));
            }

        }

        return ordens;

    }

    public void atualizar(OrdemServico ordem) throws SQLException {

        String sql = """
                UPDATE ordem_servico
                SET descricao = ?,
                    valor = ?,
                    status = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ordem.getDescricao());
            stmt.setDouble(2, ordem.getValor());
            stmt.setString(3, ordem.getStatus());
            stmt.setLong(4, ordem.getId());

            stmt.executeUpdate();

        }

    }

    public void deletar(Long id) throws SQLException {

        String sql = """
                DELETE FROM ordem_servico
                WHERE id = ?
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();

        }

    }

    private OrdemServico mapear(ResultSet rs) throws SQLException {

        Veiculo veiculo = new Veiculo();
        veiculo.setId(rs.getLong("id_veiculo"));

        return new OrdemServico(
                rs.getLong("id"),
                veiculo,
                rs.getString("descricao"),
                rs.getDouble("valor"),
                rs.getString("status")
        );

    }

}