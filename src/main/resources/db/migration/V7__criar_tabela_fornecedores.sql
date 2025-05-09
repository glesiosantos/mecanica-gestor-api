CREATE TABLE IF NOT EXISTS fornecedores (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    nome VARCHAR(150) UNIQUE NOT NULL,
    estabelecimento_id VARCHAR(150) NOT NULL,
    CONSTRAINT fk_estabelecimento_fornecedor FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimentos(id) ON DELETE CASCADE ON UPDATE NO ACTION
);