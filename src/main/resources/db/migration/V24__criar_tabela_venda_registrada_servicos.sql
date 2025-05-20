CREATE TABLE IF NOT EXISTS venda_realizada_servicos (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    venda_realizada_id VARCHAR(150) NOT NULL,
    servico_id VARCHAR(150),
    descricao VARCHAR(100) NOT NULL,
    vl_venda DECIMAL(10,2) NOT NULL DEFAULT '0.00',
    CONSTRAINT fk_venda_realizada_produto_registro FOREIGN KEY (venda_realizada_id) REFERENCES venda_realizadas(id) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_venda_realizada_produto_produto FOREIGN KEY (servico_id) REFERENCES servicos_estabelecimento(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);