package model;

public class Tutor {

    private long id;
    private String nome;
    private String endereco;
    private String telefone;

    public Tutor(long id, String nome, String endereco, String telefone) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public long getId() {return id;}
    public String getNome() {return nome;}
    public String getEndereco() {return endereco;}
    public String getTelefone() {return telefone;}
}
