CREATE TABLE IF NOT EXISTS ordem_servicos (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    ordem_id VARCHAR(150) NOT NULL,
    servico_id VARCHAR(150) NOT NULL,
    CONSTRAINT fk_ordem_servico FOREIGN KEY (ordem_id) REFERENCES ordens(id) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_servico_ordem FOREIGN KEY (servico_id) REFERENCES servicos_estabelecimento(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);