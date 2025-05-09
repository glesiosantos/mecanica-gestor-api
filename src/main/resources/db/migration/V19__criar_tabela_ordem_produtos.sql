CREATE TABLE IF NOT EXISTS ordem_produtos (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    ordem_id VARCHAR(150) NOT NULL,
    produto_id VARCHAR(150) NOT NULL,
    quantidade INTEGER NOT NULL,
    CONSTRAINT fk_ordem_item FOREIGN KEY (ordem_id) REFERENCES ordens(id) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_produto_item FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);