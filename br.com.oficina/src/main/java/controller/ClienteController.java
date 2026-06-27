package controller;

import model.Cliente;
import service.ClienteService;

import java.sql.SQLException;

public class ClienteController {

    private final ClienteService service;

    public ClienteController() {
        this.service = new ClienteService();
    }

    public Cliente cadastrarCliente(Cliente cliente) {

        try {
            return service.salvar(cliente);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());

        } catch (SQLException e) {
            System.out.println("Erro BD: " + e.getMessage());
        }

        return null;
    }

}
