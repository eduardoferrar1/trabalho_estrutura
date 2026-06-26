package repository;

import model.Tutor;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TutorRepository {

    public Tutor salvar(Tutor tutor) throws SQLException{
        String  sql = "INSERT INTO tutor (nome, endereco, telefone) VALUES (?, ?, ?)";
        try(Connection conn = Conexao.getConnection();
              PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getEndereco());
            stmt.setString(3, tutor.getTelefone());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Tutor(rs.getLong("id"), tutor.getNome(), tutor.getEndereco(), tutor.getTelefone());
        }
    }

    public Optional<Tutor> buscarPorId(long id) throws SQLException{
        String  sql = "SELECT * FROM tutor WHERE id = ?";
        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ?Optional.of(mapear(rs)) : Optional.empty();
        }
    }

    public List<Tutor> listarTutor() throws SQLException{
        String  sql = "SELECT * FROM tutor ORDER BY nome";
        List<Tutor> lista = new ArrayList<>();
        try(Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public void atualizar(Tutor tutor) throws SQLException {
        String sql = "UPDATE tutor SET nome = ?, telefone = ?, endereco = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getTelefone());
            stmt.setString(3, tutor.getEndereco());
            stmt.setLong(4, tutor.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM tutor WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Tutor mapear(ResultSet rs) throws SQLException {
        return new Tutor(rs.getLong("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("endereco"));
    }
}
