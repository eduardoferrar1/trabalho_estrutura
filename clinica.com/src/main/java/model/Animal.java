package model;

public class Animal {

    private long id;
    private String nome;
    private String especie;
    private String raca;
    private long id_tutor;
    
    public Animal(long id, String nome, String especie, String raca, Long id_tutor) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.id_tutor = id_tutor;
    }

    public long getId() {return id;}
    public String getNome() {return nome;}
    public String getEspecie() {return especie;}
    public String getRaca() {return raca;}
    public long getId_tutor() {return id_tutor;}
}
