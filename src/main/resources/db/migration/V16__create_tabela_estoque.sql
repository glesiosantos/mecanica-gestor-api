CREATE TABLE IF NOT EXISTS estoques (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    produto_id VARCHAR(150) NOT NULL,
    fornecedor_id VARCHAR(150) NOT NULL,
    qtd_anterior INT NOT NULL,
    qtd_adquirida int not null,
    preco_custo DECIMAL(10,2) NOT NULL DEFAULT '0.0',
    dt_criado_em DATE NOT NULL DEFAULT 'now()',
    dt_atualizado_em DATE,
    CONSTRAINT fk_produto_estoque FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_fornecedor_estoque FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);