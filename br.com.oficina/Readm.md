CREATE TABLE cliente(

    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL

);

CREATE TABLE veiculo(

    id BIGSERIAL PRIMARY KEY,
    placa VARCHAR(10) UNIQUE NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    ano INTEGER NOT NULL,
    id_cliente BIGINT NOT NULL,
    CONSTRAINT fk_cliente
        FOREIGN KEY(id_cliente)
        REFERENCES cliente(id)

);

CREATE TABLE ordem_servico(

    id BIGSERIAL PRIMARY KEY,
    id_veiculo BIGINT NOT NULL,
    descricao TEXT NOT NULL,
    valor NUMERIC(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_veiculo
        FOREIGN KEY(id_veiculo)
        REFERENCES veiculo(id)

);

clientes informando pelo menos nome e telefone
cliente pode ter mais de um veículo
registrar os veículos com pelo menos placa, modelo e ano
Não permitir abrir uma ordem de serviço para um veículo que não esteja cadastrado
O valor do serviço não pode ser negativo