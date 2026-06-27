package repository;

import model.Cliente;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepository {

    public Cliente salvar(Cliente cliente) throws SQLException {

        String sql = """
                INSERT INTO cliente (nome, telefone)
                VALUES (?, ?)
                RETURNING id
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getLong("id"),
                        cliente.getNome(),
                        cliente.getTelefone()
                );
            }
        }

        throw new SQLException("Erro ao salvar cliente.");
    }

    public Optional<Cliente> buscarPorId(Long id) throws SQLException {

        String sql = """
                SELECT *
                FROM cliente
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

    public List<Cliente> listarTodos() throws SQLException {

        String sql = """
                SELECT *
                FROM cliente
                ORDER BY nome
                """;

        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clientes.add(mapear(rs));
            }
        }

        return clientes;
    }

    public void atualizar(Cliente cliente) throws SQLException {

        String sql = """
                UPDATE cliente
                SET nome = ?, telefone = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setLong(3, cliente.getId());

            stmt.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException {

        String sql = """
                DELETE FROM cliente
                WHERE id = ?
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }

    private Cliente mapear(ResultSet rs) throws SQLException {

        return new Cliente(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("telefone")
        );
    }
}