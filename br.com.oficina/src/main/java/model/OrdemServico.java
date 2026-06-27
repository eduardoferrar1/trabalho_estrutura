package model;


public class OrdemServico {

    private Long id;
    private Veiculo veiculo;
    private String descricao;
    private Double valor;
    private String status;

    public OrdemServico() {
    }

    public OrdemServico(Long id, Veiculo veiculo, String descricao, Double valor, String status) {
        this.id = id;
        this.veiculo = veiculo;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {

        return "OrdemServico{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", status='" + status + '\'' +
                '}';

    }

}
