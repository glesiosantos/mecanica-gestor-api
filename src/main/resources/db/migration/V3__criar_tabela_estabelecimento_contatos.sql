CREATE TABLE IF NOT EXISTS estabelecimento_contatos (
    estabelecimento_id VARCHAR(150) NOT NULL,
    contatos VARCHAR(20) NOT NULL,
    CONSTRAINT fk_estabelecimento_contato FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimentos(id) ON DELETE CASCADE ON UPDATE NO ACTION
);