import controller.ClienteController;
import controller.OrdemServicoController;
import controller.VeiculoController;
import model.Cliente;
import model.OrdemServico;
import model.Veiculo;

public class Main {

    public static void main(String[] args) {

        ClienteController clienteController =
                new ClienteController();

        VeiculoController veiculoController =
                new VeiculoController();

        OrdemServicoController ordemController =
                new OrdemServicoController();

        System.out.println("=== OFICINA MECÂNICA ===");

        Cliente cliente = new Cliente(
                null,
                "Eduardo Ferrari",
                "(44)99937-5053"
        );

        cliente = clienteController.cadastrarCliente(cliente);

        Veiculo veiculo = new Veiculo(
                null,
                "AWA-7863",
                "fiat uno",
                2008,
                cliente
        );

        veiculo = veiculoController.cadastrarVeiculo(veiculo);

        OrdemServico ordem = new OrdemServico(
                null,
                veiculo,
                "Troca de óleo e filtros",
                250.00,
                "ABERTA"
        );

        ordemController.abrirOrdemServico(ordem);

        ordemController.exibirHistorico(veiculo);

        System.out.println("\nFluxo concluído.");

    }

}
