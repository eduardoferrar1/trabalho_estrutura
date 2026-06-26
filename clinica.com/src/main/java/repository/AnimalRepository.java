package repository;

import model.Animal;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimalRepository {

    public Animal salvar(Animal animal) throws SQLException{
        String  sql = "INSERT INTO animal (nome, raca, especie, id_tutor) VALUES (?, ?, ?, ?)";
        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getRaca());
            stmt.setString(3, animal.getEspecie());
            stmt.setLong(4, animal.getId_tutor());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Animal(rs.getLong("id"), animal.getNome(), animal.getRaca(), animal.getEspecie(), animal.getId_tutor());
        }
    }

    public Optional<Animal> buscarPorId(long id) throws SQLException{
        String  sql = "SELECT * FROM Animal WHERE id = ?";
        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ?Optional.of(mapear(rs)) : Optional.empty();
        }
    }

    public List<Animal> listarAnimal() throws SQLException{
        String  sql = "SELECT * FROM animal ORDER BY nome";
        List<Animal> lista = new ArrayList<>();
        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public void atualizar(Animal animal) throws SQLException {
        String sql = "UPDATE animal SET nome = ?, especie = ?, raca = ?, id_tutor = ?, WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setLong(4, animal.getId_tutor());
            stmt.setLong(5, animal.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM animal WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Animal mapear(ResultSet rs) throws SQLException {
        return new Animal(rs.getLong("id"), rs.getString("nome"), rs.getString("especie"), rs.getString("raca"), rs.getLong("id_tutor"));
    }
}
