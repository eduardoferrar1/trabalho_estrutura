package repository;

import model.Cliente;
import model.Veiculo;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoRepository {

    public Veiculo salvar(Veiculo veiculo) throws SQLException {

        String sql = """
                INSERT INTO veiculo
                (placa, modelo, ano, id_cliente)
                VALUES (?, ?, ?, ?)
                RETURNING id
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setLong(4, veiculo.getCliente().getId());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Veiculo(
                        rs.getLong("id"),
                        veiculo.getPlaca(),
                        veiculo.getModelo(),
                        veiculo.getAno(),
                        veiculo.getCliente()
                );
            }
        }

        throw new SQLException("Erro ao salvar veículo.");
    }

    public Optional<Veiculo> buscarPorId(Long id) throws SQLException {

        String sql = """
                SELECT *
                FROM veiculo
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

    public List<Veiculo> listarTodos() throws SQLException {

        String sql = """
                SELECT *
                FROM veiculo
                ORDER BY modelo
                """;

        List<Veiculo> veiculos = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                veiculos.add(mapear(rs));
            }
        }

        return veiculos;
    }

    public List<Veiculo> listarPorCliente(Long idCliente) throws SQLException {

        String sql = """
                SELECT *
                FROM veiculo
                WHERE id_cliente = ?
                ORDER BY modelo
                """;

        List<Veiculo> veiculos = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idCliente);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                veiculos.add(mapear(rs));
            }
        }

        return veiculos;
    }

    public void atualizar(Veiculo veiculo) throws SQLException {

        String sql = """
                UPDATE veiculo
                SET placa = ?,
                    modelo = ?,
                    ano = ?,
                    id_cliente = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setLong(4, veiculo.getCliente().getId());
            stmt.setLong(5, veiculo.getId());

            stmt.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException {

        String sql = """
                DELETE FROM veiculo
                WHERE id = ?
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }

    private Veiculo mapear(ResultSet rs) throws SQLException {

        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id_cliente"));

        return new Veiculo(
                rs.getLong("id"),
                rs.getString("placa"),
                rs.getString("modelo"),
                rs.getInt("ano"),
                cliente
        );
    }

}
