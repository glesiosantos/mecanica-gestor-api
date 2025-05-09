CREATE TABLE IF NOT EXISTS servicos_estabelecimento (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    estabelecimento_id VARCHAR(150) NOT NULL,
    descricao VARCHAR(150) NOT NULL,
    valor DECIMAL(7,2) NOT NULL DEFAULT '0.0',
    CONSTRAINT fk_estabelecimento_servico FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimentos(id) ON DELETE CASCADE ON UPDATE NO ACTION
);