TABELAS: 
    tutor: id, nome, endereço, telefone.
    animal: id, id_tutor, nome, especie, raça
    consulta: id, id_animal, data, motivo, valor

CREATE TABLE tutor (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(200) NOT NULL,
    telefone VARCHAR(20) NOT NULL
    );

CREATE TABLE animal (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    raca VARCHAR(50) NOT NULL,
    id_tutor BIGINT NOT NULL,

    CONSTRAINT fk_animal_tutor
        FOREIGN KEY (id_tutor)
        REFERENCES tutor(id)
);

CREATE TABLE consulta (
    id BIGSERIAL PRIMARY KEY,
    id_animal BIGINT NOT NULL,
    data_consulta DATE NOT NULL,
    motivo TEXT NOT NULL,
    valor NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_consulta_animal
        FOREIGN KEY (id_animal)
        REFERENCES animal(id)
);

Regras de Negócio

Um tutor pode possuir vários animais.
Não pode existir consulta para um animal não cadastrado.
O valor da consulta não pode ser negativo.


