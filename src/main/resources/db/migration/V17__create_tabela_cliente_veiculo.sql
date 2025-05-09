CREATE TABLE IF NOT EXISTS cliente_veiculo (
    cliente_id VARCHAR(150) NOT NULL,
    veiculo_id VARCHAR(150) NOT NULL,
    CONSTRAINT fk_cliente_veiculo FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_veiculo_cliente FOREIGN KEY (veiculo_id) REFERENCES veiculos(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);