package controller;

import model.Veiculo;
import service.VeiculoService;

import java.sql.SQLException;

public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController() {
        this.service = new VeiculoService();
    }

    public Veiculo cadastrarVeiculo(Veiculo veiculo) {

        try {
            return service.salvar(veiculo);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());

        } catch (SQLException e) {
            System.out.println("Erro BD: " + e.getMessage());
        }

        return null;
    }

}
