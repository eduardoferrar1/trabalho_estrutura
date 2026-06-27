package controller;

import model.OrdemServico;
import model.Veiculo;
import service.OrdemServicoService;

import java.sql.SQLException;
import java.util.List;

public class OrdemServicoController {

    private final OrdemServicoService service;

    public OrdemServicoController() {
        this.service = new OrdemServicoService();
    }

    public void abrirOrdemServico(OrdemServico ordem) {

        try {

            service.abrirOrdemServico(ordem);
            System.out.println("Ordem de serviço aberta com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());

        } catch (SQLException e) {
            System.out.println("Erro BD: " + e.getMessage());
        }

    }

    public void exibirHistorico(Veiculo veiculo) {

        try {

            List<OrdemServico> ordens =
                    service.listarHistorico(veiculo);

            System.out.println("\nHISTÓRICO DE ORDENS:");

            for (OrdemServico ordem : ordens) {

                System.out.println(
                        "Descrição: " + ordem.getDescricao() +
                                " | Valor: R$ " + ordem.getValor() +
                                " | Status: " + ordem.getStatus()
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
