CREATE TABLE IF NOT EXISTS cliente_estabelecimento (
    cliente_id VARCHAR(150) NOT NULL,
    estabelecimento_id VARCHAR(150) NOT NULL,
    CONSTRAINT fk_cliente_estabelecimento FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_estabelecimento_cliente FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimentos(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);