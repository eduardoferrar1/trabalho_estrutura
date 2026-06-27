package model;

public class Veiculo {

    private Long id;
    private String placa;
    private String modelo;
    private Integer ano;
    private Cliente cliente;

    public Veiculo() {
    }

    public Veiculo(Long id,
                   String placa,
                   String modelo,
                   Integer ano,
                   Cliente cliente) {

        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return placa + " - " + modelo + " (" + ano + ")";
    }

}
