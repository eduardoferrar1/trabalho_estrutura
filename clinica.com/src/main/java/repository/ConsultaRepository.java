package repository;


import model.Consulta;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultaRepository {

    public Consulta salvar(Consulta consulta) throws SQLException{
        String  sql = "INSERT INTO animal (id_animal, data, motivo, valor) VALUES (?, ?, ?, ?)";
        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, consulta.getId_animal());
            stmt.setString(2, consulta.getData());
            stmt.setString(3, consulta.getMotivo());
            stmt.setFloat(4, consulta.getValor());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Consulta(rs.getLong("id"), consulta.getId_animal(), consulta.getData(), consulta.getMotivo(), consulta.getValor());
        }
    }

    public Optional<Consulta> buscarPorId(long id) throws SQLException{
        String  sql = "SELECT * FROM Consulta WHERE id = ?";
        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ?Optional.of(mapear(rs)) : Optional.empty();
        }
    }

    public List<Consulta> listarConsulta() throws SQLException{
        String  sql = "SELECT * FROM Consulta ORDER BY nome";
        List<Consulta> lista = new ArrayList<>();
        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public void atualizar(Consulta consulta) throws SQLException {
        String sql = "UPDATE animal SET id_animal = ?, data = ?, motivo = ?, valor = ?, WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, consulta.getId_animal());
            stmt.setString(2, consulta.getData());
            stmt.setString(3, consulta.getMotivo());
            stmt.setFloat(4, consulta.getValor());
            stmt.setLong(5, consulta.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM consulta WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Consulta mapear(ResultSet rs) throws SQLException {
        return new Consulta(rs.getLong("id"), rs.getString("id_animal"), rs.getString("data"), rs.getString("motivo"), rs.getFloat("valor"));
    }


    public List<Consulta> listarTodos() throws SQLException {

        String sql = """
                SELECT *
                FROM consulta
                ORDER BY data_consulta DESC
                """;

        List<Consulta> consultas = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                consultas.add(mapear(rs));
            }
        }

        return consultas;
    }

    public List<Consulta> listarPorAnimal(Long idAnimal)
            throws SQLException {

        String sql = """
                SELECT *
                FROM consulta
                WHERE id_animal = ?
                ORDER BY data_consulta DESC
                """;

        List<Consulta> consultas = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idAnimal);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                consultas.add(mapear(rs));
            }
        }

        return consultas;
    }
}
