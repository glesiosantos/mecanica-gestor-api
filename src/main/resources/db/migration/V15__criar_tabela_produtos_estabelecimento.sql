CREATE TABLE IF NOT EXISTS produtos (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    cod_produto VARCHAR(10) NOT NULL,
    referencia VARCHAR(60) NOT NULL,
    descricao VARCHAR(150) NOT NULL,
    categoria CHAR(5) NOT NULL,
    preco_custo DECIMAL(7,2) NOT NULL DEFAULT '0.0',
    percentual INTEGER NOT NULL,
    qtd_minima_estoque INTEGER NOT NULL DEFAULT '0',
    qtd_atual_estoque INTEGER NOT NULL DEFAULT '0',
    estabelecimento_id VARCHAR(150) NOT NULL,
    CONSTRAINT fk_estabelecimento_produto FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimentos(id) ON DELETE CASCADE ON UPDATE NO ACTION
);