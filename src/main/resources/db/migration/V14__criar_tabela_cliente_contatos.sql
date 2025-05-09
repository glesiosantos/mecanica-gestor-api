CREATE TABLE IF NOT EXISTS cliente_contatos (
    cliente_id VARCHAR(150) NOT NULL,
    contatos VARCHAR(20) NOT NULL,
    CONSTRAINT fk_cliente_contato FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE ON UPDATE NO ACTION
);