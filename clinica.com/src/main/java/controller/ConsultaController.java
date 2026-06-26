package controller;

import model.Animal;
import model.Consulta;
import service.ConsultaService;

import java.sql.SQLException;
import java.util.List;

public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController() {
        this.service = new ConsultaService();
    }

    public void registrarConsulta(Consulta consulta) {

        try {

            service.registrarConsulta(consulta);

            System.out.println("Consulta registrada com sucesso!");

        } catch (IllegalArgumentException e) {

            System.out.println("Erro: " + e.getMessage());

        } catch (SQLException e) {

            System.out.println("Erro BD: " + e.getMessage());
        }
    }

    public void exibirHistorico(Animal animal) {

        try {

            List<Consulta> consultas =
                    service.listarHistorico(animal);

            System.out.println("\nHISTÓRICO:");

            for (Consulta c : consultas) {

                System.out.println(
                        c.getData() + " - " +
                                c.getMotivo() + " - R$ " +
                                c.getValor()
                );
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }
}