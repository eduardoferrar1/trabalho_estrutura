package service;

import model.Animal;
import model.Consulta;
import repository.ConsultaRepository;

import java.sql.SQLException;
import java.util.List;

public class ConsultaService {

    private final ConsultaRepository repository;

    public ConsultaService() {
        this.repository = new ConsultaRepository();
    }

    public Consulta registrarConsulta(Consulta consulta) throws SQLException {

        if (consulta.getId_animal() == null) {
            throw new IllegalArgumentException("Animal deve estar cadastrado.");
        }

        if (consulta.getValor() < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo.");
        }

        return repository.salvar(consulta);
    }

    public List<Consulta> listarHistorico(Animal animal)
            throws SQLException {

        return repository.listarPorAnimal(animal.getId());
    }
}


