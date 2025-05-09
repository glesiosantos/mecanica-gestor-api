CREATE TABLE IF NOT EXISTS fornecedor_contatos (
    fornecedor_id VARCHAR(150) NOT NULL,
    contatos VARCHAR(20) NOT NULL,
    CONSTRAINT fk_fornecedor_contato FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id) ON DELETE CASCADE ON UPDATE NO ACTION
);