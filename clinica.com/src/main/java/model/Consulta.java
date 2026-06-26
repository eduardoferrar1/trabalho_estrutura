package model;

public class Consulta {

    private long id;
    private String id_animal;
    private String data;
    private String motivo;
    private float valor;

    public Consulta(long id, String id_animal, String data, String motivo, float valor) {
        this.id = id;
        this.id_animal = id_animal;
        this.data = data;
        this.motivo = motivo;
        this.valor = valor;
    }


    public long getId() {return id;}
    public String getId_animal() {return id_animal;}
    public String getData() {return data;}
    public String getMotivo() {return motivo;}
    public float getValor() {return valor;}
}
